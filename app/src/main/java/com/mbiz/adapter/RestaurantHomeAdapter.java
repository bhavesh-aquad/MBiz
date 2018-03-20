package com.mbiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.AllDealsActivity;
import com.mbiz.DealsActivity;
import com.mbiz.HomeActivity;
import com.mbiz.R;
import com.mbiz.RestaurantActivity;
import com.mbiz.application.AppConstants;
import com.mbiz.model.HomeRestaurant;
import com.mbiz.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aquad on 26-12-2017.
 */

public class RestaurantHomeAdapter extends RecyclerView.Adapter<RestaurantHomeAdapter.RestaurantViewHolder> {

    //  private final View.OnClickListener mItemClick;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<HomeRestaurant> restaurantList;

    //getting the context and product list with constructor
    public RestaurantHomeAdapter(Context mCtx, List<HomeRestaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
        // this.mItemClick = mItemClick;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_home, null);
        RestaurantViewHolder holder = new RestaurantViewHolder(view);
        //   view.setOnClickListener(mItemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        //getting the product of the specified position
        HomeRestaurant restaurant = restaurantList.get(position);

        //binding the data with the viewholder views
        holder.rest_name.setText(restaurant.getCategory_name());
        //holder.price.setText(restaurant.getPrice());
        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        //holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        Picasso.with(mCtx).load(AppConstants.IMAGE_BASE_URL + restaurant.getImage()).into(holder.imageView);
        Picasso.with(mCtx).load(AppConstants.IMAGE_BASE_URL + restaurant.getIcon()).into(holder.icon);

        //  holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getImage()));
        //  holder.icon.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getIcon()));

    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }


    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView rest_name;
        ImageView imageView, icon;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            rest_name = (TextView) itemView.findViewById(R.id.name);
            //price = (TextView) itemView.findViewById(R.id.price);
            //textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            //textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            icon = itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    item data reference
                    Object o = restaurantList.get(getLayoutPosition());
                    if (getLayoutPosition() == 0)
                        mCtx.startActivity(new Intent(mCtx, AllDealsActivity.class));
                    else if (getLayoutPosition() == 1)
                        mCtx.startActivity(new Intent(mCtx, AllDealsActivity.class));
                    else
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                }
            });
        }
    }
}
