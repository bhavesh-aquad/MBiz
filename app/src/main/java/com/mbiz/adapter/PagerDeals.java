package com.mbiz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mbiz.fragments.ActivitiesFragement;
import com.mbiz.fragments.CategoryFragment;
import com.mbiz.fragments.DealsFragment;
import com.mbiz.fragments.HotelsFragment;
import com.mbiz.fragments.OtherFragment;
import com.mbiz.fragments.PubsFragment;
import com.mbiz.fragments.RestaurantFragment;
import com.mbiz.fragments.RetailsFragment;
import com.mbiz.fragments.TakeawaysFragment;
import com.mbiz.fragments.TradeFragment;
import com.mbiz.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aquad on 15/03/2018.
 */

public class PagerDeals extends FragmentPagerAdapter {
    /**
     * Created by Aquad on 01/03/2018.
     */

//    private final List<Fragment> restaurantList = new ArrayList<>();
    //integer to count number of tabs
    int tabCount;
    RestaurantFragment restaurantFragment;
    ActivitiesFragement activitiesFragment;
    TakeawaysFragment takeawaysFragment;

    //Constructor to the class
    public PagerDeals(FragmentManager fm, int tabCount, RestaurantFragment restaurantFragment, ActivitiesFragement activitiesFragment,
                      TakeawaysFragment takeawaysFragment) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
        this.restaurantFragment = restaurantFragment;
        this.activitiesFragment = activitiesFragment;
        this.takeawaysFragment = takeawaysFragment;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            // ab dekho ak apan ko ye karna hai k only restaurent wali tab k liye loading dialog show karna hai thik hai
            case 0:
                return restaurantFragment;
            case 1:
                return activitiesFragment;
            case 2:
                return takeawaysFragment;
            case 3:
                TradeFragment tradeFragment = new TradeFragment();
                return tradeFragment;
            case 4:
                RetailsFragment retailsFragment = new RetailsFragment();
                return retailsFragment;
            case 5:
                PubsFragment pubsFragment = new PubsFragment();
                return pubsFragment;
            case 6:
                HotelsFragment hotelsFragment = new HotelsFragment();
                return hotelsFragment;
            case 7:
                OtherFragment otherFragment = new OtherFragment();
                return otherFragment;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}

