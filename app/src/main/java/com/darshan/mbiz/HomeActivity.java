package com.darshan.mbiz;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.darshan.mbiz.R.drawable.logo1;
import static com.darshan.mbiz.R.styleable.MenuItem;

public class HomeActivity extends AppCompatActivity{

    Spinner spinner;
    SpinnerAdapter adapter;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ImageView bt_magic_biz;
    private ImageView search_btn;

    //a list to store all the products
    List<Restaurant> restaurantList;

    //the recyclerview
    RecyclerView recyclerView;

    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get action bar
        //ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        //actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_home);

        bt_magic_biz = (ImageView) findViewById(R.id.bt_magic_biz);
        search_btn = (ImageView) findViewById(R.id.search_btn);
        logout = (TextView) findViewById(R.id.logout);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);

        init();

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth(400);
        spinner.getPopupBackground();
        spinner.getBaseline();

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent itent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(itent);
            }
        });

        bt_magic_biz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
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


        /**
         * Converting dp to pixel
         */
        /** private int dpToPx(int dp) {
         Resources r = getResources();
         return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
         }**/
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(HomeActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}

