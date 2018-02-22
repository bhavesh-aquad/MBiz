package com.mbiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class DealsActivity extends AppCompatActivity {
    //a list to store all the deals
    List<Deals> dealsList;

    private static final String DEALS_URL = "http://www.stubuz.com/mbiz/api/deals";
    private static final String imageUrl = "http://www.stubuz.com/mbiz/assets";

    //the recyclerview
    RecyclerView recyclerView;

    //a progressbar
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the dealslist
        dealsList = new ArrayList<>();
        loadRecyclerViewData();

    }

      private void loadRecyclerViewData() {
          //getting the progressbar
          mProgress =new ProgressDialog(this);
          String titleId="Loading Data";
          mProgress.setTitle(titleId);
          mProgress.setMessage("Please Wait...");
          mProgress.show();

        StringRequest dealsRequest = new StringRequest(Request.Method.GET, DEALS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgress.dismiss();
                        try {
                            //JSONObject jsonResponse = new JSONObject(response);
                            //getting the whole json object from the response
                            //converting the string to json array object
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray dataArray = jsonResponse.getJSONArray("data");

                            //traversing through all the object
                            for (int i = 0; i < dataArray.length(); i++) {
                                //getting data object from json array
                                JSONObject deals = dataArray.getJSONObject(i);
                                Deals add_deals = new Deals(
                                        deals.getInt("id"),
                                        deals.getString("name"),
                                        deals.getString("title"),
                                        deals.getString("image1"));
                                dealsList.add(add_deals);
                        }
                            //creating recyclerview adapter
                            DealsAdapter adapter = new DealsAdapter(dealsList, getApplicationContext());
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);
                            //}

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DealsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(DealsActivity.this).add(dealsRequest);
      }

      @Override
      public boolean onOptionsItemSelected(android.view.MenuItem item) {
          if (item.getItemId() == android.R.id.home)
              finish();
          return super.onOptionsItemSelected(item);
    }
}
       /* new Response.ErrorListener(){
                @Override
                        public void onErrorResponse(VolleyError volleyError){
                    Toast.makeText(getApplicationContext(), VolleyError.getMessage("ERROR"), Toast.LENGTH_SHORT).show();

                }

            }*/

       /* DealsRequest dealsRequest = new DealsRequest(name, title, image1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DealsActivity.this);
        queue.add(dealsRequest);
    }*/




        /*  Response.Listener<String> responseListener = new Response.Listener<String>() {
            private MenuItem item;

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response.body().String());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        Deals deals = new Deals(object.getString("name"), object.getString("offer"),
                                object.getString("image"));

                        dealsList.add(deals);
                        //String status = jsonResponse.optString("status");
                        //String message = jsonResponse.optString("message");

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    System.out.print("end of content");
                }
            }*/

        //adding some items to our list
    /*****    dealsList.add(
                new Deals(
                        1,
                        "Rencho Restaurant",
                        "10%",
                        R.drawable.img1));

        dealsList.add(
                new Deals(
                        1,
                        "Rencho Restaurant",
                        "14%",
                        R.drawable.img2));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "13%",
                        R.drawable.img3));

        dealsList.add(
                new Deals(
                        1,
                        "Rencho Restaurant",
                        "14%",
                        R.drawable.img4));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "14%",
                        R.drawable.img1));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "14%",
                        R.drawable.img2));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "13%",
                        R.drawable.img3));

        dealsList.add(
                new Deals(
                        1,
                        "Rencho Restaurant",
                        "14%",
                        R.drawable.img4));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "14%",
                        R.drawable.img1));

        dealsList.add(
                new Deals(
                        1,
                        "Paul Restaurant",
                        "14%",
                        R.drawable.img1));

        //creating recyclerview adapter
        DealsAdapter adapter = new DealsAdapter(this, dealsList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
*****/
           /* @Override
            public boolean onOptionsItemSelected(android.view.MenuItem item) {
                if (item.getItemId() == android.R.id.home)
                    finish();

                return super.onOptionsItemSelected(item);
            }
}*/


