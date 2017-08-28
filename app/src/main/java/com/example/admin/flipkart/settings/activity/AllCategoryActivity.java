package com.example.admin.flipkart.settings.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.settings.adapter.AdapterAllCategory;
import com.example.admin.flipkart.api.event.SettingsAPI;
import com.example.admin.flipkart.api.subscriber.SettingsEventSubscriber;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.models.allcategorylist.Category;
import com.example.admin.flipkart.api.response.SettingsAPIResponse;

import java.util.ArrayList;

public class AllCategoryActivity extends AppCompatActivity implements SettingsEventSubscriber {

    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    AdapterAllCategory adapterAllCategory;
    ExpandableListView expandableListView;
    ArrayList<Category> cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_all_category);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.category_drawerLayout);
        mToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Categories");

        expandableListView = (ExpandableListView) findViewById(R.id.categoryELV);

        //Retrofit used in subscriber (Implement the appropriate event subscriber and implent the methods) the methods are given below as public void onSettingsCompleted(SettingsAPIResponse settingsAPIResponse)
        //write the appropriate code inside it
        APIUtil.getAPI();
        SettingsAPI.get(this);

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

    }
}
