package com.example.admin.flipkart.models.allcategorylist;

import com.example.admin.flipkart.rest.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 23-08-2017.
 */

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @SerializedName("icon_name")
    @Expose
    private Object iconName;
    @SerializedName("icon_path")
    @Expose
    private Object iconPath;
    @SerializedName("image_name")
    @Expose
    private Object imageName;
    @SerializedName("image_path")
    @Expose
    private Object imagePath;


    @SerializedName("categoryChildren")
    @Expose
    private List<CategoryChild> categoryChildren = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Object getIconName() {
        return iconName;
    }

    public void setIconName(Object iconName) {
        this.iconName = iconName;
    }

    public Object getIconPath() {
        return iconPath;
    }

    //getting the Icon URL
    public String getIconUrl() {

        return Constants.IMAGE_URL+this.getIconPath()+"/"+this.getIconName();
    }

    public void setIconPath(Object iconPath) {
        this.iconPath = iconPath;
    }

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public List<CategoryChild> getCategoryChildren() {
        return categoryChildren;
    }

    public void setCategoryChildren(List<CategoryChild> categoryChildren) {
        this.categoryChildren = categoryChildren;
    }


}