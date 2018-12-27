package com.example.anhquoc.musicapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anhquoc.musicapp.Adapter.AllAlbumAdapter;
import com.example.anhquoc.musicapp.Model.TatcaAlbum;
import com.example.anhquoc.musicapp.R;
import com.example.anhquoc.musicapp.Service.APIService;
import com.example.anhquoc.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAlbum;
    Toolbar toolbarAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TatcaAlbum>> callback = dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<TatcaAlbum>>() {
            @Override
            public void onResponse(Call<List<TatcaAlbum>> call, Response<List<TatcaAlbum>> response) {
                ArrayList<TatcaAlbum> arrayAlbum = (ArrayList<TatcaAlbum>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this, arrayAlbum);
                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this,2));
                recyclerViewAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<TatcaAlbum>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAlbum = findViewById(R.id.recyclerviewAllAlbum);
        toolbarAlbum = findViewById(R.id.ToolbarAllAlbum);
        //Tao nut cho toolbar de khi click thi quay ve trang truoc do
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
