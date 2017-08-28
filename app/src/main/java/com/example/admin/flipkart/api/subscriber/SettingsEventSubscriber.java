package com.example.admin.flipkart.api.subscriber;

import com.example.admin.flipkart.api.response.SettingsAPIResponse;

/**
 * Created by Admin on 28-08-2017.
 */

public interface SettingsEventSubscriber {

    void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse);

}
