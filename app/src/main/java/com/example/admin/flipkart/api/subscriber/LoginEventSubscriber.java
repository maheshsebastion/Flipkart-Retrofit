package com.example.admin.flipkart.api.subscriber;

import com.example.admin.flipkart.api.response.LoginAPIResponse;

/**
 * Created by Admin on 05-09-2017.
 */

public interface LoginEventSubscriber {

    void onLoginCompleted(LoginAPIResponse loginAPIResponse);
}
