package com.example.admin.flipkart.api.remote;

import com.example.admin.flipkart.api.response.ProductAPIResponse;
import com.example.admin.flipkart.api.response.SettingsAPIResponse;
import com.example.admin.flipkart.api.util.APIUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 28-08-2017.
 */

public interface APIInterface {

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProducts();

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProductByCategory(@Query("category_id") int id);

    @GET(APIUtil.API_PRODUCT)
    Call<ProductAPIResponse> getProductByBrand(@Query("brand_id") int id);

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsAPIResponse> getCategory();

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsAPIResponse> getBrand();
}
