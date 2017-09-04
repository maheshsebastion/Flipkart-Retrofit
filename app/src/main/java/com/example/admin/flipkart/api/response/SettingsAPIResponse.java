package com.example.admin.flipkart.api.response;

import com.example.admin.flipkart.models.allcategorylist.Category;
import com.example.admin.flipkart.models.brand.Brand;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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