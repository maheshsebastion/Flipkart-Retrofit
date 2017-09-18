package com.example.admin.flipkart.api.request.wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 15-09-2017.
 */

public class WishlistAddRequest {

    @Expose
    @SerializedName("user_id")
    public int user_id;

    @Expose
    @SerializedName("product_id")
    public int product_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
