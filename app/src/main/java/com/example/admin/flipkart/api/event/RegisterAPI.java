package com.example.admin.flipkart.api.event;

import com.example.admin.flipkart.api.response.RegisterAPIResponse;
import com.example.admin.flipkart.api.subscriber.RegisterEventSubscriber;
import com.example.admin.flipkart.login.RegisterActivity;
import com.example.admin.flipkart.request.RegistrationRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 05-09-2017.
 */

public class RegisterAPI extends APIAbstract {
    public RegisterAPI() {
    }
    public static void postRegisterDetails(RegistrationRequest request,final RegisterEventSubscriber subscriber) {

        apiInterface.postRegisterDetails(request).enqueue(new Callback<RegisterAPIResponse>() {
            @Override
            public void onResponse(Call<RegisterAPIResponse> call, Response<RegisterAPIResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onRegisterationCompleted(response.body());
                } else {
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<RegisterAPIResponse> call, Throwable t) {
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
