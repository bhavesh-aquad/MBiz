package com.mbiz.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mbiz.DealsActivity;
import com.mbiz.R;
import com.mbiz.adapter.CategoryAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.application.SingleInstance;
import com.mbiz.model.Category;
import com.mbiz.model.CategoryChild;
import com.mbiz.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends CustomFragment implements CustomFragment.ResponseCallback {
    Context mCtx;
    ImageView image;
    TextView name;
    private List<Category> categoryList;
    private RecyclerView recyclerView;
    View.OnClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setResponseListener(this);
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
//get the button view
        //  image = rootView.findViewById(R.id.image);
        //   recyclerView = rootView.findViewById(R.id.recyclerView);
        //  CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, mCtx);
        //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //   recyclerView.setAdapter(categoryAdapter);
        //set a onclick listener for when the button gets clicked
      /*  image.setOnClickListener(new View.OnClickListener() {
            //Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), DealsActivity.class);
                startActivity(mainIntent);
            }
        });  */

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        CategoryAdapter categoryAdapter = new CategoryAdapter(SingleInstance.getInstance().getHomeChild(), CategoryFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoryAdapter);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
     */

      /*  categoryList = new ArrayList<>();

        categoryList.add(new Category(
                1,
                "Asian",
                R.drawable.img1
                ));
        categoryList.add(new Category(2,
                "British",
                R.drawable.img2));
        categoryList.add(new Category(3,
                "Chinese",
                R.drawable.img3));
        categoryList.add(new Category(4,
                "French",
                R.drawable.img1));
        categoryList.add(new Category(5,
                "Greek",
                R.drawable.img4));
        categoryList.add(new Category(6,
                "Indian",
                R.drawable.img1));
        categoryList.add(new Category(7,
                "Italian",
                R.drawable.img2));
        categoryList.add(new Category(8,
                "Japanese",
                R.drawable.img3));
        categoryList.add(new Category(9,
                "Mendaterrian",
                R.drawable.img4));

*/
    }

    private String categoryId;
    private String categoryName;

    public void callApi(CategoryChild c) {
        categoryName = c.getCategory_name();
        categoryId = c.getId();
        RequestParams p = new RequestParams();
        p.put("category_id",categoryId);
        postCall(getContext(), AppConstants.BASE_URL + "deals", p, "Please wait...", 1);
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
//                                    deals.getString("mflat"),
//                                    deals.getString("mbuilding"),
//                                    deals.getString("maddress1"),
//                                    deals.getString("maddress2"),
//                                    deals.getString("mtown"),
//                                    deals.getString("mcounty"),
                                    deals.getString("mpostcode"),
                                    deals.getString("mtown"));
 //                                   deals.getString("address"));
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
                    SingleInstance.getInstance().setDealsList(dealsList);
                    startActivity(new Intent(getActivity(), DealsActivity.class).putExtra("categoryName", categoryName));
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

/*    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing the productlist
        categoryList = new ArrayList<>();


        //adding some items to our list
        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));
    }
}
*/

/*        //creating recyclerview adapter
        CategoryAdapter adapter = new CategoryAdapter(categoryList, mCtx, new CategoryAdapter.ClickListener() {

            @Override
            public void onPositionClicked(int position) {
                // callback performed on click
                if(position==0){
                    Intent i = new Intent(mCtx, DetailsActivity.class);
                    startActivity(i);
                }
            }

            @Override public void onLongClicked(int position) {
                // callback performed on click
            }
        });

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
*/











  /*
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
        img_restaurant = (ImageButton) getView().findViewById(R.id.img_restaurant);
        // set a onclick listener for when the button gets clicked
        img_restaurant.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        RestaurantActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}

*/








 /*       View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        //return inflater.inflate(R.layout.fragment_category, container, false);
        //get the button view

        img_restaurant = getView().findViewById(R.id.img_restaurant);
        return rootView;
    }

    img_restaurant.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent mainIntent = new Intent(getActivity(), RestaurantActivity.class);
        startActivity(mainIntent);
    }
    });
}
*/
