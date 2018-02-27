package com.mbiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mbiz.adapter.DealsAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DealsActivity extends CustomActivity implements CustomActivity.ResponseCallback {
    private List<Deals> dealsList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        setResponseListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.side_toolbar_title);
        mTitle.setText("Hot Deals");
        actionBar.setTitle("");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dealsList = new ArrayList<>();
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {

        postCall(getContext(), AppConstants.BASE_URL + "deals", new RequestParams(), "Please wait...", 1);

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(o.toString());
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
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //creating recyclerview adapter
            DealsAdapter adapter = new DealsAdapter(dealsList, getApplicationContext());
            //setting adapter to recyclerview
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }

    private Context getContext() {
        return DealsActivity.this;
    }
}