package com.example.admin.flipkart.api.subscriber;

import com.example.admin.flipkart.api.response.wishlist.WishlistAddPostResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistGetResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistRemovePostResponse;

/**
 * Created by Admin on 15-09-2017.
 */

public interface WishlistEventSubscriber {

    void onWishlistGetCompleted(WishlistGetResponse wishlistGetResponse);
    void onWishlistAddCompleted(WishlistAddPostResponse wishlistAddPostResponse);
    void onWishlistRemoveCompleted(WishlistRemovePostResponse wishlistRemovePostResponse);
}
