package com.example.admin.thapovan.api.event;

import com.example.admin.thapovan.api.response.ProductAPIResponse;
import com.example.admin.thapovan.api.subscriber.ProductEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 28-08-2017.
 */

public class ProductAPI extends APIAbstract{

    private ProductAPI() {
        // empty method
    }

    public static void getProducts(int page, final ProductEventSubscriber subscriber) {

        apiInterface.getProducts(page).enqueue(new Callback<ProductAPIResponse>() {
            @Override
            public void onResponse(Call<ProductAPIResponse> call, Response<ProductAPIResponse> response) {
                if(response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                }else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }
            @Override
            public void onFailure(Call<ProductAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
    public static void getProductBrand(final int brandId, final ProductEventSubscriber subscriber) {

        apiInterface.getProductByBrand(brandId).enqueue(new Callback<ProductAPIResponse>() {
            @Override
            public void onResponse(Call<ProductAPIResponse> call, Response<ProductAPIResponse> response) {
                if(response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                }else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }
            @Override
            public void onFailure(Call<ProductAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
    public static void getProductCategory(final int categoryId, final ProductEventSubscriber subscriber) {

        apiInterface.getProductByCategory(categoryId).enqueue(new Callback<ProductAPIResponse>() {
            @Override
            public void onResponse(Call<ProductAPIResponse> call, Response<ProductAPIResponse> response) {
                if(response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                }else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }
            @Override
            public void onFailure(Call<ProductAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

}
