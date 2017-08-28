package com.example.admin.flipkart.product.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.flipkart.product.adapter.AdapterListProduct;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.event.ProductAPI;
import com.example.admin.flipkart.api.subscriber.ProductEventSubscriber;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.models.products.Products;
import com.example.admin.flipkart.api.response.ProductAPIResponse;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductEventSubscriber {

    private Toolbar mToolbar;

    RecyclerView recyclerView;
    ArrayList<Products> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_products);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Electronics");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        //Retrofit used in subscriber (Implement the appropriate event subscriber and implent the methods) the methods are given below as public void onProductCompleted(ProductAPIResponse productAPIResponse)
        //write the appropriate code inside it
        APIUtil.getAPI();
        ProductAPI.get(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout_product_menu_buttons, menu);
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

        pList = new ArrayList<Products> (productAPIResponse.getProducts());

        AdapterListProduct recyclerAdapter = new AdapterListProduct(getApplicationContext(), pList);
        RecyclerView.LayoutManager recyce = new GridLayoutManager(ProductActivity.this,2);

        recyclerView.setLayoutManager(recyce);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

    }
}
