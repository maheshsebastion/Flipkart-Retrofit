package com.example.admin.flipkart.category.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.api.util.CommunicationManager;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.category.adapter.AdapterAllCategory;
import com.example.admin.flipkart.api.subscriber.SettingsEventSubscriber;
import com.example.admin.flipkart.models.Category;
import com.example.admin.flipkart.api.response.SettingsAPIResponse;
import com.example.admin.flipkart.product.activity.ProductActivity;

import java.util.ArrayList;

public class AllCategoryActivity extends AppActivity implements SettingsEventSubscriber {

    AllCategoryActivity mActivity;

    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    AdapterAllCategory adapterAllCategory;
    ExpandableListView expandableListView;
    ArrayList<Category> cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_all_category);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.category_drawerLayout);
        mToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.category);

        //Retrofit used in subscriber (Implement the appropriate event subscriber and implement the methods) the methods are given below as public void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse)
        //write the appropriate code inside it
        showProgress();
        CommunicationManager.getInstance().getCategories(mActivity);

        expandableListView = (ExpandableListView) findViewById(R.id.categoryELV);

    }    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout_menu_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse) {

        cList = new ArrayList<Category> (settingsAPIResponse.getCategory());
        adapterAllCategory = new AdapterAllCategory(AllCategoryActivity.this,cList);
        expandableListView.setAdapter(adapterAllCategory);
        hideProgress();
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int categoryListPosition = cList.get(groupPosition).getChildren().get(childPosition).getId();
                String categoryName = cList.get(groupPosition).getChildren().get(childPosition).getName();
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.setClass(v.getContext(), ProductActivity.class);
                intent.putExtra(APIUtil.KEY_POSITION,categoryListPosition);
                intent.putExtra(APIUtil.STORED_ITEMS,categoryName);
                intent.putExtra(APIUtil.KEY_SOURCE,APIUtil.SOURCE_FROM_AllCATEGORY);
                startActivity(intent);
                return false;
            }
        });

    }
}
