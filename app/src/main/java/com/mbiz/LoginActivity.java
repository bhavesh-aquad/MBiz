package com.mbiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    TextView login_txtview, forgotpassword;
    EditText et_login_username, et_login_password;
    LoginButton login_button;
    CallbackManager callbackManager;
    Button sign_in_btn;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        setContentView(R.layout.activity_login);
        initialfileControls();
        loginwithFB();

        login_txtview=(TextView)findViewById(R.id.login_txtview);
        et_login_username=(EditText)findViewById(R.id.et_login_username);
        et_login_password=(EditText)findViewById(R.id.et_login_password);
        sign_in_btn=(Button)findViewById(R.id.sign_in_btn);
        forgotpassword=(TextView)findViewById(R.id.forgotpassword);

        et_login_username.setHintTextColor(Color.WHITE);
        et_login_password.setHintTextColor(Color.WHITE);

        mProgress =new ProgressDialog(this);
        String titleId="Signing in...";
        mProgress.setTitle(titleId);
        mProgress.setMessage("Please Wait...");
        login_txtview.setText(Html.fromHtml("<p>"+
                 " New User ?" +
                "           "+
                "<font color = \"#F71414\"><strong>" + "Sign up"+ "</strong></font>"+
                " Here"+
                "</p>"));

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_login_username.getText().toString().length() == 0)
                    et_login_username.setError("User name is required!");

                else if (et_login_password.getText().toString().length() == 0)
                    et_login_password.setError("Password Is Required");
                else{
                    mProgress.show();
                    final String username = et_login_username.getText().toString();
                    final String password = et_login_password.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String status = jsonResponse.optString("status");

                                if(status.equals("200")){
                                    mProgress.dismiss();
                                    Intent intentLogIn = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intentLogIn);
                                }
                            }catch (Exception e){
                                //mProgress.setMessage("Either User Name Or Password Is incorrect...");
                                mProgress.dismiss();
                                Toast.makeText(getApplicationContext(), "Either Username Or Password Is Incorrect",
                                        Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);

                }
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpasswordIntent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(forgotpasswordIntent);
            }
        });
            /**@Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Sign in Clicked", Toast.LENGTH_SHORT).show();

                if( et_login_username.getText().toString().length() == 0 )
                    et_login_username.setError( "User name is required!" );

                else if( et_login_password.getText().toString().length() == 0)
                    et_login_password.setError("Password Is Required");
                else{
                    Intent intentLogIn = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intentLogIn);}
            }
        };*/

        ClickableSpan Signup = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                 //Toast.makeText(getApplicationContext(), "Sign up Clicked", Toast.LENGTH_SHORT).show();

                Intent intentSignUP = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intentSignUP);
            }
        };

        makeLinks(login_txtview, new String[] { "Sign up" }, new ClickableSpan[] {
                 Signup
        });
    }

    private void makeLinks(TextView login_txtview, String[] links, ClickableSpan[] clickableSpens) {

        SpannableString spannableString = new SpannableString(login_txtview.getText());

        for (int i = 0; i < links.length; i++) {
            ClickableSpan clickableSpan = clickableSpens[i];
            String link = links[i];

            int startIndexOfLink = login_txtview.getText().toString().indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        login_txtview.setMovementMethod(LinkMovementMethod.getInstance());
        login_txtview.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    private void initialfileControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton)findViewById(R.id.login_button);
    }

    private void loginwithFB() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "LOGIN CANCEL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "LOGIN ERROR", Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected void onActivityResult(int requestCode, int ResultCode, Intent data){
        super.onActivityResult(requestCode, ResultCode, data);
        callbackManager.onActivityResult(requestCode, ResultCode, data);
    }

}
