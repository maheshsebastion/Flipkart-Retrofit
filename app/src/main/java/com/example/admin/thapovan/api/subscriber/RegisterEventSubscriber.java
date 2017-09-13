package com.example.admin.thapovan.api.subscriber;

import com.example.admin.thapovan.api.response.RegisterAPIResponse;

/**
 * Created by Admin on 05-09-2017.
 */

public interface RegisterEventSubscriber {

    void onRegisterationCompleted(RegisterAPIResponse registerAPIResponse);
}
