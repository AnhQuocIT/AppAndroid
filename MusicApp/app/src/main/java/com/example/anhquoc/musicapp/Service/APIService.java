package com.example.anhquoc.musicapp.Service;

public class APIService {
    private static String base_url = "link a folder API";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
