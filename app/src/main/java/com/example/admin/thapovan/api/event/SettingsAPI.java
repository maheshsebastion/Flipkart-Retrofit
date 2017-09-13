package com.example.admin.thapovan.api.event;

import com.example.admin.thapovan.api.response.SettingsAPIResponse;
import com.example.admin.thapovan.api.subscriber.SettingsEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 28-08-2017.
 */

public class SettingsAPI extends APIAbstract {

    private SettingsAPI() {
        // empty method
    }

    public static void getCategories(final SettingsEventSubscriber subscriber) {

        apiInterface.getCategory().enqueue(new Callback<SettingsAPIResponse>() {
            @Override
            public void onResponse(Call<SettingsAPIResponse> call, Response<SettingsAPIResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onSettingsCompleted(response.body());
                } else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<SettingsAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void getCategoryListByBrand(final SettingsEventSubscriber subscriber) {

        apiInterface.getBrand().enqueue(new Callback<SettingsAPIResponse>() {
            @Override
            public void onResponse(Call<SettingsAPIResponse> call, Response<SettingsAPIResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onSettingsCompleted(response.body());
                } else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<SettingsAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
