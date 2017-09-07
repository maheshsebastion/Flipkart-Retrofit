package com.example.admin.flipkart.api.event;

import android.util.Log;

import com.example.admin.flipkart.api.response.LoginAPIResponse;
import com.example.admin.flipkart.api.subscriber.LoginEventSubscriber;
import com.example.admin.flipkart.request.LoginRequest;

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
                Log.i("TAG1","response>>>>>>>"+response.body());
                if (response.isSuccessful()) {
                    Log.i("TAG1","response>>>>>>><<<<<<<<<"+response.body());
                    subscriber.onLoginCompleted(response.body());
                } else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<LoginAPIResponse> call, Throwable t) {
                Log.i("TAG1","response<<<<<<<<<---->>>>>>>FAILED");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
