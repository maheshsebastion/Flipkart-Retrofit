package com.example.admin.flipkart.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.activity.MainActivity;
import com.example.admin.flipkart.api.response.RegisterAPIResponse;
import com.example.admin.flipkart.api.subscriber.RegisterEventSubscriber;
import com.example.admin.flipkart.api.util.CommunicationManager;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.models.User;
import com.example.admin.flipkart.request.RegistrationRequest;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;

public class RegisterActivity extends AppActivity implements RegisterEventSubscriber {

    RegisterActivity mActivity;

    Button btnRegister;
    EditText mName,mEmail,mPhone,mPassword,mCPassword;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_register);

        mName = (EditText) findViewById(R.id.rName);
        mEmail = (EditText) findViewById(R.id.rEmail);
        mPhone = (EditText) findViewById(R.id.rPhone);
        mPassword = (EditText) findViewById(R.id.rPassword);
        mCPassword = (EditText) findViewById(R.id.rCpassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = mName.getText().toString();
                String email = TextUtil.cleanupString(mEmail.getText().toString());
                String phone = TextUtil.cleanupString(mPhone.getText().toString());
                String password = TextUtil.cleanupString(mPassword.getText().toString());
                String cPassword = TextUtil.cleanupString(mCPassword.getText().toString());

                String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (!email.matches(emailPattern) || phone.length()<10 || phone.length()>10 || password.length()<6 || !cPassword.equals(password) ||
                TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword)) {

                    if (!email.matches(emailPattern)) {
                        mEmail.setError(getString(R.string.error_email_mismatch));
                    }
                    if (phone.length() < 10 || phone.length() > 10) {
                        mPhone.setError(getString(R.string.error_phone_size));
                    }
                    if (password.length() < 6) {
                        mPassword.setError(getString(R.string.error_password_size));
                    }
                    if (!cPassword.equals(password)) {
                        mCPassword.setError(getString(R.string.error_password_mismatch));
                    }
                    //Checking fields are empty
                    if (TextUtils.isEmpty(name)) {
                        mName.setError(getString(R.string.error_field_required));
                    }
                    if (TextUtils.isEmpty(email)) {
                        mEmail.setError(getString(R.string.error_field_required));
                    }
                    if (TextUtils.isEmpty(phone)) {
                        mPhone.setError(getString(R.string.error_field_required));
                    }
                    if (TextUtils.isEmpty(password)) {
                        mPassword.setError(getString(R.string.error_field_required));
                    }
                    if (TextUtils.isEmpty(cPassword)) {
                        mCPassword.setError(getString(R.string.error_field_required));
                    }
                }else {

                    RegistrationRequest registrationRequest = new RegistrationRequest();
                    registrationRequest.setName(name);
                    registrationRequest.setEmail(email);
                    registrationRequest.setMobile_number(phone);
                    registrationRequest.setPassword(password);
                    registrationRequest.setPassword_confirmation(cPassword);

                    showProgress();
                    //API Call Through CommunicationManager Class------>
                    CommunicationManager.getInstance().postRegisterDetails(registrationRequest, mActivity);

                }
            }
        });
    }

    @Override
    public void onRegisterationCompleted(RegisterAPIResponse registerAPIResponse) {

        hideProgress();

        if(registerAPIResponse.isSuccess()) {
            user = registerAPIResponse.getUser();
            ToastUtil.showCenterToast(getApplicationContext(),registerAPIResponse.getMessage());
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            ToastUtil.showCenterToast(getApplicationContext(),"Please Login to your account...");
            finish();
        }
        else{

            ToastUtil.showCenterToast(getApplicationContext(),registerAPIResponse.getMessage());

        }
    }
}