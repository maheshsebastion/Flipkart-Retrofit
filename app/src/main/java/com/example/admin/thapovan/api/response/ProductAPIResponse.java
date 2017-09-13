package com.example.admin.thapovan.api.response;

import com.example.admin.thapovan.models.Products;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class ProductAPIResponse {

    private ArrayList<Products> products;
    /**
     * success : true
     * message : Product List
     */

    private boolean success;
    private String message;


    public ArrayList<Products> getProducts(){
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
