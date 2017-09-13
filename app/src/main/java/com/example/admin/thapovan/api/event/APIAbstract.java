package com.example.admin.thapovan.api.event;

import com.example.admin.thapovan.api.remote.APIInterface;
import com.example.admin.thapovan.api.util.APIUtil;

/**
 * Created by Admin on 28-08-2017.
 */

public class APIAbstract {

    static APIInterface apiInterface = APIUtil.getAPI();

}
