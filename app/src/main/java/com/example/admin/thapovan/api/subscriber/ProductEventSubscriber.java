package com.example.admin.thapovan.api.subscriber;

import com.example.admin.thapovan.api.response.ProductAPIResponse;

/**
 * Created by Admin on 28-08-2017.
 */

public interface ProductEventSubscriber {

    void onProductCompleted(ProductAPIResponse productAPIResponse);

}
