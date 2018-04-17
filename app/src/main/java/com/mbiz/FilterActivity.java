package com.mbiz;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mbiz.adapter.MyPlacesAdapter;
import com.mbiz.application.MyApp;
import com.mbiz.model.Category;
import com.mbiz.model.Deals;
import com.mbiz.model.MyGooglePlaces;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    Button bt_find_deal;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String currentlat,currentlong;

    Spinner deals_spinner, sub_spinner, within_spinner, for_spinner;
    AutoCompleteTextView places;
    MyPlacesAdapter adapter;
    private List<String> categories = new ArrayList<>();
    private List<String> subcategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Filter");

        deals_spinner=findViewById(R.id.category_spinner);
        sub_spinner=findViewById(R.id.subcategory_spinner);
        within_spinner=findViewById(R.id.within_spinner);
        for_spinner=findViewById(R.id.for_spinner);

        final List<String> spinnerCategoryList= new ArrayList<String>();
        final List<String> subcategories= new ArrayList<String>();
        final List<String> within= new ArrayList<String>();
        final List<String> fors= new ArrayList<String>();

        categories.add("Category...");
        categories.add("Restaurant");
        categories.add("Activity");
        categories.add("Health and Beauty");
        categories.add("Take-Away");
        categories.add("Trades and Services");
        categories.add("Leisure and Lifestyle");
        categories.add("Retail");
        categories.add("Bars and Clubs");
        categories.add("Hotel");
        categories.add("Other");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,
                categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deals_spinner.setAdapter(categoryAdapter);

//        ArrayAdapter<String> subcategoryAdapter = new ArrayAdapter<String>(
//                this, R.layout.spinner_item,
//                subcategories);
//        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sub_spinner.setAdapter(subcategoryAdapter);

        deals_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long id) {

                String catName = categories.get(position).toString();
                resetCategory(catName);
                //                subCategory.setAdapter(null);

            }

            private void resetCategory(String catName) {
                subcategories.removeAll(subcategories);
                if (catName.equals("Restaurant")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Asian");
                    subcategories.add("British");
                    subcategories.add("Chinese");
                    subcategories.add("French");
                    subcategories.add("Greek");
                    subcategories.add("Indian");
                    subcategories.add("Italian");
                    subcategories.add("Japanese");
                    subcategories.add("Mediterranean");
                    subcategories.add("Spanish");
                    subcategories.add("Others");
                } else if (catName.equals("Activity")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Golf");
                    subcategories.add("Skiing");
                    subcategories.add("Soft-Play");
                    subcategories.add("Others");
                } else if (catName.equals("Health and Beauty")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Barbers");
                    subcategories.add("Beauty Salon");
                    subcategories.add("Hair Salon");
                    subcategories.add("Health Spa");
                    subcategories.add("Healthcare");
                    subcategories.add("Other");
                } else if(catName.equals("Take-Away")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Chinese");
                    subcategories.add("Fish and Chips");
                    subcategories.add("Indian");
                    subcategories.add("Kebabs");
                    subcategories.add("Other");
                } else if(catName.equals("Trades and Services")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Piano Teacher");
                    subcategories.add("Childminding and Babysitting");
                    subcategories.add("Dental");
                    subcategories.add("Life Coach");
                    subcategories.add("Plumber");
                    subcategories.add("Utilities");
                    subcategories.add("Window Cleaner");
                    subcategories.add("DIY");
                    subcategories.add("Other");
                } else if(catName.equals("Leisure and Lifestyle")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Leisure Centre");
                    subcategories.add("Health Clubs");
                    subcategories.add("Gyms");
                    subcategories.add("Personal Trainers");
                    subcategories.add("Spas");
                    subcategories.add("Other");
                } else if(catName.equals("Retail")) {
                    subcategories.add("Sub Category");
                    subcategories.add("Car Dealerships");
                    subcategories.add("Bathrooms");
                    subcategories.add("Beds/Mattresses");
                    subcategories.add("FirePlaces");
                    subcategories.add("Vaping");
                    subcategories.add("Opticians");
                    subcategories.add("Clothing and Footwear");
                    subcategories.add("Kitchen Design");
                    subcategories.add("Furniture");
                    subcategories.add("Golf Shop");
                    subcategories.add("Hot Tubs");
                    subcategories.add("Lighting");
                    subcategories.add("Estate and Letting Agents");
                    subcategories.add("Dental");
                    subcategories.add("Shoe Shop");
                    subcategories.add("Home Furnishing");
                    subcategories.add("Bridal");
                    subcategories.add("Bicycle Shope");
                    subcategories.add("Skate Shop");
                    subcategories.add("Motorcycles");
                    subcategories.add("Other");
                }else if(catName.equals("Bars and Clubs")) {
                }else if (catName.equals("Hotel")) {
                    subcategories.add("Sub Category");
                    subcategories.add("ApartHotel");
                    subcategories.add("Other");
                } else if (spinnerCategoryList.equals("Other")) {
                }

                ArrayAdapter<String> subcategoryAdapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.spinner_item, subcategories);

                subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sub_spinner.setAdapter(subcategoryAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        sub_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        places = findViewById(R.id.places);
        adapter = new MyPlacesAdapter(FilterActivity.this);
        places.setAdapter(adapter);
        // text changed listener to get results precisely according to our search
        places.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //calling getfilter to filter the results
                adapter.getFilter().filter(s);
               //notify the adapters after results changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // handling click of autotextcompleteview items
        places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyGooglePlaces googlePlaces = (MyGooglePlaces) parent.getItemAtPosition(position);
                places.setText(googlePlaces.getName());
            }
        });


        bt_find_deal = findViewById(R.id.bt_find_deal);
        bt_find_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilterActivity.this, DealsActivity.class);
                startActivity(intent);
            }
        });

     /*   // Spinner element
        Spinner deals_spinner = findViewById(R.id.category_spinner);
        Spinner subcategory_spinner = findViewById(R.id.subcategory_spinner);
        // Spinner click listener
        deals_spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Category...");
        categories.add("Restaurant");
        categories.add("Activity");
        categories.add("Health and Beauty");
        categories.add("Take-Away");
        categories.add("Trades and Services");
        categories.add("Leisure and Lifestyle");
        categories.add("Retail");
        categories.add("Bars and Clubs");
        categories.add("Hotel");
        categories.add("Other");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        deals_spinner.setAdapter(dataAdapter);


        // Spinner element
        Spinner sub_spinner = findViewById(R.id.subcategory_spinner);
        // Spinner click listener
        subcategory_spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> subcategories = new ArrayList<String>();
        if (categories.equals("Restaurant")) {
            subcategories.add("Sub Category");
            subcategories.add("Asian");
            subcategories.add("British");
            subcategories.add("Chinese");
            subcategories.add("French");
            subcategories.add("Greek");
            subcategories.add("Indian");
            subcategories.add("Italian");
            subcategories.add("Japanese");
            subcategories.add("Mediterranean");
            subcategories.add("Spanish");
            subcategories.add("Others");
        } else if (categories.equals("Activity")) {
            subcategories.add("Sub Category");
            subcategories.add("Golf");
            subcategories.add("Skiing");
            subcategories.add("Soft-Play");
            subcategories.add("Others");
        } else if (categories.equals("Health and Beauty")) {
            subcategories.add("Sub Category");
            subcategories.add("Barbers");
            subcategories.add("Beauty Salon");
            subcategories.add("Hair Salon");
            subcategories.add("Health Spa");
            subcategories.add("Healthcare");
            subcategories.add("Other");
        } else if (categories.equals("Take-Away")) {
            subcategories.add("Sub Category");
            subcategories.add("Chinese");
            subcategories.add("Fish and Chips");
            subcategories.add("Indian");
            subcategories.add("Kebabs");
            subcategories.add("Other");
        } else if (categories.equals("Trades and Services")) {
            subcategories.add("Sub Category");
            subcategories.add("Piano Teacher");
            subcategories.add("Childminding and Babysitting");
            subcategories.add("Dental");
            subcategories.add("Life Coach");
            subcategories.add("Plumber");
            subcategories.add("Utilities");
            subcategories.add("Window Cleaner");
            subcategories.add("DIY");
            subcategories.add("Other");
        } else if (categories.equals("Leisure and Lifestyle")) {
            subcategories.add("Sub Category");
            subcategories.add("Leisure Centre");
            subcategories.add("Health Clubs");
            subcategories.add("Gyms");
            subcategories.add("Personal Trainers");
            subcategories.add("Spas");
            subcategories.add("Other");
        } else if (categories.equals("Retail")) {
            subcategories.add("Sub Category");
            subcategories.add("Car Dealerships");
            subcategories.add("Bathrooms");
            subcategories.add("Beds/Mattresses");
            subcategories.add("FirePlaces");
            subcategories.add("Vaping");
            subcategories.add("Opticians");
            subcategories.add("Clothing and Footwear");
            subcategories.add("Kitchen Design");
            subcategories.add("Furniture");
            subcategories.add("Golf Shop");
            subcategories.add("Hot Tubs");
            subcategories.add("Lighting");
            subcategories.add("Estate and Letting Agents");
            subcategories.add("Dental");
            subcategories.add("Shoe Shop");
            subcategories.add("Home Furnishing");
            subcategories.add("Bridal");
            subcategories.add("Bicycle Shope");
            subcategories.add("Skate Shop");
            subcategories.add("Motorcycles");
            subcategories.add("Other");
        } else if (categories.equals("Bars and Clubs")) {
        } else if (categories.equals("Hotel")) {
            subcategories.add("Sub Category");
            subcategories.add("ApartHotel");
            subcategories.add("Other");
        } else if (categories.equals("Other")) {
        } else {

        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subcategories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        subcategory_spinner.setAdapter(dataAdapter6);
*/
        //within_spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        within.add("1/2 Mile");
        within.add("1 Mile");
        within.add("3 Mile");
        within.add("5 Mile");
        within.add("10 Mile");
        within.add("20 Mile");
        within.add("30 Mile");
        within.add("40 Mile");
        within.add("50 Mile");
        within.add("75 Mile");
        within.add("100 Mile");
        within.add("150 Mile");
        within.add("200 Mile");
        within.add("250 Mile");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, within);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        within_spinner.setAdapter(dataAdapter1);
        within_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        // Spinner element
       // Spinner for_spinner = (Spinner) findViewById(R.id.for_spinner);
        // Spinner click listener
       // for_spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
       // List<String> forspinner = new ArrayList<String>();
        fors.add("Sunday");
        fors.add("Monday");
        fors.add("Tuesday");
        fors.add("Wednesday");
        fors.add("Thurseday");
        fors.add("Friday");
        fors.add("Saturday");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fors);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        for_spinner.setAdapter(dataAdapter2);
        for_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
//                        + "\n" + "currentlong = " + currentlong);

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
    @Override
    public boolean onOptionsItemSelected (android.view.MenuItem item){
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

