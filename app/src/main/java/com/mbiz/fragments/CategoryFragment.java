package com.mbiz.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.mbiz.DealsActivity;
import com.mbiz.DealsallActivity;
import com.mbiz.DetailsActivity;
import com.mbiz.R;
import com.mbiz.RestaurantActivity;
import com.mbiz.adapter.CategoryAdapter;
import com.mbiz.adapter.DealsallAdapter;
import com.mbiz.model.Category;
import com.mbiz.model.Deals;
import com.mbiz.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    Context mCtx;
    ImageView image;
    TextView name;
    private List<Category> categoryList;
    private RecyclerView recyclerView;
    View.OnClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
//get the button view
        image = rootView.findViewById(R.id.image);
        //   recyclerView = rootView.findViewById(R.id.recyclerView);
        //  CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, mCtx);
        //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //   recyclerView.setAdapter(categoryAdapter);
        //set a onclick listener for when the button gets clicked
        image.setOnClickListener(new View.OnClickListener() {
            //Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), DealsActivity.class);
                startActivity(mainIntent);
            }
        });


        return rootView;

    }
}

/*    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing the productlist
        categoryList = new ArrayList<>();


        //adding some items to our list
        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));

        categoryList.add(
                new Category(
                        1,
                        " Restaurant",
                        R.drawable.img2));
    }
}
*/

/*        //creating recyclerview adapter
        CategoryAdapter adapter = new CategoryAdapter(categoryList, mCtx, new CategoryAdapter.ClickListener() {

            @Override
            public void onPositionClicked(int position) {
                // callback performed on click
                if(position==0){
                    Intent i = new Intent(mCtx, DetailsActivity.class);
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
*/











  /*
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
        img_restaurant = (ImageButton) getView().findViewById(R.id.img_restaurant);
        // set a onclick listener for when the button gets clicked
        img_restaurant.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        RestaurantActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}

*/








 /*       View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        //return inflater.inflate(R.layout.fragment_category, container, false);
        //get the button view

        img_restaurant = getView().findViewById(R.id.img_restaurant);
        return rootView;
    }

    img_restaurant.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent mainIntent = new Intent(getActivity(), RestaurantActivity.class);
        startActivity(mainIntent);
    }
    });
}
*/
