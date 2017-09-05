package com.example.admin.flipkart.brand.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.event.SettingsAPI;
import com.example.admin.flipkart.api.response.SettingsAPIResponse;
import com.example.admin.flipkart.api.subscriber.SettingsEventSubscriber;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.api.util.CommunicationManager;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.brand.adapter.AdapterBrand;
import com.example.admin.flipkart.models.brand.Brand;
import com.example.admin.flipkart.product.activity.ProductActivity;

import java.util.ArrayList;

public class BrandActivity extends AppActivity implements SettingsEventSubscriber {

    BrandActivity mActivity;

    Toolbar toolbar;
    ListView blistView;
    ArrayList<Brand> brandArrayList = new ArrayList<>();
    AdapterBrand adapterBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_brand);

        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.brand);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        showProgress();
        CommunicationManager.getInstance().getCategoryListByBrand(mActivity);
        blistView = (ListView) findViewById(R.id.brandLV);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse) {

        brandArrayList = settingsAPIResponse.getBrand();
        adapterBrand = new AdapterBrand(brandArrayList);
        blistView.setAdapter(adapterBrand);
        hideProgress();
        blistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int brandListPosition = brandArrayList.get(position).getId();
                String brandName = brandArrayList.get(position).getName();
                Intent intent = new Intent(view.getContext(), ProductActivity.class);
                intent.setClass(view.getContext(), ProductActivity.class);
                intent.putExtra(APIUtil.KEY_POSITION,brandListPosition);
                intent.putExtra(APIUtil.STORED_ITEMS,brandName);
                intent.putExtra(APIUtil.KEY_SOURCE,APIUtil.SOURCE_FROM_BRAND);
                startActivity(intent);

            }
        });
    }
}
