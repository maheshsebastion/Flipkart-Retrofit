package com.example.admin.thapovan.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 13-09-2017.
 */

public class FireBaseUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private Object mobileNumber;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Object mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
