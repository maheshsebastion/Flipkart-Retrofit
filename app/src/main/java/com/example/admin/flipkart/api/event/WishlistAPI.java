package com.example.admin.flipkart.api.event;

import android.util.Log;

import com.example.admin.flipkart.api.request.wishlist.WishlistAddRequest;
import com.example.admin.flipkart.api.request.wishlist.WishlistRemoveRequest;
import com.example.admin.flipkart.api.response.wishlist.WishlistAddPostResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistGetResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistRemovePostResponse;
import com.example.admin.flipkart.api.subscriber.WishlistEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.admin.flipkart.api.util.APIUtil.getGenericResponseErr;
import static com.example.admin.flipkart.api.util.APIUtil.processUnSuccessResponce;

/**
 * Created by Admin on 15-09-2017.
 */

public class WishlistAPI extends APIAbstract {
    public WishlistAPI() {
    }

    public static void postAddToWishlist(WishlistAddRequest request, final WishlistEventSubscriber subscriber) {

        apiInterface.postAddToWishlist(request).enqueue(new Callback<WishlistAddPostResponse>() {
            @Override
            public void onResponse(Call<WishlistAddPostResponse> call, Response<WishlistAddPostResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onWishlistAddCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");

                    subscriber.onWishlistAddCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishlistAddPostResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistAddPostResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
                subscriber.onWishlistAddCompleted(getGenericResponseErr(WishlistAddPostResponse.class, t ));
            }
        });
    }
    public static void postRemoveFromWishlist(WishlistRemoveRequest request, final WishlistEventSubscriber subscriber) {

        apiInterface.postRemoveFromWishlist(request).enqueue(new Callback<WishlistRemovePostResponse>() {
            @Override
            public void onResponse(Call<WishlistRemovePostResponse> call, Response<WishlistRemovePostResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Response!");
                    subscriber.onWishlistRemoveCompleted(response.body());
                } else {
                    Log.i("TAG", "No Response!");
                    subscriber.onWishlistRemoveCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishlistRemovePostResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistRemovePostResponse> call, Throwable t) {

                Log.i("TAG", "No Response!");
                subscriber.onWishlistRemoveCompleted(getGenericResponseErr(WishlistRemovePostResponse.class, t ));
            }
        });
    }
    public static void getWishlist(int userId, final WishlistEventSubscriber subscriber) {

        apiInterface.getWishlist(userId).enqueue(new Callback<WishlistGetResponse>() {
            @Override
            public void onResponse(Call<WishlistGetResponse> call, Response<WishlistGetResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Yes Get Response!");
                    subscriber.onWishlistGetCompleted(response.body());
                } else {
                    Log.i("TAG", "No Get Response21!");
                    subscriber.onWishlistGetCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishlistGetResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishlistGetResponse> call, Throwable t) {

                Log.i("TAG", "No Get Response!");
                subscriber.onWishlistGetCompleted(getGenericResponseErr(WishlistGetResponse.class, t ));
            }
        });
    }
}