package com.mbiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.model.HomeRestaurant;
import com.mbiz.model.Restaurant;
import com.mbiz.model.Restaurant;

import java.util.List;

/**
 * Created by Aquad on 12/03/2018.
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
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_restaurant, null);
        return new RestaurantAdapter.RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.RestaurantViewHolder holder, int position) {
        //getting the product of the specified position
        Restaurant restaurant = restaurantList.get(position);

        //binding the data with the viewholder views
        holder.name.setText(restaurant.getName());
        holder.offer.setText(restaurant.getOffer());
       // holder.price.setText(restaurant.getPrice());
        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        //holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.image.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getImage()));
        holder.mapicon.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getMapicon()));

    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }


    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView name, offer;
        ImageView image, mapicon;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            offer = itemView.findViewById(R.id.offer);
           // img_restaurant = (TextView) itemView.findViewById(R.id.price);
            //textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            //textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            image = (ImageView) itemView.findViewById(R.id.image);
            mapicon = itemView.findViewById(R.id.mapicon);
        }
    }
}
