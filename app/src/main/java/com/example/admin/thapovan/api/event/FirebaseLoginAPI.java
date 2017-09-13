package com.example.admin.thapovan.api.event;

import android.util.Log;

import com.example.admin.thapovan.api.response.FirebaseLoginAPIResponse;
import com.example.admin.thapovan.api.subscriber.FirebaseLoginEventSubscriber;
import com.example.admin.thapovan.request.FirebaseLoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 13-09-2017.
 */

public class FirebaseLoginAPI extends APIAbstract{

    public FirebaseLoginAPI() {
    }
    public static void postFirebaseLoginDetails(FirebaseLoginRequest request, final FirebaseLoginEventSubscriber subscriber) {

        apiInterface.postFirebaseLoginDetails(request).enqueue(new Callback<FirebaseLoginAPIResponse>() {
            @Override
            public void onResponse(Call<FirebaseLoginAPIResponse> call, Response<FirebaseLoginAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onFirebaseLoginCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<FirebaseLoginAPIResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
