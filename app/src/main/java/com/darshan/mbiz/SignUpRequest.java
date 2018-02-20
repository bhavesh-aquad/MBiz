package com.darshan.mbiz;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aquad on 03-01-2018.
 */

public class SignUpRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.stubuz.com/mbiz/api/signup" ;
    private Map<String, String> params;

    public SignUpRequest(String fname, String lname, String email, String password,
                         String address, String postcode, String mobile, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("fname", fname);
        params.put("lname", lname);
        params.put("email", email);
        params.put("password", password);
        params.put("address", address);
        params.put("postcode", postcode);
        params.put("mobile", mobile);
    }

    @Override
    public Map<String, String>getParams(){
        return params;
    }
}
