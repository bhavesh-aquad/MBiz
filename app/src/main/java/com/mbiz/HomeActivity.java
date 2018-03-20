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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.mbiz.adapter.RestaurantHomeAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.CategoryChild;
import com.mbiz.model.HomeRestaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class HomeActivity extends CustomActivity implements CustomActivity.ResponseCallback,
        NavigationView.OnNavigationItemSelectedListener {

    private static final Integer[] IMAGES = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private ImageView filter;
    private List<HomeRestaurant> restaurantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BannerSlider bannerSlider;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setResponseListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


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

        postCall(getApplicationContext(), AppConstants.HOME_URL, new RequestParams(), "Please wait...", 1);

    }

    private Context getContext() {
        return HomeActivity.this;
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            progress_bar.setVisibility(View.GONE);
            if (o.optInt("status") != 200) {
                MyApp.popMessage("MBiz", o.optString("message"), getContext());
                return;
            }

            JSONArray array = o.optJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                HomeRestaurant h = new HomeRestaurant();
                JSONObject oo = array.optJSONObject(i);
                h.setCategory_name(oo.optString("category_name"));
                h.setDisplay_order(oo.optString("display_order"));
                h.setIcon(oo.optString("icon"));
                h.setId(oo.optString("id"));
                h.setImage(oo.optString("image"));
                h.setParent(oo.optString("parent"));
                h.setParent_id(oo.optString("parent_id"));

//                List<CategoryChild> childList = new ArrayList<>();
//                JSONArray innerArr = oo.optJSONArray("child");
//                for (int j = 0; j < innerArr.length(); j++) {
//                    JSONObject io = innerArr.optJSONObject(i);
//                    CategoryChild c = new CategoryChild();
//                    c.setCategory_name(io.optString("category_name"));
//                    c.setChild(io.optString("child"));
//                    c.setDisplay_order(io.optString("display_order"));
//                    c.setIcon(io.optString("icon"));
//                    c.setId(io.optString("id"));
//                    c.setImage(io.optString("image"));
//                    c.setSlug(io.optString("slug"));
//                    c.setParent_id(io.optString("parent_id"));
//                    c.setParent(io.optString("parent"));
//
//                    childList.add(c);
//                }

//                h.setChild(childList);
                restaurantList.add(h);
                RestaurantHomeAdapter rvadapter = new RestaurantHomeAdapter(this, restaurantList);
                //setting adapter to recyclerview
                recyclerView.setAdapter(rvadapter);
            }
//            List<HomeRestaurant> list = new Gson().fromJson(o.optJSONArray("data").toString()
//                    , new TypeToken<List<HomeRestaurant>>() {
//                    }.getType());

//                Type listType = new TypeToken<List<HomeRestaurant>>() {
//                }.getType();
//                List<HomeRestaurant> posts = new GsonBuilder().create().fromJson(o.getJSONArray("data").toString(), listType);

            //creating recyclerview adapter

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
        progress_bar = findViewById(R.id.progress_bar);
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
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class).putExtra("number", 1);
            startActivity(i);

        } else if (id == R.id.nav_hotdeals) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class).putExtra("number", 2);
            startActivity(i);

        } else if (id == R.id.nav_featureddeals) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class).putExtra("number", 3);
            startActivity(i);

        } else if (id == R.id.nav_weekend_offer) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class).putExtra("number", 4);
            startActivity(i);

        } else if (id == R.id.nav_top10_offer) {
            Intent i = new Intent(HomeActivity.this, TodaysDealsActivity.class).putExtra("number", 3);
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


