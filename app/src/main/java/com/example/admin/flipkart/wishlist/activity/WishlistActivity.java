package com.example.admin.flipkart.wishlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.request.wishlist.WishlistAddRequest;
import com.example.admin.flipkart.api.request.wishlist.WishlistRemoveRequest;
import com.example.admin.flipkart.api.response.wishlist.WishlistAddAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistGetAPIResponse;
import com.example.admin.flipkart.api.response.wishlist.WishlistRemoveAPIResponse;
import com.example.admin.flipkart.api.subscriber.WishlistEventSubscriber;
import com.example.admin.flipkart.api.util.CommunicationManager;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.cart.activity.CartActivity;
import com.example.admin.flipkart.login.SessionManager;
import com.example.admin.flipkart.models.Products;
import com.example.admin.flipkart.models.User;
import com.example.admin.flipkart.product.activity.ProductDescriptionActivity;
import com.example.admin.flipkart.wishlist.adapter.AdapterWishlist;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishlistActivity extends AppActivity implements WishlistEventSubscriber{

    @BindView(R.id.nav_action)  Toolbar mToolbar;
    @BindView(R.id.recycle)     RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;

    int removedProductPosition;

    AdapterWishlist adapterWishlist;

    //Products model is commonly used for Both the CartList and wishList so we use cartList commonly for both
    ArrayList<Products> wishList;

    WishlistActivity mActivity;

    SessionManager sessionManager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_cart);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());
        gson = new Gson();

        //allocating the JSON to the "User" model and accessing it through the created OBJECT
        final User loginuserdata = gson.fromJson(sessionManager.getStoredJSON(),User.class);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.wishlist);
        //setting up the BACK ARROW in ACTION BAR
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        wishList = new ArrayList<>();

        adapterWishlist = new AdapterWishlist(mActivity, wishList, new AdapterWishlist.MyWishlistClickListener() {

            @Override
            public void onRemoveFromWishlist(int position) {
                if (sessionManager.isLoggedIn()){

                    removedProductPosition = position;

                    WishlistRemoveRequest wishlistRemoveRequest = new WishlistRemoveRequest();

                    wishlistRemoveRequest.setProduct_id(wishList.get(position).getId());
                    wishlistRemoveRequest.setUser_id(loginuserdata.getId());

                    showProgress();
                    //API Call Through CommunicationManager Class------>
                    CommunicationManager.getInstance().postRemoveFromWishlist(wishlistRemoveRequest, mActivity);
                }else {
                    ToastUtil.showCenterToast(getApplicationContext(),"Please Login first.....");
                    sessionManager.checkLogin();
                }

            }

            @Override
            public void onWishlistClicked(View view, int position) {
                ProductDescriptionActivity.start(mActivity, wishList.get(position));

            }
        });
            linearLayoutManager = new LinearLayoutManager(mActivity);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapterWishlist);

        if (sessionManager.isLoggedIn()){
            showProgress();
            CommunicationManager.getInstance().getWishlist(loginuserdata.getId(),mActivity);
        }else {
            ToastUtil.showCenterToast(getApplicationContext(),"Your cart is empty");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Wishlist
    @Override
    public void onWishlistGetCompleted(WishlistGetAPIResponse wishlistGetAPIResponse) {
        hideProgress();

        if (wishlistGetAPIResponse.isSuccess()){

            wishList.addAll(wishlistGetAPIResponse.getProducts());
            adapterWishlist.notifyDataSetChanged();

            ToastUtil.showCenterToast(getApplicationContext(), wishlistGetAPIResponse.getMessage());
        }else {

            ToastUtil.showCenterToast(getApplicationContext(), wishlistGetAPIResponse.getMessage());
        }
    }

    @Override
    public void onWishlistAddCompleted(WishlistAddAPIResponse wishlistAddAPIResponse) {

    }

    @Override
    public void onWishlistRemoveCompleted(WishlistRemoveAPIResponse wishlistRemoveAPIResponse) {
        hideProgress();

        if (wishlistRemoveAPIResponse.isSuccess()){

            wishList.remove(removedProductPosition);
            adapterWishlist.notifyDataSetChanged();
            ToastUtil.showCenterToast(getApplicationContext(), wishlistRemoveAPIResponse.getMessage());
        }else {

            ToastUtil.showCenterToast(getApplicationContext(), wishlistRemoveAPIResponse.getMessage());
        }

    }
}
