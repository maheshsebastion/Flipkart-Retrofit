package com.example.admin.flipkart.api.util;

import android.app.Activity;

import com.example.admin.flipkart.api.event.ProductAPI;
import com.example.admin.flipkart.api.event.SettingsAPI;
import com.example.admin.flipkart.api.subscriber.ProductEventSubscriber;
import com.example.admin.flipkart.api.subscriber.SettingsEventSubscriber;

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
    public void getProducts(Activity activity){

        ProductAPI.getProducts((ProductEventSubscriber) activity);

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
}
