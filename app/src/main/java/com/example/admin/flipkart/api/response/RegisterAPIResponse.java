package com.example.admin.flipkart.api.response;

import com.example.admin.flipkart.models.User;

import java.util.ArrayList;

/**
 * Created by Admin on 05-09-2017.
 */

public class RegisterAPIResponse {

    private boolean success;
    private String message;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
