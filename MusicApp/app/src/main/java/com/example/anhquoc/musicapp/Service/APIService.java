package com.example.anhquoc.musicapp.Service;

public class APIService {
    private static String base_url = "http://192.168.56.1:8080/musicapp/server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
