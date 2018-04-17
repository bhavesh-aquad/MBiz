package com.mbiz;

import android.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.mbiz.adapter.CategoryAdapter;
import com.mbiz.adapter.DealsAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.application.SingleInstance;
import com.mbiz.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class DealsActivity extends CustomActivity implements CustomActivity.ResponseCallback {
    private List<Deals> dealsList;
    private RecyclerView recyclerView;
    View.OnClickListener listener;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String currentlat,currentlong;
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
        mTitle.setText(getIntent().getStringExtra("categoryName"));
        actionBar.setTitle("");
      //  actionBar.setTitle(getIntent().getStringExtra("categoryName"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dealsList = new ArrayList<>();
        loadRecyclerViewData();

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void loadRecyclerViewData() {

        dealsList = SingleInstance.getInstance().getDealsList();
        DealsAdapter adapter = new DealsAdapter(dealsList, getApplicationContext(),  new DealsAdapter.ClickListener() {
            @Override public void onPositionClicked(int position) {
                //postCall(getContext(), AppConstants.BASE_URL + "getdealbyid", new RequestParams(), "Please wait...", 1);
                // callback performed on click
                if(position<=dealsList.size()){
                    Intent i = new Intent(DealsActivity.this, DetailsActivity.class).putExtra("id",dealsList.get(position).getId());
                    startActivity(i);
                }
            }

            @Override public void onLongClicked(int position) {
                // callback performed on click
            }
        });
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
//        postCall(getContext(), AppConstants.BASE_URL + "deals", new RequestParams(), "Please wait...", 1);

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
            DealsAdapter adapter = new DealsAdapter(dealsList, getApplicationContext(),  new DealsAdapter.ClickListener() {
                @Override public void onPositionClicked(int position) {
                // callback performed on click
                    if(position<=dealsList.size()){
                        Intent i = new Intent(DealsActivity.this, DetailsActivity.class).putExtra("id",dealsList.get(position).getId());
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

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }

    private Context getContext() {
        return DealsActivity.this;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(DealsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (DealsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DealsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                currentlat = String.valueOf(latti);
                currentlong = String.valueOf(longi);

//                textView.setText("Your current location is"+ "\n" + "currentlat = " + currentlat
//                        + "\n" + "currentlong = " + currentlong); // konsi get kar pa rahe ho? currentlat and currentlong

            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                currentlat = String.valueOf(latti);
                currentlong = String.valueOf(longi);

//                textView.setText("Your current location is"+ "\n" + "currentlat = " + currentlat
//                        + "\n" + "currentlong = " + currentlong);


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                currentlat = String.valueOf(latti);
                currentlong = String.valueOf(longi);

//                textView.setText("Your current location is"+ "\n" + "currentlat = " + currentlat
//                        + "\n" + "currentlong = " + currentlong);

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }

            MyApp.setSharedPrefString("CLAT",currentlat);
            MyApp.setSharedPrefString("CLONG",currentlong);
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}