package com.mbiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.model.Deals;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aquad on 26-12-2017.
 */
public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    private Context mCtx;
    private List<Deals> dealsList;

    //getting the context and product list with constructor
    public DealsAdapter(List<Deals> dealsList, Context mCtx) {
        this.mCtx = mCtx;
        this.dealsList = dealsList;
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_deals, null);
        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, int position) {
        //getting the product of the specified position
        Deals deals = dealsList.get(position);
        holder.rest_name.setText(deals.getName());
        holder.offer.setText(deals.getTitle());
        Picasso.with(mCtx).load(deals.getImage1()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return dealsList.size();
    }

    class DealsViewHolder extends RecyclerView.ViewHolder {

        TextView rest_name, offer;
        ImageView imageView;

        public DealsViewHolder(View itemView) {
            super(itemView);

            rest_name = itemView.findViewById(R.id.rest_name);
            offer = itemView.findViewById(R.id.offer);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}