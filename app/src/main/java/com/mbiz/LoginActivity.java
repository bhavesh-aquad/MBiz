package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.User;
import com.shaishavgandhi.loginbuttons.FacebookButton;
import com.shaishavgandhi.loginbuttons.GooglePlusButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends CustomActivity implements GoogleApiClient.OnConnectionFailedListener, CustomActivity.ResponseCallback {

    private TextView login_txtview, forgotpassword;
    private EditText et_login_username, et_login_password;
    private Button sign_in_btn;
    private FacebookButton btn_fb;
    private GooglePlusButton btn_google;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private static int RC_SIGN_IN = 156;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MyApp.getStatus(AppConstants.IS_LOGIN)) {
            startActivity(new Intent(getContext(), HomeActivity.class));
            return;
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        setResponseListener(this);
        setContentView(R.layout.activity_login);
        initialfileControls();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        login_txtview = findViewById(R.id.login_txtview);
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        btn_fb = findViewById(R.id.btn_fb);
        btn_google = findViewById(R.id.btn_google);
        sign_in_btn = findViewById(R.id.sign_in_btn);
        forgotpassword = findViewById(R.id.forgotpassword);

        et_login_username.setHintTextColor(Color.WHITE);
        et_login_password.setHintTextColor(Color.WHITE);
        login_txtview.setText(Html.fromHtml("<p>" +
                " New User ?" +
                "           " +
                "<font color = \"#F71414\"><strong>" + "Sign up" + "</strong></font>" +
                " Here" +
                "</p>"));

        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithFB();
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_login_username.getText().toString().isEmpty())
                    et_login_username.setError("Email is required!");

                else if (et_login_password.getText().toString().isEmpty())
                    et_login_password.setError("Password is required");

                else {
                    RequestParams p = new RequestParams();
                    p.put("email", et_login_username.getText().toString());
                    p.put("password", et_login_password.getText().toString());
                    postCall(getContext(), AppConstants.BASE_URL + "douserlogin", p, "Logging you in..", 1);
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


        ClickableSpan Signup = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intentSignUP = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intentSignUP);
            }
        };

        makeLinks(login_txtview, new String[]{"Sign up"}, new ClickableSpan[]{
                Signup
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private void initialfileControls() {
        callbackManager = CallbackManager.Factory.create();
//        login_button = (LoginButton) findViewById(R.id.login_button);
    }

    private void loginWithFB() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                MyApp.spinnerStart(getContext(), "Please wait...");
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                MyApp.spinnerStop();
                                String fb_id = null;
                                try {
                                    fb_id = object.getString("id");
                                    MyApp.setSharedPrefString(AppConstants.FB_ID, fb_id);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (MyApp.isConnectingToInternet(getContext())) {
                                        RequestParams p = new RequestParams();
                                        p.put("email", object.getString("email"));
                                        p.put("account_type", "FACEBOOK");
                                        p.put("account_id", fb_id);
                                        if (object.getString("name").split(" ").length > 1) {
                                            p.put("fname", object.getString("name").split(" ")[0]);
                                            p.put("lname", object.getString("name").split(" ")[1]);
                                        } else {
                                            p.put("fname", object.getString("name").split(" ")[0]);
                                            p.put("lname", "");
                                        }

                                        p.put("address", "");
                                        p.put("postcode", "");
                                        p.put("mobile", "");
                                        postCall(getContext(), AppConstants.BASE_URL + "sociallogin", p, "Logging you in..", 2);
//                                        registerUser(object.getString("email"), object.getString("name").split(" ")[0], object.getString("name").split(" ")[1]);
                                        LoginManager.getInstance().logOut();
                                        object.getString("email");
                                    } else {
                                        MyApp.popMessage("Alert", getString(R.string.connect_working_internet), getContext());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    try {
                                        object.getString("email");
                                        RequestParams p = new RequestParams();
                                        p.put("email", object.getString("email"));
                                        p.put("account_type", "FACEBOOK");
                                        p.put("account_id", fb_id);
                                        if (object.getString("name").split(" ").length > 1) {
                                            p.put("fname", object.getString("name").split(" ")[0]);
                                            p.put("lname", object.getString("name").split(" ")[1]);
                                        } else {
                                            p.put("fname", object.getString("name").split(" ")[0]);
                                            p.put("lname", "");
                                        }

                                        p.put("address", "");
                                        p.put("postcode", "");
                                        p.put("mobile", "");
                                        postCall(getContext(), AppConstants.BASE_URL + "sociallogin", p, "Logging you in..", 2);

                                        LoginManager.getInstance().logOut();
                                    } catch (JSONException ee) {

                                    }

                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email,gender");
                request.setParameters(parameters);
                request.executeAsync();

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

    protected void onActivityResult(int requestCode, int ResultCode, Intent data) {
        super.onActivityResult(requestCode, ResultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            return;
        }
        callbackManager.onActivityResult(requestCode, ResultCode, data);
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Google sign in", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
//            registerUser(acct.getEmail(), acct.getGivenName(), acct.getFamilyName());
            RequestParams p = new RequestParams();
            p.put("email", acct.getEmail());
            p.put("account_type", "GOOGLE");
            p.put("account_id", acct.getId());
            p.put("fname", acct.getGivenName());
            p.put("lname", acct.getFamilyName());
            p.put("address", "");
            p.put("postcode", "");
            p.put("mobile", "");
            postCall(getContext(), AppConstants.BASE_URL + "sociallogin", p, "Logging you in..", 2);
            signOut();
            revokeAccess();
        } else {
            // Signed out, show unauthenticated UI.
            MyApp.showMassage(getContext(), "Failed...");
        }
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    private Context getContext() {
        return LoginActivity.this;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        MyApp.showMassage(getContext(), "Connection not stabilised with google");
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            if (o.optInt("status") == 200) {

                User user = new Gson().fromJson(o.toString(), User.class);
                if (user.getStatus_id().equals("t2") || user.getStatus_id().equals("t3")) {
                    MyApp.popMessage("Error", o.optString("message"), getContext());
                    return;
                }
                MyApp.getApplication().writeUser(user);

                MyApp.showMassage(getContext(), "Logged-in successfully");
                MyApp.setStatus(AppConstants.IS_LOGIN, true);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                MyApp.popMessage("Error", o.optString("message"), getContext());
            }
        } else {
            // social login
            if (o.optInt("status") == 200) {

                MyApp.showMassage(getContext(), o.optString("message"));
                User user = new Gson().fromJson(o.toString(), User.class);
                if (user.getStatus_id().equals("t2") || user.getStatus_id().equals("t3")) {
                    MyApp.popMessage("Error", o.optString("message"), getContext());
                    return;
                }
                MyApp.getApplication().writeUser(user);

//                MyApp.showMassage(getContext(), "Logged-in successfully");
                MyApp.setStatus(AppConstants.IS_LOGIN, true);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                MyApp.showMassage(getContext(), o.optString("message"));
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
}
