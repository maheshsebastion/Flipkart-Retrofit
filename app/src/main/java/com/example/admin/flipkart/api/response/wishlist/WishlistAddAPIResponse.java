package com.example.admin.flipkart.api.response.wishlist;

/**
 * Created by Admin on 15-09-2017.
 */

public class WishlistAddAPIResponse {


    /**
     * success : true
     * message : Product added successfully
     */

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
