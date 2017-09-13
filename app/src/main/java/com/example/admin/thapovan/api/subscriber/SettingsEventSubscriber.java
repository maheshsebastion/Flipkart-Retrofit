package com.example.admin.thapovan.api.subscriber;

import com.example.admin.thapovan.api.response.SettingsAPIResponse;

/**
 * Created by Admin on 28-08-2017.
 */

public interface SettingsEventSubscriber {

    void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse);

}
