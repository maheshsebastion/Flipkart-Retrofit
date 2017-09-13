package com.example.admin.thapovan.api.event;

import android.util.Log;

import com.example.admin.thapovan.api.response.LoginAPIResponse;
import com.example.admin.thapovan.api.subscriber.LoginEventSubscriber;
import com.example.admin.thapovan.request.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 05-09-2017.
 */

public class LoginAPI extends APIAbstract {

    public LoginAPI() {
    }
    public static void postLoginDetails(LoginRequest request, final LoginEventSubscriber subscriber) {

        apiInterface.postLoginDetails(request).enqueue(new Callback<LoginAPIResponse>() {
            @Override
            public void onResponse(Call<LoginAPIResponse> call, Response<LoginAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onLoginCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<LoginAPIResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
