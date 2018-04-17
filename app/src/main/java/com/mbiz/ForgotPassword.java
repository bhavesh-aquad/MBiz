package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class ForgotPassword extends CustomActivity implements CustomActivity.ResponseCallback {
    TextView tv_send_code;
    Button btn_send;
    EditText et_email;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tv_send_code = findViewById(R.id.tv_send_code);
        btn_send = findViewById(R.id.btn_send);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = findViewById(R.id.side_toolbar_title);
        mTitle.setText("Forgot Password");
        actionBar.setTitle("");


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_email.getText().toString().length() == 0)
                    et_email.setError("Email is required");

                else {
                    RequestParams p = new RequestParams();
                    p.put("email", et_email.getText().toString());
                    postCall(getContext(), AppConstants.BASE_URL + "forgetpassword", p, "Please wait...", 1);

                    // tv_send_code.setText("A CODE SENT TO YOUR EMAIL ID");
                }
            }
        });
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {

        if (callNumber == 1) {
            if (o.optInt("status") == 200) {
                    MyApp.popFinishableMessage("Mbiz Message", o.optString("message"), ForgotPassword.this);
            } else {
                MyApp.popFinishableMessage("Error", o.optString("message"), ForgotPassword.this);
            }
        }
    }


    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }
    private Context getContext() {
        return ForgotPassword.this;
    }


    @Override
    public void onErrorReceived(String error) {MyApp.popMessage("Error", error, getApplicationContext());}
    public boolean onOptionsItemSelected(android.view.MenuItem item){
        if(item.getItemId()==R.id.forgotpassword);
        finish();

        return super.onOptionsItemSelected(item);
    }
}

