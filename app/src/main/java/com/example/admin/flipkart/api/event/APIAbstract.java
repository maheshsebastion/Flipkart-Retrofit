package com.example.admin.flipkart.api.event;

import com.example.admin.flipkart.api.remote.APIInterface;
import com.example.admin.flipkart.api.util.APIUtil;

/**
 * Created by Admin on 28-08-2017.
 */

public class APIAbstract {

    static APIInterface apiInterface = APIUtil.getAPI();

}
