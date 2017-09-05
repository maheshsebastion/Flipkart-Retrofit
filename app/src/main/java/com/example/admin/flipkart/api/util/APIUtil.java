package com.example.admin.flipkart.api.util;

import com.example.admin.flipkart.api.remote.APIClient;
import com.example.admin.flipkart.api.remote.APIInterface;

/**
 * Created by Admin on 22-08-2017.
 */

public class APIUtil {

    public static final String KEY_POSITION = "position";
    public static final String KEY_PRODUCTS = "products";
    public static final String STORED_ITEMS = "list of items";
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_FROM_BRAND = "from BrandActivity";
    public static final String SOURCE_FROM_MAIN = "from MainActivity";
    public static final String SOURCE_FROM_AllCATEGORY= "from AllCategoryActivity";


    public static final String IMAGE_URL = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";
    public static final String BASE_URL = "http://192.168.1.73";
    public static final String API_PRODUCT = "/projects/learning/laravel/e-commerce-portal/api/v1/getProducts";
    public static final String API_SETTINGS = "/projects/learning/laravel/e-commerce-portal/api/v1/getSettings";


    public static APIInterface getAPI(){
        return APIClient.getClient(BASE_URL).create(APIInterface.class);

    }
}
