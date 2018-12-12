package com.example.anhquoc.musicapp.Service;

import com.example.anhquoc.musicapp.Model.Album;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.Model.Chude;
import com.example.anhquoc.musicapp.Model.Playlist;
import com.example.anhquoc.musicapp.Model.Quangcao;
import com.example.anhquoc.musicapp.Model.Theloai;
import com.example.anhquoc.musicapp.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<List<Chude>> Gettopic();

    @GET("chudevatheloaitrongngay.php")
    Call<List<Theloai>> Getkind();

    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();
}
