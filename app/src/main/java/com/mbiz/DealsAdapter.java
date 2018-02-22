package com.mbiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Aquad on 26-12-2017.
 */
public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    private Context mCtx;

    ImageLoader imageLoader;

    //we are storing all the products in a list
    private List<Deals> dealsList;

    //getting the context and product list with constructor
    public DealsAdapter(List<Deals> dealsList, Context mCtx) {
        //super(mCtx, R.layout.layout_deals, dealsList);
        this.mCtx =  mCtx;
        this.dealsList = dealsList;
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        //View view = inflater.inflate(R.layout.layout_deals, parent, false);
        //DealsViewHolder holder = new DealsViewHolder(view);
        //return holder;


        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_deals, null);
        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, int position) {
        //getting the product of the specified position
        Deals deals = dealsList.get(position);

        //binding the data with the viewholder views
        holder.rest_name.setText(deals.getName());
        holder.offer.setText(deals.getTitle());

        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        //holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        /*if (deals.has("image")) {
            Picasso.with(mCtx).load(deals.get("image")).into(R.id.image_view);
        }*/
        Picasso.with(mCtx).load(deals.getImage1()).into(holder.imageView);
        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(deals.getImage1()));

    }

  /*  @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/


    @Override
    public int getItemCount() {
        return dealsList.size();
    }


    class DealsViewHolder extends RecyclerView.ViewHolder {

        TextView rest_name, offer;
        ImageView imageView;

        public DealsViewHolder(View itemView) {
            super(itemView);

            rest_name =  itemView.findViewById(R.id.rest_name);
            offer =  itemView.findViewById(R.id.offer);
            //textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            //textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView =  itemView.findViewById(R.id.imageView);
        }
    }
}

/*private List<DummyPropertyItem> listdata;
private LayoutInflater inflater;
private ItemClickCallback itemclickcallback;
private int count = 0;
private Context context;
public interface ItemClickCallback {
    void onItemClick(int p);

    void onSecondaryIconClick(int p);

}

    public void SetItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemclickcallback = itemClickCallback;
    }


    public PropertyListAdapter(List<DummyPropertyItem> listdata, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listdata = listdata;
        this.context = c;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.property_list_item, parent, false);
        return new DataHolder(view);

    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        DummyPropertyItem item = listdata.get(position);



        holder.tv_views.setText(item.getTotalViews());
        holder.tv_requested_info.setText(item.getTotalRequest());
        holder.tv_req_appointment.setText(item.getTotalAppointments());
        holder.tv_visited.setText(item.getTotalVisitors());
        holder.tv_offers.setText(item.getTotalOffers());
        holder.tv_address_detail.setText(item.getDetailAddsress());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


class DataHolder extends RecyclerView.ViewHolder {
    TextView tv_views, tv_certified, tv_requested_info, tv_badge;
    ImageView img_certified, img_badge;
    TextView tv_req_appointment, tv_visited, tv_offers, tv_address_detail;

    public DataHolder(final View itemView) {
        super(itemView);

        img_certified=(ImageView)itemView.findViewById(R.id.img_certified);
        img_badge=(ImageView)itemView.findViewById(R.id.img_badge);

        tv_views=(TextView)itemView.findViewById(R.id.tv_views);
        tv_certified=(TextView)itemView.findViewById(R.id.tv_certified);
        tv_requested_info=(TextView)itemView.findViewById(R.id.tv_requested_info);
        tv_badge=(TextView)itemView.findViewById(R.id.tv_badge);


        tv_req_appointment=(TextView)itemView.findViewById(R.id.tv_req_appointment);
        tv_visited=(TextView)itemView.findViewById(R.id.tv_visited);
        tv_offers=(TextView)itemView.findViewById(R.id.tv_offers);
        tv_address_detail=(TextView)itemView.findViewById(R.id.tv_address_detail);



    }


}

    public void setListData(ArrayList<DummyPropertyItem> exerciseList) {
        this.listdata.clear();
        this.listdata.addAll(exerciseList);

    }*/
