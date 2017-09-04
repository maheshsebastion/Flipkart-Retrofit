package com.example.admin.flipkart.product.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.product.adapter.AdapterListProduct;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.event.ProductAPI;
import com.example.admin.flipkart.api.subscriber.ProductEventSubscriber;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.models.products.Products;
import com.example.admin.flipkart.api.response.ProductAPIResponse;

import java.util.ArrayList;

public class ProductActivity extends AppActivity implements ProductEventSubscriber {

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
        getSupportActionBar().setTitle(R.string.product);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        //Retrofit used in subscriber (Implement the appropriate event subscriber and implent the methods) the methods are given below as public void onProductCompleted(ProductAPIResponse productAPIResponse)
        //write the appropriate code inside it

        String KEY_SOURCE = getIntent().getExtras().getString(APIUtil.KEY_SOURCE);

        if(KEY_SOURCE.equals(APIUtil.SOURCE_FROM_MAIN)){

            showProgress();
            ProductAPI.get(this);
        }
        else if (KEY_SOURCE.equals(APIUtil.SOURCE_FROM_AllCATEGORY)){

            int categoryId = getIntent().getIntExtra(APIUtil.KEY_POSITION,0);
            String categoryName = getIntent().getStringExtra(APIUtil.STORED_ITEMS);
            getSupportActionBar().setTitle(categoryName);
            showProgress();
            ProductAPI.getProductCategory(categoryId,this);
        }
        else if (KEY_SOURCE.equals(APIUtil.SOURCE_FROM_BRAND)){

            int brandId = getIntent().getIntExtra(APIUtil.KEY_POSITION,0);
            String brandName = getIntent().getStringExtra(APIUtil.STORED_ITEMS);
            getSupportActionBar().setTitle(brandName);
            showProgress();
            ProductAPI.getProductBrand(brandId,this);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
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
        hideProgress();
        if(productAPIResponse.isSuccess()) {
            pList = new ArrayList<Products>(productAPIResponse.getProducts());

            AdapterListProduct recyclerAdapter = new AdapterListProduct(getApplicationContext(), pList);
            RecyclerView.LayoutManager recyce = new GridLayoutManager(ProductActivity.this, 2);

            recyclerView.setLayoutManager(recyce);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(recyclerAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), productAPIResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
