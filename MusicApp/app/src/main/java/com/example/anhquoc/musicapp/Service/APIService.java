package com.example.anhquoc.musicapp.Service;

public class APIService {
    private static String base_url = "http://localhost:8080/musicapp/server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
