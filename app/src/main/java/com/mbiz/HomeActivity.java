package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mbiz.adapter.RestaurantHomeAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.Deals;
import com.mbiz.model.HomeRestaurant;
import com.mbiz.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class HomeActivity extends CustomActivity implements CustomActivity.ResponseCallback,
        NavigationView.OnNavigationItemSelectedListener {

    // private Spinner spinner;
    private static final Integer[] IMAGES = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private ImageView  filter;
    // private ImageView search_btn;
    private List<HomeRestaurant> restaurantList;
    private RecyclerView recyclerView;
    private TextView logout;
    private BannerSlider bannerSlider;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setResponseListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        ImageButton mode_edit = header.findViewById(R.id.mode_edit);

        mode_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, EditActivity.class);
                startActivity(i);
            }
        });


        filter = findViewById(R.id.filter);
        //bt_magic_biz = findViewById(R.id.bt_magic_biz);
        // search_btn = findViewById(R.id.search_btn);
        // logout = findViewById(R.id.logout);
        init();

     /*   spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth(400);
        spinner.getPopupBackground();
        spinner.getBaseline();   */

    /*    logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setStatus(AppConstants.IS_LOGIN, false);
                Intent itent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(itent);
                finish();
            }
        });   */

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, FilterActivity.class);
                startActivity(i);
            }
        });


 /*       bt_magic_biz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllDealsActivity.class);
                startActivity(intent);
            }
        });           */

      /*  search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DealsActivity.class);
                startActivity(intent);
            }
        });  */

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        restaurantList = new ArrayList<>();
        loadRecyclerViewData();
    }
    private void loadRecyclerViewData() {

        postCall(getApplicationContext(), AppConstants.HOME_URL , new RequestParams(), "Please wait...", 1);

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
                    JSONObject homerestaurant = dataArray.getJSONObject(i);
                    HomeRestaurant add_restaurant = new HomeRestaurant(
                            homerestaurant.getInt("id"),
                            homerestaurant.getString("category_name"),
                            homerestaurant.getString("image"),
                            homerestaurant.getString("icon"));
                    restaurantList.add(add_restaurant);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    /*
        //adding some items to our list
        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Restaurant",
                        R.drawable.img2,
                        R.drawable.restaurant));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Activities",
                        R.drawable.img1,
                        R.drawable.activities));
        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Takeaways",
                        R.drawable.img2,
                        R.drawable.takeaway));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Trade and Services",
                        R.drawable.img3,
                        R.drawable.trader_services));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Retails",
                        R.drawable.img4,
                        R.drawable.retail));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Pubs and Clubs",
                        R.drawable.img4,
                        R.drawable.bar));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Hotels",
                        R.drawable.img2,
                        R.drawable.hotel));
        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Others",
                        R.drawable.img4,
                        R.drawable.other));



/*        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Rencho HomeRestaurant",
                        "$14",
                        R.drawable.img4));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Paul HomeRestaurant",
                        "$14",
                        R.drawable.img3));

        restaurantList.add(
                new HomeRestaurant(
                        1,
                        "Paul HomeRestaurant",
                        "$14",
                        R.drawable.img1));
*/

   /*     View.OnClickListener mItemClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = null;
                int position = restaurantList.get(getLayoutPosition());
                switch (position) {
                    case 0:
                        intent = new Intent(HomeActivity.this, RestaurantActivity.class);
                        break;
                    case 1:
                        intent = new Intent(HomeActivity.this, RestaurantActivity.class);
                        break;
                }
                if (intent != null) {
                    HomeActivity.this.startActivity(intent);
                }
            }
        };

        */

            //creating recyclerview adapter
            RestaurantHomeAdapter rvadapter = new RestaurantHomeAdapter(this, restaurantList);

            //setting adapter to recyclerview
            recyclerView.setAdapter(rvadapter);
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }


    private void init() {
        bannerSlider = findViewById(R.id.banner_slider);
        List<Banner> banners = new ArrayList<>();
        for (int i = 0; i < IMAGES.length; i++)
            banners.add(new DrawableBanner(IMAGES[i]));
        bannerSlider.setBanners(banners);

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }


    /*
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            startActivity(new Intent(getContext(), LoginActivity.class));
        finishAffinity();
        return super.onOptionsItemSelected(item);
    }*/

        private Context getContext() {
            return HomeActivity.this;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_todaysdeals) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_hotdeals) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_featureddeals) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_weekend_offer) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_top10_offer) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            //logout.setOnClickListener(new View.OnClickListener() {
            //@Override
            //  public void onClick(View view) {
            MyApp.setStatus(AppConstants.IS_LOGIN, false);
            Intent itent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(itent);
            finish();
            // }
            // });

//        } else if (id == R.id.nav_share) {

//        } else if (id == R.id.nav_send) {
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
}


