package com.example.retrofit_lib;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_Client {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
            )).
                    build())
                    .build();

        }
        return retrofit;
    }
}
