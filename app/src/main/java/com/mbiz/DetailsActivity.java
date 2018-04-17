package com.mbiz;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.loopj.android.http.RequestParams;
import com.mbiz.application.AppConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends CustomActivity implements OnMapReadyCallback, CustomActivity.ResponseCallback {

    // Google Map
    private GoogleMap mMap;

    private TextView name, description, deals, deals_description, tv_no, tv_web, tv_add, tv_brief;
    private int id;
    private CheckBox cb_mon_mo, cb_mon_lu, cb_mon_ev, cb_tue_mo, cb_tue_lu, cb_tue_ev, cb_wed_mo, cb_wed_lu, cb_wed_ev, cb_thu_mo, cb_thu_lu, cb_thu_ev, cb_fri_mo, cb_fri_lu, cb_fri_ev, cb_sat_mo, cb_sat_lu, cb_sat_ev, cb_sun_mo, cb_sun_lu, cb_sun_ev;

    private void setupCheckboxes() {
        cb_mon_mo = findViewById(R.id.cb_mon_mo);
        cb_mon_mo.setClickable(false);
        cb_mon_lu = findViewById(R.id.cb_mon_lu);
        cb_mon_lu.setClickable(false);
        cb_mon_ev = findViewById(R.id.cb_mon_ev);
        cb_mon_ev.setClickable(false);

        cb_tue_mo = findViewById(R.id.cb_tue_mo);
        cb_tue_mo.setClickable(false);
        cb_tue_lu = findViewById(R.id.cb_tue_lu);
        cb_tue_lu.setClickable(false);
        cb_tue_ev = findViewById(R.id.cb_tue_ev);
        cb_tue_ev.setClickable(false);

        cb_wed_mo = findViewById(R.id.cb_wed_mo);
        cb_wed_mo.setClickable(false);
        cb_wed_lu = findViewById(R.id.cb_wed_lu);
        cb_wed_lu.setClickable(false);
        cb_wed_ev = findViewById(R.id.cb_wed_ev);
        cb_wed_ev.setClickable(false);

        cb_thu_mo = findViewById(R.id.cb_thu_mo);
        cb_thu_mo.setClickable(false);
        cb_thu_lu = findViewById(R.id.cb_thu_lu);
        cb_thu_lu.setClickable(false);
        cb_thu_ev = findViewById(R.id.cb_thu_ev);
        cb_thu_ev.setClickable(false);

        cb_fri_mo = findViewById(R.id.cb_fri_mo);
        cb_fri_mo.setClickable(false);
        cb_fri_lu = findViewById(R.id.cb_fri_lu);
        cb_fri_lu.setClickable(false);
        cb_fri_ev = findViewById(R.id.cb_fri_ev);
        cb_fri_ev.setClickable(false);

        cb_sat_mo = findViewById(R.id.cb_sat_mo);
        cb_sat_mo.setClickable(false);
        cb_sat_lu = findViewById(R.id.cb_sat_lu);
        cb_sat_lu.setClickable(false);
        cb_sat_ev = findViewById(R.id.cb_sat_ev);
        cb_sat_ev.setClickable(false);

        cb_sun_mo = findViewById(R.id.cb_sun_mo);
        cb_sun_mo.setClickable(false);
        cb_sun_lu = findViewById(R.id.cb_sun_lu);
        cb_sun_lu.setClickable(false);
        cb_sun_ev = findViewById(R.id.cb_sun_ev);
        cb_sun_ev.setClickable(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResponseListener(this);
        setContentView(R.layout.activity_details);
        id = getIntent().getIntExtra("id", 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setupCheckboxes();
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        deals = findViewById(R.id.deals);
        deals_description = findViewById(R.id.deals_description);
        tv_add = findViewById(R.id.tv_add);
        tv_brief = findViewById(R.id.tv_brief);
        tv_no = findViewById(R.id.tv_no);
        tv_web = findViewById(R.id.tv_web);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        RequestParams p = new RequestParams();
        p.put("id", id);
        postCall(DetailsActivity.this, AppConstants.BASE_URL + "getdealbyid", p, "Loading details...", 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(55.378051, -3.435973);
        mMap.addMarker(new MarkerOptions().position(sydney).title(" "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // createMarker(LatLng);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

//        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) ||
//            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }





  /*      try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }   */

    /**
     * function to load map. If map is not created it will create it for you
     */
  /*  private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            String days = o.optJSONArray("data").optJSONObject(0).optString("activewhens");
            description.setText(o.optJSONArray("data").optJSONObject(0).optString("mintro"));
            char[] charArray = days.toCharArray();
            for (char d : charArray) {
                daysList.add(Character.toString(d));
            }

            setupDays();
        }
    }

    private void setupDays() {
        cb_mon_mo.setChecked(daysList.get(0).equals("0") ? false : true);
        cb_mon_lu.setChecked(daysList.get(1).equals("0") ? false : true);
        cb_mon_ev.setChecked(daysList.get(2).equals("0") ? false : true);

        cb_tue_mo.setChecked(daysList.get(3).equals("0") ? false : true);
        cb_tue_lu.setChecked(daysList.get(4).equals("0") ? false : true);
        cb_tue_ev.setChecked(daysList.get(5).equals("0") ? false : true);

        cb_wed_mo.setChecked(daysList.get(6).equals("0") ? false : true);
        cb_wed_lu.setChecked(daysList.get(7).equals("0") ? false : true);
        cb_wed_ev.setChecked(daysList.get(8).equals("0") ? false : true);

        cb_thu_mo.setChecked(daysList.get(9).equals("0") ? false : true);
        cb_thu_lu.setChecked(daysList.get(10).equals("0") ? false : true);
        cb_thu_ev.setChecked(daysList.get(11).equals("0") ? false : true);

        cb_fri_mo.setChecked(daysList.get(12).equals("0") ? false : true);
        cb_fri_lu.setChecked(daysList.get(13).equals("0") ? false : true);
        cb_fri_ev.setChecked(daysList.get(14).equals("0") ? false : true);

        cb_sat_mo.setChecked(daysList.get(15).equals("0") ? false : true);
        cb_sat_lu.setChecked(daysList.get(16).equals("0") ? false : true);
        cb_sat_ev.setChecked(daysList.get(17).equals("0") ? false : true);

        cb_sun_mo.setChecked(daysList.get(18).equals("0") ? false : true);
        cb_sun_lu.setChecked(daysList.get(19).equals("0") ? false : true);
        cb_sun_ev.setChecked(daysList.get(20).equals("0") ? false : true);

    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }

    private List<String> daysList = new ArrayList<>();
}
