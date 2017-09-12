package com.example.admin.flipkart.api.remote;

import com.example.admin.flipkart.api.event.LoginAPI;
import com.example.admin.flipkart.api.response.LoginAPIResponse;
import com.example.admin.flipkart.api.response.ProductAPIResponse;
import com.example.admin.flipkart.api.response.RegisterAPIResponse;
import com.example.admin.flipkart.api.response.SettingsAPIResponse;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.request.LoginRequest;
import com.example.admin.flipkart.request.RegistrationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Admin on 28-08-2017.
 */

public interface APIInterface {

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProducts(@Query("page") int page);

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProductByCategory(@Query("category_id") int id);

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProductByBrand(@Query("brand_id") int id);

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsAPIResponse> getCategory();

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsAPIResponse> getBrand();

    @POST(APIUtil.API_LOGIN)
    Call<LoginAPIResponse> postLoginDetails(@Body LoginRequest loginRequest);

    @POST(APIUtil.API_REGISTER)
    Call<RegisterAPIResponse> postRegisterDetails(@Body RegistrationRequest registrationRequest);
}
