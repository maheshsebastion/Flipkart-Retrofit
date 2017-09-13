package com.example.admin.thapovan.api.subscriber;

import com.example.admin.thapovan.api.response.LoginAPIResponse;

/**
 * Created by Admin on 05-09-2017.
 */

public interface LoginEventSubscriber {

    void onLoginCompleted(LoginAPIResponse loginAPIResponse);
}
