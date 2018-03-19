package com.mbiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.loopj.android.http.RequestParams;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;

public class SignupActivity extends CustomActivity implements CustomActivity.ResponseCallback {
    Button sign_up_btn;
    EditText et_first_name, et_last_name, et_phone, et_address, et_signup_email, et_signup_password, et_signup_confirmpassword,
            et_postcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.side_toolbar_title);
        mTitle.setText("Sign Up");
        actionBar.setTitle("");

        setResponseListener(this);

        sign_up_btn = findViewById(R.id.sign_up_btn);
      //  et_first_name = findViewById(R.id.et_first_name);
      //  et_last_name = findViewById(R.id.et_last_name);
      //  et_phone = findViewById(R.id.et_phone);
      //  et_address = findViewById(R.id.et_address);
      //  et_postcode = findViewById(R.id.et_postcode);
        et_signup_email = findViewById(R.id.et_signup_email);
        et_signup_password = findViewById(R.id.et_signup_password);
        et_signup_confirmpassword = findViewById(R.id.et_signup_confirmpassword);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (et_first_name.getText().toString().length() == 0)
                    et_first_name.setError("First Name is Required");
                else if (et_last_name.getText().toString().length() == 0)
                    et_last_name.setError("Last Name is Required");
                else if (et_phone.getText().toString().length() == 0)
                    et_phone.setError("Phone Number is Required");
               /* else if (et_address.getText().toString().length() == 0)
                    et_address.setError("Address is Required");
                else */
                if (et_signup_email.getText().toString().length() == 0)
                    et_signup_email.setError("Email is required");
                else if (et_signup_password.getText().toString().length() == 0)
                    et_signup_password.setError("Password Is Required");
                else if (et_signup_confirmpassword.getText().toString().length()==0)
                    et_signup_confirmpassword.setError("Please enter Password again");
                else {

                    RequestParams p = new RequestParams();
                  //  p.put("fname", et_first_name.getText().toString());
                  //  p.put("lname", et_last_name.getText().toString());
                    p.put("email", et_signup_email.getText().toString());
                    p.put("password", et_signup_password.getText().toString());
                    p.put("confirmpassword", et_signup_confirmpassword.getText().toString());
                  //  p.put("address", et_address.getText().toString());
                  //  p.put("postcode", et_postcode.getText().toString());
                  //  p.put("mobile", et_phone.getText().toString());

                    String password = et_signup_password.getText().toString();
                    String confirmpassword = et_signup_confirmpassword.getText().toString();

                    if(password.equals(confirmpassword))
                        postCall(getContext(), AppConstants.BASE_URL + "signup", p, "Registering you...", 1);
                    else{
                        Toast.makeText(getApplicationContext(), "Password and confirm password should be same", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            if (o.optInt("status") == 200) {
                if (o.optString("status_id").equals("t2") || o.optString("status_id").equals("t3")) {
                    MyApp.popMessage("Error", o.optString("message"), getContext());
                    return;
                }
//                MyApp.showMassage(getContext(), "Registered successfully");
//                MyApp.setStatus(AppConstants.IS_LOGIN, true);
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//                finishAffinity();
                MyApp.popFinishableMessage("MBiz", "You have registered successfully, Please login to use the app.\nThank you.", SignupActivity.this);
            } else {
                MyApp.popMessage("Error", o.optString("message"), getContext());
            }
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {
        MyApp.popMessage("Error", error, getContext());
    }

    private Context getContext() {
        return SignupActivity.this;
    }
}
