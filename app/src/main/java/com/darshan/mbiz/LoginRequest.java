package com.darshan.mbiz;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aquad on 03-01-2018.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://www.stubuz.com/mbiz/api/douserlogin";

    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String>getParams(){
        return params;
    }
}
