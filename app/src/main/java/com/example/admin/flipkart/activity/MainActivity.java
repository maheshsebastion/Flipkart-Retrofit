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
import com.example.admin.flipkart.login.SessionManager;
import com.example.admin.flipkart.models.User;
import com.example.admin.flipkart.product.activity.ProductActivity;
import com.example.admin.flipkart.category.activity.AllCategoryActivity;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppActivity {

    @BindView(R.id.main_drawerLayout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_action)        Toolbar mToolbar;
    //used for set button text as LOGIN and LOGOUT
    @BindView(R.id.btnLogin)          Button btnLogin;
    @BindView(R.id.nav_view)          NavigationView navigationView;

    private android.support.v7.app.ActionBarDrawerToggle mToggle;

    SessionManager sessionManager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        //declaraton for SHARED PREFERENCE
        sessionManager = new SessionManager(getApplicationContext());
        gson = new Gson();

        //declarations for THIS ACTIVITY
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.main);

        mToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

//providing action with on click listnener for items in navigation drawer
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

    //***************************************SESSION CODE*******************************************

//        HashMap<String,String> user = sessionManager.getUserDetails();

        //allocating the JSON to the "User" model and accessing it through the created OBJECT
        User loginuserdata = gson.fromJson(sessionManager.gettingstoredJSON(),User.class);

        if (sessionManager.isLoggedIn() == false){
            ToastUtil.showCenterToast(getApplicationContext(),"Please Login to your account....");
            btnLogin.setText("Login");
        }else {
            ToastUtil.showCenterToast(getApplicationContext(),"Your Name is : "+loginuserdata.getName());
            btnLogin.setText("Logout");
        }

    //***************************************SESSION CODE ENDS*******************************************
    }

    //setting onClickListener for TEXTVIEW more
    @OnClick(R.id.tv_more)
    public void more(View v){
        Intent intent = new Intent(v.getContext(),AllCategoryActivity.class);
        startActivity(intent);
    }

    //setting onClickListener for View All Button
    @OnClick(R.id.viewall)
    public void viewAll(View v){
        Intent intent = new Intent(v.getContext(),ProductActivity.class);
        intent.setClass(v.getContext(),ProductActivity.class);
        intent.putExtra(APIUtil.KEY_SOURCE,APIUtil.SOURCE_FROM_MAIN);
        startActivity(intent);
    }

    //setting onClickListener for Login Button
    @OnClick(R.id.btnLogin)
    public void login(View v){
        if (sessionManager.isLoggedIn() == false){

            sessionManager.checkLogin();
        }
        else{
            sessionManager.logoutUser();
            finish();
        }
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
