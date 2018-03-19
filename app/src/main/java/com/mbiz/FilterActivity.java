package com.mbiz;

import android.content.Intent;
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
import com.mbiz.model.Deals;
import com.mbiz.model.MyGooglePlaces;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button bt_find_deal, bt_reset;

    AutoCompleteTextView places;
    MyPlacesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Filter");


        places=(AutoCompleteTextView)findViewById(R.id.places);
        adapter=new MyPlacesAdapter(FilterActivity.this);
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
                MyGooglePlaces googlePlaces=(MyGooglePlaces)parent.getItemAtPosition(position);
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

/*        bt_reset = findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });                             */

        // Spinner element
        Spinner deals_spinner = (Spinner) findViewById(R.id.deals_spinner);
        // Spinner click listener
        deals_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("All Deals...");
        categories.add("Restaurant");
        categories.add("Activities");
        categories.add("Health and Beauty");
        categories.add("Takeaways");
        categories.add("Trades and Services");
        categories.add("Retail");
        categories.add("Pubs and Clubs");
        categories.add("Hotels");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        deals_spinner.setAdapter(dataAdapter);


        // Spinner element
        Spinner deals_sub_spinner = (Spinner) findViewById(R.id.deals_sub_spinner);
        // Spinner click listener
        deals_sub_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> subcategories = new ArrayList<String>();
        subcategories.add("Sub Categories...");
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

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subcategories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        deals_sub_spinner.setAdapter(dataAdapter6);


        // Spinner element
        Spinner within_spinner = (Spinner) findViewById(R.id.within_spinner);
        // Spinner click listener
        within_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> within = new ArrayList<String>();
        within.add("1/2 Mile");
        within.add("1 Mile");
        within.add("2 Mile");
        within.add("3 Mile");
        within.add("4 Mile");
        within.add("5 Mile");
        within.add("6 Mile");
        within.add("7 Mile");
        within.add("8 Mile");
        within.add("9 Mile");
        within.add("10 Mile");
        within.add("15 Mile");
        within.add("20 Mile");
        within.add("25 Mile");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, within);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        within_spinner.setAdapter(dataAdapter1);


        // Spinner element
        Spinner for_spinner = (Spinner) findViewById(R.id.for_spinner);
        // Spinner click listener
        for_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> forspinner = new ArrayList<String>();
        forspinner.add("Sunday");
        forspinner.add("Monday");
        forspinner.add("Tuesday");
        forspinner.add("Wednesday");
        forspinner.add("Thurseday");
        forspinner.add("Friday");
        forspinner.add("Saturday");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, forspinner);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        for_spinner.setAdapter(dataAdapter2);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
