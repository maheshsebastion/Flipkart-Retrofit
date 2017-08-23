package com.example.admin.flipkart.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.adapter.AdapterAllCategories;
import com.example.admin.flipkart.models.allcategorylist.Category;
import com.example.admin.flipkart.remoteAPI.APIClient;
import com.example.admin.flipkart.remoteAPI.APIResponse;
import com.example.admin.flipkart.remoteAPI.APIService;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAllCategories extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    ListView listView;
    ArrayList<Category> cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_all_categories);

        //Code for Retrofit Written in APIClient
        APIService service1 = APIClient.getClient().create(APIService.class);
        Call<APIResponse> call=   service1.getCategory();
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                APIResponse apiResponse1 = response.body();
                //cList--->Category List
                cList = new ArrayList<>(Arrays.asList(apiResponse1.getCategory()));

                listView = (ListView) findViewById(R.id.allcategoriesLV);
                AdapterAllCategories adapter = new AdapterAllCategories(ActivityAllCategories.this, cList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(ActivityAllCategories.this);

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });


        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.category_drawerLayout);
        mToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Categories");


    }
    @Override
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "you have clicked in an item"+cList.get(position).getName(),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

    }
}
