package com.mbiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.model.Restaurant;

import java.util.List;

/**
 * Created by Aquad on 26-12-2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Restaurant> restaurantList;

    //getting the context and product list with constructor
    public RestaurantAdapter(Context mCtx, List<Restaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_restaurant, null);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        //getting the product of the specified position
        Restaurant restaurant = restaurantList.get(position);

        //binding the data with the viewholder views
        holder.rest_name.setText(restaurant.getName());
        holder.price.setText(restaurant.getPrice());
        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        //holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getImage()));

    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }


    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView rest_name, price;
        ImageView imageView;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            rest_name = (TextView) itemView.findViewById(R.id.rest_name);
            price = (TextView) itemView.findViewById(R.id.price);
            //textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            //textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
