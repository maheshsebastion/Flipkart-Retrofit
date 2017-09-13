package com.example.admin.thapovan.api.subscriber;

import com.example.admin.thapovan.api.response.FirebaseLoginAPIResponse;

/**
 * Created by Admin on 13-09-2017.
 */

public interface FirebaseLoginEventSubscriber {

    void onFirebaseLoginCompleted(FirebaseLoginAPIResponse loginAPIResponse);
}
