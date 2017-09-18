package com.example.admin.flipkart.api.subscriber;

import com.example.admin.flipkart.api.response.wishlist.WishlistAddAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistGetAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistRemoveAPIResponse;

/**
 * Created by Admin on 15-09-2017.
 */

public interface WishlistEventSubscriber {

    void onWishlistGetCompleted(WishlistGetAPIResponse wishlistGetAPIResponse);
    void onWishlistAddCompleted(WishlistAddAPIResponse wishlistAddAPIResponse);
    void onWishlistRemoveCompleted(WishlistRemoveAPIResponse wishlistRemoveAPIResponse);
}
