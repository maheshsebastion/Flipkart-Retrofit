package com.example.admin.flipkart.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.brand.activity.BrandActivity;
import com.example.admin.flipkart.login.LoginActivity;
import com.example.admin.flipkart.product.activity.ProductActivity;
import com.example.admin.flipkart.category.activity.AllCategoryActivity;
import com.thapovan.android.commonutils.toast.ToastUtil;

public class MainActivity extends AppActivity {

    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    Button viewall,btnLogin;
    TextView more;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawerLayout);
        mToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.main);

//providing action with on click listnener for items in navigation drawer
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.electronics:
                        intent = new Intent(getApplicationContext(), AllCategoryActivity.class);
                        startActivity(intent);
                        ToastUtil.showToast(getApplicationContext(), "electronics Selected");
                        break;
                    case R.id.brands:
                        intent = new Intent(getApplicationContext(), BrandActivity.class);
                        startActivity(intent);
                        ToastUtil.showToast(getApplicationContext(), "brand Selected");
                        break;
                    case R.id.fashion:
                        ToastUtil.showToast(getApplicationContext(), "fashion Selected");
                        break;
                    case R.id.home:
                        ToastUtil.showToast(getApplicationContext(), "home & furniture Selected");
                        break;
                }
                return true;
            }
        });

        //setting onClickListener for View All Button
        viewall = (Button) findViewById(R.id.viewall);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProductActivity.class);
                intent.setClass(v.getContext(),ProductActivity.class);
                intent.putExtra(APIUtil.KEY_SOURCE,APIUtil.SOURCE_FROM_MAIN);
                startActivity(intent);
            }
        });

        //setting onClickListener for Login Button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //setting onClickListener for TEXTVIEW more
        more = (TextView) findViewById(R.id.tv_more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AllCategoryActivity.class);
                startActivity(intent);
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
    public boolean onOptionsItemSelected(MenuItem item){

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
