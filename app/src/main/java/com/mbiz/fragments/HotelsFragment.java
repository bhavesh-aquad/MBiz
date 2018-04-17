package com.mbiz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.mbiz.DealsCategoryActivity;
import com.mbiz.DetailsActivity;
import com.mbiz.R;
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

public class HotelsFragment extends CustomFragment implements CustomFragment.ResponseCallback {
    private List<Deals> dealsList;
    private RecyclerView recyclerView;
    View.OnClickListener listener;
  //  private HotelsFragment responseListener;

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

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    private void loadRecyclerViewData() {

        RequestParams p = new RequestParams();
        p.put("category_id",6);
        if (((DealsCategoryActivity) getActivity()).number == 3) {
            postCall(getContext(), AppConstants.BASE_URL + "getallfeatureddeals", p, " ", 1);
        } else if (((DealsCategoryActivity) getActivity()).number == 4) {
            postCall(getContext(), AppConstants.BASE_URL + "getallweekenddeals", p, " ", 1);
        } else if (((DealsCategoryActivity) getActivity()).number == 1) {
            postCall(getContext(), AppConstants.BASE_URL + "getalltodaydeals", p, " ", 1);
        } else if (((DealsCategoryActivity) getActivity()).number == 5) {
            postCall(getContext(), AppConstants.BASE_URL + "getalltopdeals", p, " ", 1);
        } else if (((DealsCategoryActivity) getActivity()).number == 2) {
            postCall(getContext(), AppConstants.BASE_URL + "getallhotdeals", p, " ", 1);
        } else{
            postCall(getContext(), AppConstants.BASE_URL + "getalltodaydeals", p, " ", 1);

        }
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(o.toString());
                JSONArray dataArray = jsonResponse.getJSONArray("count");

                //traversing through all the object
                for (int i = 0; i < dataArray.length(); i++) {
                    //getting data object from json array
                    JSONObject deals = dataArray.getJSONObject(i);
                    Deals add_deals = new Deals(
                            deals.getInt("id"),
                            deals.getString("name"),
                            deals.getString("merchant_name"),
                            deals.getString("mtagline"),
                            deals.getString("partner_distance"),
                            deals.getString("mthumbnail"),
//                            deals.getString("mflat"),
//                            deals.getString("mbuilding"),
//                            deals.getString("maddress1"),
//                            deals.getString("maddress2"),
//                            deals.getString("mtown"),
//                            deals.getString("mcounty"),
                            deals.getString("mpostcode"),
                            deals.getString("mtown"));
//                            deals.getString("address"));
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
                    if (position <= dealsList.size()) {
                        Intent i = new Intent(getContext(), DetailsActivity.class).putExtra("id",dealsList.get(position).getId());
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

//            if (dealsList.size() == 0) {
//                MyApp.popFinishableMessage("MBiz Message", "No deals available", getActivity());
//            }
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {
        MyApp.popMessage("Error", error, getActivity());
    }

 /*   public void setResponseListener(HotelsFragment responseListener) {
        this.responseListener = responseListener;
    }        */
}