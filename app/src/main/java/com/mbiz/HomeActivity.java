package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mbiz.adapter.RestaurantAdapter;
import com.mbiz.application.AppConstants;
import com.mbiz.application.MyApp;
import com.mbiz.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class HomeActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final Integer[] IMAGES = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private ImageView bt_magic_biz;
    private ImageView search_btn;
    private List<Restaurant> restaurantList;
    private RecyclerView recyclerView;
    private TextView logout;
    private BannerSlider bannerSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_magic_biz = findViewById(R.id.bt_magic_biz);
        search_btn = findViewById(R.id.search_btn);
        logout = findViewById(R.id.logout);
        init();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth(400);
        spinner.getPopupBackground();
        spinner.getBaseline();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setStatus(AppConstants.IS_LOGIN, false);
                Intent itent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(itent);
            }
        });

        bt_magic_biz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DealsActivity.class);
                startActivity(intent);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DealsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        restaurantList = new ArrayList<>();

        //adding some items to our list
        restaurantList.add(
                new Restaurant(
                        1,
                        "Rencho Restaurant",
                        "$10",
                        R.drawable.img2));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Rencho Restaurant",
                        "$14",
                        R.drawable.img1));
        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$13",
                        R.drawable.img2));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Rencho Restaurant",
                        "$14",
                        R.drawable.img3));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$14",
                        R.drawable.img4));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$14",
                        R.drawable.img4));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$13",
                        R.drawable.img2));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Rencho Restaurant",
                        "$14",
                        R.drawable.img4));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$14",
                        R.drawable.img3));

        restaurantList.add(
                new Restaurant(
                        1,
                        "Paul Restaurant",
                        "$14",
                        R.drawable.img1));

        //creating recyclerview adapter
        RestaurantAdapter rvadapter = new RestaurantAdapter(this, restaurantList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(rvadapter);
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

    }

    private void init() {
        bannerSlider = findViewById(R.id.banner_slider);
        List<Banner> banners = new ArrayList<>();
        for (int i = 0; i < IMAGES.length; i++)
            banners.add(new DrawableBanner(IMAGES[i]));
        bannerSlider.setBanners(banners);

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            startActivity(new Intent(getContext(), LoginActivity.class));
        finishAffinity();
        return super.onOptionsItemSelected(item);
    }

    private Context getContext() {
        return HomeActivity.this;
    }
}

