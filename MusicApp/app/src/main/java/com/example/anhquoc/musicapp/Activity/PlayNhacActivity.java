package com.example.anhquoc.musicapp.Activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.anhquoc.musicapp.Adapter.ViewPagerPlaylistnhac;
import com.example.anhquoc.musicapp.Fragment.Fragment_Dia_Nhac;
import com.example.anhquoc.musicapp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong,txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom, imgdownload, imagshare;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    static MediaPlayer mediaPlayer = null;
    DownloadManager downloadManager;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        init();
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(mangbaihat.size() >0) {
                        fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.playbutton);
                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.stopbutton);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position <(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.stopbutton);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }
                        if(position >(mangbaihat.size() -1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position <(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.stopbutton);
                        position--;
                        if(position <0){
                            position = mangbaihat.size() -1;
                        }
                        if(repeat == true){

                            position +=1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
        imgdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(mangbaihat.get(position).getLinkBaiHat());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });

        imagshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                                                .setQuote("Music app: " + mangbaihat.get(position).getTenBaiHat())
                                                .setContentUrl(Uri.parse(mangbaihat.get(position).getLinkBaiHat()))
                                                .build();
                if(shareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(shareLinkContent);
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init(){
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgdownload = findViewById(R.id.btnDownload);
        viewPagerplaynhac = findViewById(R.id.viewpaperplaynhac);
        imagshare = findViewById(R.id.btnShare);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        if(mangbaihat.size() == 1){
            adapternhac.AddFragment(fragment_dia_nhac);
            adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
            viewPagerplaynhac.setAdapter(adapternhac);
            fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(0);
        } else {
            adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
            adapternhac.AddFragment(fragment_dia_nhac);
            viewPagerplaynhac.setAdapter(adapternhac);
            fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        }

        if(mangbaihat.size() >0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.stopbutton);
        }
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer !=null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(position <(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.stopbutton);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }
                        if(position >(mangbaihat.size() -1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(false);
                        imgnext.setClickable(true);
                    }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
