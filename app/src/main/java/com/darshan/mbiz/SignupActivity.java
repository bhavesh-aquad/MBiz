package com.darshan.mbiz;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.font;
import static com.darshan.mbiz.R.id.et_phone;
import static com.darshan.mbiz.R.id.et_signup_email;
import static com.darshan.mbiz.R.id.et_signup_password;

public class SignupActivity extends AppCompatActivity {
    TextView tv_signup;
    Button sign_up_btn;
    EditText  et_first_name,et_last_name,et_phone,et_address, et_signup_email, et_signup_password, et_zip;
    //TextInputLayout input_email_id,  input_password_id,  input_username_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get action bar
        //ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        //actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
        et_first_name=(EditText)findViewById(R.id.et_first_name);
        et_last_name=(EditText)findViewById(R.id.et_last_name);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_address=(EditText)findViewById(R.id.et_address);
        et_zip=(EditText)findViewById(R.id.et_zip);
        et_signup_email=(EditText)findViewById(R.id.et_signup_email);
        et_signup_password=(EditText)findViewById(R.id. et_signup_password);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_first_name.getText().toString().length()==0)
                    et_first_name.setError("First Name is Required");
                else if(et_last_name.getText().toString().length()==0)
                    et_last_name.setError("Last Name is Required");
                else if(et_phone.getText().toString().length()==0)
                    et_phone.setError("Phone Number is Required");
                else if(et_address.getText().toString().length()==0)
                    et_address.setError("Address is Required");
                else if(et_signup_email.getText().toString().length()==0)
                    et_signup_email.setError("Email is required");
                else if (et_signup_password.getText().toString().length()==0)
                    et_signup_password.setError("Password Is Required");
                else {
                    final String fname = et_first_name.getText().toString();
                    final String lname = et_last_name.getText().toString();
                    final String email = et_signup_email.getText().toString();
                    final String password = et_signup_password.getText().toString();
                    final String address = et_address.getText().toString();
                    final String postcode = et_zip.getText().toString();
                    final String mobile = et_phone.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String status = jsonResponse.optString("status");

                                if (status.equals("200")){
                                    // TODO Auto-generated method stub
                                    Intent signUp = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(signUp);
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                    builder.setMessage("Signup Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };

                    SignUpRequest signUpRequest = new SignUpRequest(fname, lname, email, password, address,
                            postcode, mobile, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                    queue.add(signUpRequest);
                }

            }
        });

        //TextInputLayout   input_username_id = (TextInputLayout)    findViewById(R.id.input_username_id);
        //font = Typeface.createFromAsset(getAssets(), "fonts/ir.ttf");
        //input_username_id .setTypeface(font);

        //Typeface newTypeface = Typeface.createFromAsset(getAssets(), "AguafinaScript-Regular.ttf");
        //CustomHint customHint = new CustomHint(newTypeface, null, Typeface.BOLD_ITALIC, 60f);
        //        CustomHint customHint = new CustomHint(newTypeface, "Enter some text", Typeface.BOLD_ITALIC);
        //        CustomHint customHint = new CustomHint(newTypeface, "Enter some text", 60f);
        //        CustomHint customHint = new CustomHint("Enter some text", Typeface.BOLD_ITALIC, 60f);
        //        CustomHint customHint = new CustomHint("Enter some text", Typeface.BOLD_ITALIC);
        //        CustomHint customHint = new CustomHint("Enter some text", 60f);

        //et_signup_password.setHint(customHint);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
