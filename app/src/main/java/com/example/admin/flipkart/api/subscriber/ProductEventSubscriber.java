package com.example.admin.flipkart.api.subscriber;

import com.example.admin.flipkart.api.response.ProductAPIResponse;

/**
 * Created by Admin on 28-08-2017.
 */

public interface ProductEventSubscriber {

    void onProductCompleted(ProductAPIResponse productAPIResponse);

}
