package com.example.admin.flipkart.api.response;

import com.example.admin.flipkart.models.products.Products;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class ProductAPIResponse {

    private ArrayList<Products> products;

    public ArrayList<Products> getProducts(){
        return products;
    }

}
