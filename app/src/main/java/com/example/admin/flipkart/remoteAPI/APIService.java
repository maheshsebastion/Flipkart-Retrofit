package com.example.admin.flipkart.remoteAPI;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 14-08-2017.
 */

public interface APIService {

    @GET("/projects/learning/laravel/e-commerce-portal/api/v1/getProducts")
    Call<APIResponse> getProducts();

    @GET("/projects/learning/laravel/e-commerce-portal/api/v1/getSettings")
    Call<APIResponse> getCategory();

}
