package com.example.admin.thapovan.api.response;

import com.example.admin.thapovan.models.FireBaseUser;

/**
 * Created by Admin on 13-09-2017.
 */

public class FirebaseLoginAPIResponse {


    /**
     * success : true
     * message : Login Success...
     * user : {"id":14,"name":"Kar twi","email":"karthi123@gmail.com","mobile_number":null}
     */

    private boolean success;
    private String message;
    private FireBaseUser user;

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

    public FireBaseUser getFirebaseUser() {
        return user;
    }

    public void setFirebaseUser(FireBaseUser user) {
        this.user = user;
    }
}
