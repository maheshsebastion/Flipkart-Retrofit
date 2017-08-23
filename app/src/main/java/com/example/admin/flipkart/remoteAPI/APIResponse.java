package com.example.admin.flipkart.remoteAPI;

import android.util.Log;

import com.example.admin.flipkart.models.allcategorylist.Category;
import com.example.admin.flipkart.models.products.Products;

/**
 * Created by Admin on 17-08-2017.
 */

public class APIResponse {

    private Products[] products;
    private Category[] category;

    public Products[] getProducts(){
        return products;
    }

    public Category[] getCategory(){
        return category;
    }

}
