package com.example.admin.thapovan.api.util;

import android.app.Activity;

import com.example.admin.thapovan.api.event.FirebaseLoginAPI;
import com.example.admin.thapovan.api.event.LoginAPI;
import com.example.admin.thapovan.api.event.ProductAPI;
import com.example.admin.thapovan.api.event.RegisterAPI;
import com.example.admin.thapovan.api.event.SettingsAPI;
import com.example.admin.thapovan.api.subscriber.FirebaseLoginEventSubscriber;
import com.example.admin.thapovan.api.subscriber.LoginEventSubscriber;
import com.example.admin.thapovan.api.subscriber.ProductEventSubscriber;
import com.example.admin.thapovan.api.subscriber.RegisterEventSubscriber;
import com.example.admin.thapovan.api.subscriber.SettingsEventSubscriber;
import com.example.admin.thapovan.request.FirebaseLoginRequest;
import com.example.admin.thapovan.request.LoginRequest;
import com.example.admin.thapovan.request.RegistrationRequest;

/**
 * Created by Admin on 04-09-2017.
 */

public class CommunicationManager {
    private static final CommunicationManager ourInstance = new CommunicationManager();

    public static CommunicationManager getInstance() {
        return ourInstance;
    }

    private CommunicationManager() {
    }
    public void getProducts(int page, Activity activity){

        ProductAPI.getProducts(page, (ProductEventSubscriber) activity);

    }
    public void getProductCategory(Activity activity,int categoryId){

        ProductAPI.getProductCategory(categoryId,(ProductEventSubscriber) activity);

    }
    public void getProductBrand(Activity activity,int brandId){

        ProductAPI.getProductBrand(brandId,(ProductEventSubscriber) activity);

    }
    public void getCategoryListByBrand(Activity activity){

        SettingsAPI.getCategoryListByBrand((SettingsEventSubscriber) activity);

    }
    public void getCategories(Activity activity){

        SettingsAPI.getCategories((SettingsEventSubscriber) activity);

    }
    public void postRegisterDetails(RegistrationRequest registrationRequest,Activity activity){

        RegisterAPI.postRegisterDetails(registrationRequest, (RegisterEventSubscriber) activity);

    }
    public void postLoginDetails(LoginRequest loginRequest,Activity activity){

        LoginAPI.postLoginDetails(loginRequest, (LoginEventSubscriber) activity);

    }
    public void postFirebaseLoginDetails(FirebaseLoginRequest firebaseLoginRequest, Activity activity){

        FirebaseLoginAPI.postFirebaseLoginDetails(firebaseLoginRequest, (FirebaseLoginEventSubscriber) activity);

    }
}
