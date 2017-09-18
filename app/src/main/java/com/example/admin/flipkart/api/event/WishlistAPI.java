package com.example.admin.flipkart.api.event;

import android.util.Log;

import com.example.admin.flipkart.api.request.wishlist.WishlistAddRequest;
import com.example.admin.flipkart.api.request.wishlist.WishlistRemoveRequest;
import com.example.admin.flipkart.api.response.wishlist.WishlistAddAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistGetAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistRemoveAPIResponse;
import com.example.admin.flipkart.api.subscriber.WishlistEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 15-09-2017.
 */

public class WishlistAPI extends APIAbstract {
    public WishlistAPI() {
    }

    public static void postAddToWishlist(WishlistAddRequest request, final WishlistEventSubscriber subscriber) {

        apiInterface.postAddToWishlist(request).enqueue(new Callback<WishlistAddAPIResponse>() {
            @Override
            public void onResponse(Call<WishlistAddAPIResponse> call, Response<WishlistAddAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onWishlistAddCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistAddAPIResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
    public static void postRemoveFromWishlist(WishlistRemoveRequest request, final WishlistEventSubscriber subscriber) {

        apiInterface.postRemoveFromWishlist(request).enqueue(new Callback<WishlistRemoveAPIResponse>() {
            @Override
            public void onResponse(Call<WishlistRemoveAPIResponse> call, Response<WishlistRemoveAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onWishlistRemoveCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistRemoveAPIResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
    public static void getWishlist(int userId, final WishlistEventSubscriber subscriber) {

        apiInterface.getWishlist(userId).enqueue(new Callback<WishlistGetAPIResponse>() {
            @Override
            public void onResponse(Call<WishlistGetAPIResponse> call, Response<WishlistGetAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onWishlistGetCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response21!");
//                    subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistGetAPIResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
//                subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}