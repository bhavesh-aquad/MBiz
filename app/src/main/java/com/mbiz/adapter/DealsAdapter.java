package com.mbiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mbiz.AllDealsActivity;
import com.mbiz.DealsallActivity;
import com.mbiz.DetailsActivity;
import com.mbiz.R;
import com.mbiz.application.MyApp;
import com.mbiz.model.Deals;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Aquad on 26-12-2017.
 */
public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    public void updateList(List<Deals> list) {
        dealsList = list;
        notifyDataSetChanged();
    }

    public interface ClickListener {

        void onPositionClicked(int position);

        void onLongClicked(int position);
    }

    private final ClickListener listener;
    private Context mCtx;
    private List<Deals> dealsList;

    //getting the context and product list with constructor
    public DealsAdapter(List<Deals> dealsList, Context mCtx, ClickListener listener) {
        this.mCtx = mCtx;
        this.dealsList = dealsList;
        this.listener = listener;
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
        holder.offer.setText(deals.getName());
        holder.rest_name.setText(deals.getMerchant_name());
        holder.rest_tag.setText(deals.getMtagline());

        try {
            holder.dist.setText("" + MyApp.distance(Double.parseDouble(deals.getMlatitude()),
                    Double.parseDouble(deals.getMlongitude()),
                    Double.parseDouble(MyApp.getSharedPrefString("CLAT")),
                    Double.parseDouble(MyApp.getSharedPrefString("CLONG"))));
        } catch (Exception e) {
        }
        //isme apan ko distance set karna hai
//        holder.add.setText(deals.getMflat());  ab tum batao current lat lng kahanikale
//        holder.add.setText(deals.getMbuilding());
//        holder.add.setText(deals.getMaddress1());ha//ni aa rha hai abhi bhipe
//        holder.add.setText(deals.getMaddress2());
//        holder.add.setText(deals.getMtown());
//        holder.add.setText(deals.getMcounty());
        holder.add.setText(deals.getMpostcode());
        holder.add.setText(deals.getMtown());
//        holder.add.setText(deals.getAddressAll());

        try {
            Picasso.with(mCtx).load(deals.getMthumbnail()).into(holder.imageView);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return dealsList.size();
    }

    class DealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rest_name, offer, rest_tag, dist, add;
        ImageView imageView;
        Button btn_viewdeals;
        private WeakReference<View.OnClickListener> listenerRef;

        public DealsViewHolder(View itemView) {
            super(itemView);

            offer = itemView.findViewById(R.id.offer);
            rest_name = itemView.findViewById(R.id.rest_name);
            rest_tag = itemView.findViewById(R.id.rest_tag);
            dist = itemView.findViewById(R.id.dist);
            add = itemView.findViewById(R.id.add);
            imageView = itemView.findViewById(R.id.imageView);
            btn_viewdeals = itemView.findViewById(R.id.btn_viewdeals);
            itemView.setOnClickListener(this);
            btn_viewdeals.setOnClickListener(this);
            btn_viewdeals.getLayoutParams();
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == btn_viewdeals.getId()) {

            }
            listener.onPositionClicked(getAdapterPosition());
        }
    }
}