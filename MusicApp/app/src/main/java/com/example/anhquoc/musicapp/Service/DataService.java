package com.example.anhquoc.musicapp.Service;

import com.example.anhquoc.musicapp.Model.Album;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.Model.Chude;
import com.example.anhquoc.musicapp.Model.Playlist;
import com.example.anhquoc.musicapp.Model.Quangcao;
import com.example.anhquoc.musicapp.Model.TatcaAlbum;
import com.example.anhquoc.musicapp.Model.Theloai;
import com.example.anhquoc.musicapp.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>>  GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<Chude>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<Theloai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<TatcaAlbum>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>>  GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);
}
