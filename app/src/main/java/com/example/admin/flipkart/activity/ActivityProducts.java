package com.example.admin.flipkart.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.flipkart.adapter.AdapterRecyclerView;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.products.Products;
import com.example.admin.flipkart.remoteAPI.APIClient;
import com.example.admin.flipkart.remoteAPI.APIResponse;
import com.example.admin.flipkart.remoteAPI.APIService;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProducts extends AppCompatActivity {

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

        //Code for Retrofit Written in APIClient
        APIService service = APIClient.getClient().create(APIService.class);
        Call<APIResponse> call=   service.getProducts();
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                APIResponse apiResponse = response.body();
                //pList--->Product List
                pList = new ArrayList<>(Arrays.asList(apiResponse.getProducts()));

                AdapterRecyclerView recyclerAdapter = new AdapterRecyclerView(getApplicationContext(), pList);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(ActivityProducts.this,2);

                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });
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
}
