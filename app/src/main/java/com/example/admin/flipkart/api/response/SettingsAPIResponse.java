package com.example.admin.flipkart.api.response;

import com.example.admin.flipkart.models.allcategorylist.Category;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class SettingsAPIResponse {

    private ArrayList<Category> category;

    public ArrayList<Category> getCategory(){
        return category;
    }
}
