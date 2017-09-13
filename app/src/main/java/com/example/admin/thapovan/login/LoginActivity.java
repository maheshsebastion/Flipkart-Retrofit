package com.example.admin.thapovan.login;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.admin.thapovan.R;
import com.example.admin.thapovan.api.response.LoginAPIResponse;
import com.example.admin.thapovan.api.subscriber.LoginEventSubscriber;
import com.example.admin.thapovan.api.util.CommunicationManager;
import com.example.admin.thapovan.app.AppActivity;
import com.example.admin.thapovan.firebase.FirebaseActivity;
import com.example.admin.thapovan.models.User;
import com.example.admin.thapovan.request.LoginRequest;
import com.thapovan.android.commonutils.toast.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppActivity implements LoginEventSubscriber {

    @BindView(R.id.email)    EditText editTextEmail;
    @BindView(R.id.password) EditText editTextPassword;

    LoginActivity mActivity;

    User user = new User();

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_login);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

    }

    //Onclick for FIREBASE button
    @OnClick(R.id.email_firebase_sign_in_button)
    public void onFireBaseBtnClicked(){
        Intent intent = new Intent(getApplicationContext(),FirebaseActivity.class);
        startActivity(intent);
        finish();
    }

    //Onclick for REGISTER BUTTON
    @OnClick(R.id.register)
    public void onRegisterBTN(){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }

    //Onclick for LOGIN BUTTON
    @OnClick(R.id.email_sign_in_button)
    public void onloginBTN(){
        String editEmail = editTextEmail.getText().toString();
        String editPassword = editTextPassword.getText().toString();

        String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!editEmail.matches(emailPattern) || editPassword.length()<6 || TextUtils.isEmpty(editEmail) || TextUtils.isEmpty(editPassword)){

            if (!editEmail.matches(emailPattern)) {
                editTextEmail.setError(getString(R.string.error_email_mismatch));
            }
            if(editPassword.length()<6) {
                editTextPassword.setError(getString(R.string.error_password_size));
            }
            //Checking fields are empty
            if (TextUtils.isEmpty(editEmail)){
                editTextEmail.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(editPassword)){
                editTextPassword.setError(getString(R.string.error_field_required));
            }

        }else {

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(editEmail);
            loginRequest.setPassword(editPassword);

            showProgress();
            //API Call Through CommunicationManager Class------>
            CommunicationManager.getInstance().postLoginDetails(loginRequest,mActivity);
        }
    }

    @Override
    public void onLoginCompleted(LoginAPIResponse loginAPIResponse) {

        hideProgress();

        if (loginAPIResponse.isSuccess()){

            user = loginAPIResponse.getUser();
            //Putting datas to the shared preference
            sessionManager.createLoginSession(user);

            ToastUtil.showCenterToast(getApplicationContext(),loginAPIResponse.getMessage());

            finish();

        }else {

            ToastUtil.showCenterToast(getApplicationContext(),loginAPIResponse.getMessage());

        }

    }
}


