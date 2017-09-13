package com.example.admin.thapovan.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 13-09-2017.
 */

public class FirebaseLoginRequest {

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("provider")
    public String provider;

    @Expose
    @SerializedName("provider_id")
    public String provider_id;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("token")
    public String token;

    @Expose
    @SerializedName("secret")
    public String secret;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
