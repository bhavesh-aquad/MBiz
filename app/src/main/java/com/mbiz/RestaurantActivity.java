package com.mbiz;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.mbiz.adapter.DealsAdapter;
import com.mbiz.adapter.RestaurantAdapter;
import com.mbiz.model.Deals;
import com.mbiz.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    //a list to store all the products
    List<Restaurant> restaurantList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.side_toolbar_title);
        mTitle.setText("Restaurant");
        actionBar.setTitle("");


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        restaurantList = new ArrayList<Restaurant>();


        //adding some items to our list
        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Restaurant",
                        "offer",
                        R.drawable.img2,
                        R.drawable.map_icon));

        //creating recyclerview adapter
        RestaurantAdapter adapter = new RestaurantAdapter(this, restaurantList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
