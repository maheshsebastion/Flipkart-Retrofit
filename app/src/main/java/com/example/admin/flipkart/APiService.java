package com.example.admin.flipkart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 14-08-2017.
 */

public interface APiService {

    @GET("/maheshsebastion/7d976a8a7c8a223489d263f2e4740b09/raw/aaec751692b30bfca21deaa699b3be3329ff9c4f/Products.txt")
    Call<APIResponse> getJSON();

}
