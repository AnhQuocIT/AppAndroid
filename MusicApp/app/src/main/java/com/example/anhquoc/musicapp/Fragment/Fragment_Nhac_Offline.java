package com.example.anhquoc.musicapp.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhquoc.musicapp.Activity.DanhsachbaihatActivity;
import com.example.anhquoc.musicapp.Activity.PlayNhacActivity;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.R;

import java.util.ArrayList;


public class Fragment_Nhac_Offline extends Fragment {
    View view;
    private static final int MY_PERMISSION_REQUEST = 1;
    TextView seeAll;
    ArrayList<Baihat> mangbaihat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nhac_offline, container, false);
        seeAll = view.findViewById(R.id.txtSeeAll);
        mangbaihat = new ArrayList<Baihat>();
        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else {
            getSongListFromStorage();
        }
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() == 0) {
                    Toast.makeText(getActivity(), "Không có bài hát nào trong thiết bị của bạn!", Toast.LENGTH_SHORT).show();
                } else if(mangbaihat.size() > 0) {
                    Intent intent = new Intent(getActivity(), PlayNhacActivity.class);
                    intent.putExtra("cacbaihat", mangbaihat);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    public void getSongListFromStorage() {
        //retrieve song info
        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int dataColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            //add songs to list
            do {
                String thisId = musicCursor.getString(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisData = musicCursor.getString(dataColumn);
                mangbaihat.add(new Baihat(thisId, thisTitle, "http://baihatyeuthich.vn/wp-content/uploads/2018/03/ta-dung-cua-am-nhac.png" , thisArtist, thisData,"0"));
            } while (musicCursor.moveToNext());
        }
        musicCursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_SHORT).show();
                        getSongListFromStorage();
                    }
                } else {
                    Toast.makeText(getActivity(), "Granted fail", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
