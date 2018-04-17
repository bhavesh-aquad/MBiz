package com.mbiz.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mbiz.CustomActivity;
import com.mbiz.DealsActivity;
import com.mbiz.R;
import com.mbiz.adapter.CategoryAdapter;
import com.mbiz.adapter.FDealsAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.application.SingleInstance;
import com.mbiz.model.Category;
import com.mbiz.model.Deals;
import com.mbiz.model.FDeals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DealsFragment extends CustomFragment implements CustomFragment.ResponseCallback {
    Context mCtx;
   // ImageView image;
   // TextView name;
    private List<FDeals> fdealsList;
    private RecyclerView recyclerView;
   // View.OnClickListener listener;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        View rootView = inflater.inflate(R.layout.fragment_deals, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        FDealsAdapter fdealsAdapter = new FDealsAdapter(fdealsList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(fdealsAdapter);

        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
     */

        fdealsList = new ArrayList<>();

        fdealsList.add(new FDeals(
                1,
                "Todays Deals",
                R.drawable.img1
        ));
        fdealsList.add(new FDeals(2,
                "Hot Deals",
                R.drawable.img2));
        fdealsList.add(new FDeals(3,
                "Featured Deals",
                R.drawable.img3));
        fdealsList.add(new FDeals(4,
                "Weekend Offer",
                R.drawable.img4));
        fdealsList.add(new FDeals(5,
                "Top 10 Offer",
                R.drawable.img2));

        if(getId()==1)
            postCall(getContext(), AppConstants.BASE_URL + "todaydeals", new RequestParams(), "", 1);
        else if(getId()==2)
            postCall(getContext(), AppConstants.BASE_URL + "hotdeals", new RequestParams(), "", 1);
        else if(getId()==3)
            postCall(getContext(), AppConstants.BASE_URL + "featureddeals", new RequestParams(), "", 1);
        else if(getId()==4)
            postCall(getContext(), AppConstants.BASE_URL + "weekenddeals", new RequestParams(), "", 1);
        else if(getId()==5)
            postCall(getContext(), AppConstants.BASE_URL + "topdeals", new RequestParams(), "", 1);
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {

            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(o.toString());
                JSONArray dataArray = jsonResponse.getJSONArray("data");
                List<Deals> dealsList = new ArrayList<>();
                //traversing through all the object
                for (int i = 0; i < dataArray.length(); i++) {
                    //getting data object from json array
                    JSONObject deals = dataArray.getJSONObject(i);

//                        if (deals.optString("category_id").equals(categoryId)) {
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
//                        }

//                        else if(deals.optString("category_id").equals("british")){
//                            Deals add_deals = new Deals(
//                                    deals.getInt("id"),
//                                    deals.getString("name"),
//                                    deals.getString("title"),
//                                    deals.getString("mthumbnail"));
//                            dealsList.add(add_deals);
//                        }
                }

                if (dealsList.size() == 0) {
                    MyApp.popFinishableMessage("MBiz Message", "No deals available", getActivity());
                    return;
                }
//                SingleInstance.getInstance().setDealsList(dealsList);
//                startActivity(new Intent(getActivity(), DealsActivity.class).putExtra("categoryName", categoryName));
            } catch (JSONException e) {
                e.printStackTrace();
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
