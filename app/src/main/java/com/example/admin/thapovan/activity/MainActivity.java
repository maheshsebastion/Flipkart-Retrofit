package com.example.admin.thapovan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.thapovan.R;
import com.example.admin.thapovan.api.util.APIUtil;
import com.example.admin.thapovan.app.AppActivity;
import com.example.admin.thapovan.brand.activity.BrandActivity;
import com.example.admin.thapovan.firebase.FirebaseActivity;
import com.example.admin.thapovan.firebase.UserProfileActivity;
import com.example.admin.thapovan.login.SessionManager;
import com.example.admin.thapovan.models.User;
import com.example.admin.thapovan.product.activity.ProductActivity;
import com.example.admin.thapovan.category.activity.AllCategoryActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.toast.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppActivity {

    @BindView(R.id.main_drawerLayout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_action)        Toolbar mToolbar;
    //used for set button text as LOGIN and LOGOUT
    @BindView(R.id.tvLogin)           TextView tvLogin;
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

        if (sessionManager.isLoggedIn()){
            ToastUtil.showCenterToast(getApplicationContext(),"Welcome Mr/Mrs : "+loginuserdata.getName());
            tvLogin.setText("Logout");
        }else {
            ToastUtil.showCenterToast(getApplicationContext(),"Please Login to your account....");
            tvLogin.setText("Login");
        }

    //***************************************SESSION CODE ENDS*******************************************
    }



    //setting onClickListener for TEXTVIEW more
    @OnClick(R.id.tv_more)
    public void onMoreClicked(View v){
        Intent intent = new Intent(v.getContext(),AllCategoryActivity.class);
        startActivity(intent);
    }

    //setting onClickListener for View All Button
    @OnClick(R.id.viewall)
    public void onViewAllClicked(View v){
        Intent intent = new Intent(v.getContext(),ProductActivity.class);
        intent.setClass(v.getContext(),ProductActivity.class);
        intent.putExtra(APIUtil.KEY_SOURCE,APIUtil.SOURCE_FROM_MAIN);
        startActivity(intent);
    }

    //setting onClickListener for Login TextView
    @OnClick(R.id.tvLogin)
    public void onLoginClicked(View v){
        if (sessionManager.isLoggedIn()){

            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
//                                FirebaseActivity.start(MainActivity.this);
                                finish();
                            } else {
//                                showSnackbar(R.string.sign_out_failed);
                            }
                        }
                    });
            sessionManager.logoutUser();
            finish();
        }
        else{
            sessionManager.checkLogin();
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

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        if (sessionManager.isLoggedIn()){

            tvLogin.setText("Logout");
        }else {

            tvLogin.setText("Login");
        }
    }
}
