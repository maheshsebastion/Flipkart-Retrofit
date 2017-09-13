package com.example.admin.thapovan.api.response;

import com.example.admin.thapovan.models.Category;
import com.example.admin.thapovan.models.Brand;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class SettingsAPIResponse {

    private boolean success;
    private String message;
    private ArrayList<Category> category;
    private ArrayList<Brand> brand;

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
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

    public ArrayList<Brand> getBrand() {
        return brand;
    }

    public void setBrand(ArrayList<Brand> brand) {
        this.brand = brand;
    }
}