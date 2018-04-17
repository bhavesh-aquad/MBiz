package com.mbiz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mbiz.adapter.PagerDeals;
import com.mbiz.fragments.ActivitiesFragement;
import com.mbiz.fragments.RestaurantFragment;
import com.mbiz.fragments.TakeawaysFragment;

public class TodaysDealsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    //This is our tablayout
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.restaurant,
            R.drawable.activities,
            R.drawable.takeaway,
            R.drawable.bar,
            R.drawable.retail,
            R.drawable.trader_services,
            R.drawable.hotel,
            R.drawable.other
    };
    RestaurantFragment restaurantFragment;
    ActivitiesFragement activitiesFragment;
    TakeawaysFragment takeawaysFragment;
    //This is our viewPager
    private ViewPager viewPager;
    PagerDeals adapter;
    public int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_deals);
        number = getIntent().getIntExtra("number", 0);
        //Initializing the tablayout
        tabLayout = findViewById(R.id.tabLayout);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.side_toolbar_title);
        mTitle.setText("Deals");
        actionBar.setTitle("");


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Restaurant"));
        tabLayout.addTab(tabLayout.newTab().setText("Activities"));
        tabLayout.addTab(tabLayout.newTab().setText("Takeaway"));
        tabLayout.addTab(tabLayout.newTab().setText("Bar"));
        tabLayout.addTab(tabLayout.newTab().setText("Retail"));
        tabLayout.addTab(tabLayout.newTab().setText("Trade and Services"));
        tabLayout.addTab(tabLayout.newTab().setText("Hotel"));
        tabLayout.addTab(tabLayout.newTab().setText("Other"));

        tabLayout.setTabGravity(TabLayout.SCROLL_AXIS_HORIZONTAL);

        //Initializing viewPager
        viewPager = findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Creating our pager adapter
        restaurantFragment = new RestaurantFragment();
        activitiesFragment = new ActivitiesFragement();
        takeawaysFragment = new TakeawaysFragment();
        adapter = new PagerDeals(getSupportFragmentManager(), tabLayout.getTabCount(), restaurantFragment, activitiesFragment, takeawaysFragment);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
        tabLayout.getTabAt(7).setIcon(tabIcons[7]);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        Fragment f = adapter.getItem(tab.getPosition());
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, f)
//                .commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
