package com.example.myapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.0.113:8080/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance ==null){
            instance = new RetrofitClient();
        }
        return instance;
    }
    public UserService getAPI(){
        return retrofit.create(UserService.class);
    }
}
