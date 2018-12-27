package com.example.anhquoc.musicapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anhquoc.musicapp.Adapter.DanhsachbaihatAdapter;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.Model.Playlist;
import com.example.anhquoc.musicapp.Model.Quangcao;
import com.example.anhquoc.musicapp.Model.TatcaAlbum;
import com.example.anhquoc.musicapp.Model.Theloai;
import com.example.anhquoc.musicapp.R;
import com.example.anhquoc.musicapp.Service.APIService;
import com.example.anhquoc.musicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {

        Quangcao quangcao;
        ImageView imgdanhsachcakhuc;
        CoordinatorLayout coordinatorLayout;
        CollapsingToolbarLayout collapsingToolbarLayout;
        Toolbar toolbar;
        RecyclerView recyclerViewdanhsachbaihat;
        FloatingActionButton floatingActionButton;
        ArrayList<Baihat> mangbaihat;
        DanhsachbaihatAdapter danhsachbaihatAdapter;
        Playlist playlist;
        Theloai theloai;
        TatcaAlbum Album;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_danhsachbaihat);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DataIntent();
            anhxa();
            init();
            if (quangcao != null && !quangcao.getTenBaiHat().equals("")){
                setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
                GetDataQuangcao(quangcao.getId());
            }
            if (playlist != null && !playlist.getTen().equals("")){
                setValueInView(playlist.getTen(),playlist.getHinhNen());
                GetDataPlaylist(playlist.getIdPlayList());
            }
            if (theloai != null && !theloai.getTenTheLoai().equals("")){
                setValueInView(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
                GetDataTheLoai(theloai.getIdTheLoai());
            }
            if (Album != null && !Album.getTenAlbum().equals("")){
                setValueInView(Album.getTenAlbum(), Album.getHinhAlbum());
                GetDataAlbum(Album.getIdAlbum());
            }
        }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai){
            DataService dataService = APIService.getService();
            Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
            callback.enqueue(new Callback<List<Baihat>>() {
                @Override
                public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                    mangbaihat = (ArrayList<Baihat>) response.body();
                    danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                    eventClick();
                }

                @Override
                public void onFailure(Call<List<Baihat>> call, Throwable t) {

                }
            });
    }

    private void GetDataPlaylist(String idplaylist) {
            DataService dataService = APIService.getService();
            Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
            callback.enqueue(new Callback<List<Baihat>>() {
                @Override
                public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                    mangbaihat = (ArrayList<Baihat>) response.body();
                    danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                    eventClick();
                }

                @Override
                public void onFailure(Call<List<Baihat>> call, Throwable t) {

                }
            });
    }

    private void setValueInView(String ten , String hinh) {
            collapsingToolbarLayout.setTitle(ten);
            try {
                URL url = new URL(hinh);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    collapsingToolbarLayout.setBackground(bitmapDrawable);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
        }

        private void GetDataQuangcao(String id) {
            DataService dataService = APIService.getService();
            Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheoquangcao(id);
            callback.enqueue(new Callback<List<Baihat>>() {
                @Override
                public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                    mangbaihat = (ArrayList<Baihat>) response.body();
                    danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity. this,mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                    eventClick();
                }

                @Override
                public void onFailure(Call<List<Baihat>> call, Throwable t) {

                }
            });
        }


        private void init() {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                public  void onClick(View v){
                    finish();
                }
            });
            collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
            floatingActionButton.setEnabled(false);
        }


        private void anhxa() {
            coordinatorLayout = findViewById(R.id.coordinatorlayout);
            collapsingToolbarLayout = findViewById(R.id.collapstingtoolbar);
            toolbar = findViewById(R.id.toolbardanhsach);
            recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
            floatingActionButton = findViewById(R.id.floatingactionbutton);
            imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        }

        private void DataIntent() {
            Intent intent = getIntent();
            if (intent != null){
                if (intent.hasExtra("banner")){
                    quangcao = (Quangcao) intent.getSerializableExtra("banner");
                }
                if (intent.hasExtra("itemplaylist")){
                    playlist = (Playlist) intent.getSerializableExtra( "itemplaylist");
                }
                if (intent.hasExtra("idtheloai")){
                    theloai = (Theloai) intent.getSerializableExtra("idtheloai");
                }
                if (intent.hasExtra("album")){
                    Album = (TatcaAlbum) intent.getSerializableExtra("album");
                }
            }
        }
    private void  eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }

    }
