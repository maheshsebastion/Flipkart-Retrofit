package com.example.admin.flipkart.login;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.activity.MainActivity;
import com.example.admin.flipkart.api.response.LoginAPIResponse;
import com.example.admin.flipkart.api.subscriber.LoginEventSubscriber;
import com.example.admin.flipkart.api.util.CommunicationManager;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.models.User;
import com.example.admin.flipkart.request.LoginRequest;
import com.thapovan.android.commonutils.toast.ToastUtil;

public class LoginActivity extends AppActivity implements LoginEventSubscriber {

    LoginActivity mActivity;

    EditText editTextEmail,editTextPassword;

    User user = new User();

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

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
        });
        TextView mRegister = (TextView) findViewById(R.id.register);
        mRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onLoginCompleted(LoginAPIResponse loginAPIResponse) {

        hideProgress();

        if (loginAPIResponse.isSuccess()){

            user = loginAPIResponse.getUser();
            //Putting datas to the shared preference
            sessionManager.createLoginSession(user.getName(),user.getEmail());

            ToastUtil.showCenterToast(getApplicationContext(),loginAPIResponse.getMessage());

            //Start Activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }else {

            ToastUtil.showCenterToast(getApplicationContext(),loginAPIResponse.getMessage());

        }

    }
}


