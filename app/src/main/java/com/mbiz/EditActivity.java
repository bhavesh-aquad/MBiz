package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditActivity extends CustomActivity implements CustomActivity.ResponseCallback {

    EditText et_edit_email, et_edit_password, et_edit_fname, et_edit_lname, et_edit_address, et_edit_pn, et_edit_postcode;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_edit_email = findViewById(R.id.et_edit_email);
        et_edit_password = findViewById(R.id.et_edit_password);
        et_edit_fname = findViewById(R.id.et_first_name);
        et_edit_lname = findViewById(R.id.et_last_name);
        et_edit_address = findViewById(R.id.et_address);
        et_edit_pn = findViewById(R.id.et_phone);
        et_edit_postcode = findViewById(R.id.et_postcode);
        submit = findViewById(R.id.btn_submit);

     /*   Intent i = getIntent();
        String email = i.getStringExtra( "Email","" );
        // Now set this value to EditText
        et_edit_email.setText ( email );
        String password = i.getStringExtra( "Password","" );
        // Now set this value to EditText
        et_edit_email.getText ( password );
        //et_edit_email.setEnable(false);            */

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.side_toolbar_title);
        mTitle.setText("Edit");
        actionBar.setTitle("");



     final RequestParams p = new RequestParams();
        //  p.put("fname", et_first_name.getText().toString());
        //  p.put("lname", et_last_name.getText().toString());
        //  p.put("member_id");
        p.put("email", et_edit_email.getText().toString());
        p.put("password", et_edit_password.getText().toString());
        p.put("fname", et_edit_fname.getText().toString());
        p.put("lname", et_edit_lname.getText().toString());
        p.put("phone", et_edit_pn.getText().toString());
        p.put("address", et_edit_address.getText().toString());
        p.put("postcode", et_edit_postcode.getText().toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCall(getContext(), AppConstants.BASE_URL + "updateuserprofile", p, "Registering you...", 1);
                Bundle bb;
                bb=getIntent().getExtras();
                et_edit_email.setText(bb.getString("name"));
                et_edit_password.setText(bb.getString("password"));

            }
        });

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
                MyApp.popFinishableMessage("MBiz", "You have registered successfully, Please login to use the app.\nThank you.", EditActivity.this);
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
        return EditActivity.this;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
