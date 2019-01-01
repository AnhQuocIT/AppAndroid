package com.example.anhquoc.musicapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.anhquoc.musicapp.Adapter.MainViewPaperAdapter;
import com.example.anhquoc.musicapp.Fragment.Fragment_Home;
import com.example.anhquoc.musicapp.Fragment.Fragment_Search;

import com.example.anhquoc.musicapp.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatActivity appCompatActivity;
    SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping(); //Ánh xạ tìm kiếm theo id
//        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_dark,android.R.color.holo_blue_light,android.R.color.holo_green_dark,android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }, 3000);
            }
        });
        init();
    }

    private void init() {
        MainViewPaperAdapter mainViewPaperAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Home(), getString(R.string.lbl_home));
        mainViewPaperAdapter.addFragment(new Fragment_Search(),getString(R.string.lbl_search));
        viewPager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconhome);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

    }

    private void mapping(){
        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        viewPager = (ViewPager) findViewById(R.id.myViewPaper);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
    }
}
