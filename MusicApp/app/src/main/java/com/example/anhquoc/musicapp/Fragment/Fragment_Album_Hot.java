package com.example.anhquoc.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anhquoc.musicapp.Activity.DanhsachtatcaAlbumActivity;
import com.example.anhquoc.musicapp.Adapter.AlbumAdapter;
import com.example.anhquoc.musicapp.Model.Album;
import com.example.anhquoc.musicapp.R;
import com.example.anhquoc.musicapp.Service.APIService;
import com.example.anhquoc.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    RecyclerView recyclerViewalbum;
    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot,container,false);
        recyclerViewalbum = view.findViewById(R.id.recycleviewAlbum);
        txtxemthemalbum = view.findViewById(R.id.textviewxemthemAlbum);
        txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaAlbumActivity.class);
                startActivity(intent);
            }
        });
        //Mapping();
        GetData();
        return view;
    }

    /*private void Mapping() {
        viewPager = view.findViewById(R.id.viewpaper);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }*/

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewalbum.setLayoutManager(linearLayoutManager);
                recyclerViewalbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
