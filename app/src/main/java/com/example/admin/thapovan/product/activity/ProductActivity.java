package com.example.admin.thapovan.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.thapovan.api.util.CommunicationManager;
import com.example.admin.thapovan.api.util.RecyclerViewInfiniteScrollListener;
import com.example.admin.thapovan.app.AppActivity;
import com.example.admin.thapovan.product.adapter.AdapterListProduct;
import com.example.admin.thapovan.R;
import com.example.admin.thapovan.api.subscriber.ProductEventSubscriber;
import com.example.admin.thapovan.api.util.APIUtil;
import com.example.admin.thapovan.models.Products;
import com.example.admin.thapovan.api.response.ProductAPIResponse;
import com.thapovan.android.commonutils.recyclerview.RecyclerTouchListener;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppActivity implements ProductEventSubscriber {

    @BindView(R.id.nav_action) Toolbar mToolbar;
    @BindView(R.id.recycle)    RecyclerView recyclerView;

    ProductActivity mActivity;

    ArrayList<Products> pList;

    AdapterListProduct recyclerAdapter;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_products);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.product);

        //setting up the BACK ARROW in ACTION BAR
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        //Retrofit used in subscriber (Implement the appropriate event subscriber and implent the methods) the methods are given below as public void onLoginCompleted(ProductAPIResponse productAPIResponse)
        //write the appropriate code inside it

        String KEY_SOURCE = getIntent().getExtras().getString(APIUtil.KEY_SOURCE);

        pList = new ArrayList<>();

        recyclerAdapter = new AdapterListProduct(mActivity, pList);
        linearLayoutManager = new GridLayoutManager(ProductActivity.this, 2);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);


            if(KEY_SOURCE.equals(APIUtil.SOURCE_FROM_MAIN)){

                int page = 1;
                showProgress();
                CommunicationManager.getInstance().getProducts(page, mActivity);
                recyclerView.addOnScrollListener(new RecyclerViewInfiniteScrollListener(linearLayoutManager) {
                    @Override
                    public void loadMore(int page) {
                        CommunicationManager.getInstance().getProducts(page, mActivity);
                    }
                });
            }

            if (KEY_SOURCE.equals(APIUtil.SOURCE_FROM_AllCATEGORY)){

                int categoryId = getIntent().getIntExtra(APIUtil.KEY_POSITION,0);
                String categoryName = getIntent().getStringExtra(APIUtil.STORED_ITEMS);
                getSupportActionBar().setTitle(categoryName);
                showProgress();
                CommunicationManager.getInstance().getProductCategory(mActivity,categoryId);
            }
            else if (KEY_SOURCE.equals(APIUtil.SOURCE_FROM_BRAND)){

                int brandId = getIntent().getIntExtra(APIUtil.KEY_POSITION,0);
                String brandName = getIntent().getStringExtra(APIUtil.STORED_ITEMS);
                getSupportActionBar().setTitle(brandName);
                showProgress();
                CommunicationManager.getInstance().getProductBrand(mActivity,brandId);
            }

        new RecyclerTouchListener(mActivity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(view.getContext(),ProductDescriptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(APIUtil.KEY_PRODUCTS,pList);
                intent.putExtra(APIUtil.KEY_POSITION,position);
                view.getContext().startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int i) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout_menu_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProductCompleted(ProductAPIResponse productAPIResponse) {
        hideProgress();
        if(productAPIResponse.isSuccess()) {
            pList.addAll(productAPIResponse.getProducts());
            recyclerAdapter.notifyDataSetChanged();

        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), productAPIResponse.getMessage());
//            DialogUtil.showAlert();
//            Toast.makeText(getApplicationContext(), productAPIResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
