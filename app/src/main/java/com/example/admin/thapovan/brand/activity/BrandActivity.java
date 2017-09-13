package com.example.admin.thapovan.brand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.thapovan.R;
import com.example.admin.thapovan.api.response.SettingsAPIResponse;
import com.example.admin.thapovan.api.subscriber.SettingsEventSubscriber;
import com.example.admin.thapovan.api.util.APIUtil;
import com.example.admin.thapovan.api.util.CommunicationManager;
import com.example.admin.thapovan.app.AppActivity;
import com.example.admin.thapovan.brand.adapter.AdapterBrand;
import com.example.admin.thapovan.models.Brand;
import com.example.admin.thapovan.product.activity.ProductActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandActivity extends AppActivity implements SettingsEventSubscriber {

    @BindView(R.id.nav_action) Toolbar toolbar;
    @BindView(R.id.brandLV)    ListView blistView;

    BrandActivity mActivity;

    ArrayList<Brand> brandArrayList = new ArrayList<>();
    AdapterBrand adapterBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_brand);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.brand);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        showProgress();
        CommunicationManager.getInstance().getCategoryListByBrand(mActivity);
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
