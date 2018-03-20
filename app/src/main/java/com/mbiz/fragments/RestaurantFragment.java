package com.mbiz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.mbiz.DetailsActivity;
import com.mbiz.R;
import com.mbiz.TodaysDealsActivity;
import com.mbiz.adapter.DealsAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Aquad on 16/03/2018.
 */

public class RestaurantFragment extends CustomFragment implements CustomFragment.ResponseCallback {
    private List<Deals> dealsList;
    private RecyclerView recyclerView;
    View.OnClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deals_bhavesh, container, false);
        setResponseListener(this);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dealsList = new ArrayList<>();
        loadRecyclerViewData();
//get the button view
        // img_restaurant = rootView.findViewById(R.id.img_restaurant);
        //set a onclick listener for when the button gets clicked
      /* img_restaurant.setOnClickListener(new View.OnClickListener() {
            //Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), DealsActivity.class);
                startActivity(mainIntent);
            }
        });       */


        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    private void loadRecyclerViewData() {

        if (((TodaysDealsActivity) getActivity()).number == 3) {
            postCall(getContext(), AppConstants.BASE_URL + "featureddeals", new RequestParams(), "Please wait...", 1);
        } else if (((TodaysDealsActivity) getActivity()).number == 4) {
            postCall(getContext(), AppConstants.BASE_URL + "weekenddeals", new RequestParams(), "Please wait...", 1);
        } else if (((TodaysDealsActivity) getActivity()).number == 1) {
            postCall(getContext(), AppConstants.BASE_URL + "todaydeals", new RequestParams(), "Please wait...", 1);
        } else if (((TodaysDealsActivity) getActivity()).number == 5) {
            postCall(getContext(), AppConstants.BASE_URL + "topdeals", new RequestParams(), "Please wait...", 1);
        } else if (((TodaysDealsActivity) getActivity()).number == 2) {
            postCall(getContext(), AppConstants.BASE_URL + "hotdeals", new RequestParams(), "Please wait...", 1);
        } else
            postCall(getContext(), AppConstants.BASE_URL + "deals", new RequestParams(), "Please wait...", 1);

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
            DealsAdapter adapter = new DealsAdapter(dealsList, getApplicationContext(), new DealsAdapter.ClickListener() {
                @Override
                public void onPositionClicked(int position) {
                    // callback performed on click
                    if (position == 0) {
                        Intent i = new Intent(getContext(), DetailsActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onLongClicked(int position) {
                    // callback performed on click
                }
            });
            //setting adapter to recyclerview
            recyclerView.setAdapter(adapter);

            if (dealsList.size() == 0) {
                MyApp.popFinishableMessage("MBiz Message", "No deals available", getActivity());
            }
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }
}

