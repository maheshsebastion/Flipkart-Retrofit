package com.example.admin.flipkart.api.event;

import com.example.admin.flipkart.api.response.ProductAPIResponse;
import com.example.admin.flipkart.api.subscriber.ProductEventSubscriber;

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

    public static void get(final ProductEventSubscriber subscriber) {

        apiInterface.getProducts().enqueue(new Callback<ProductAPIResponse>() {
            @Override
            public void onResponse(Call<ProductAPIResponse> call, Response<ProductAPIResponse> response) {
                if(response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                }else {
//                    subscriber.onProductCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }
            @Override
            public void onFailure(Call<ProductAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }


}
