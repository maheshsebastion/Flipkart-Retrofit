package com.example.admin.flipkart.api.remote;

import com.example.admin.flipkart.api.util.APIUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 22-08-2017.
 */

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(final String baseURL){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
