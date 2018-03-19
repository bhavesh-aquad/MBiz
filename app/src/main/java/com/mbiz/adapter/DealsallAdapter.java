package com.mbiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.model.Dealsall;

import java.util.List;

/**
 * Created by Aquad on 12/03/2018.
 */

public class DealsallAdapter extends RecyclerView.Adapter<DealsallAdapter.DealsallViewholder> {

    private Context mCtx;

    private List<Dealsall> dealsallList;

    public DealsallAdapter(Context mCtx, List<Dealsall> dealsallList){
        this.mCtx = mCtx;
        this.dealsallList = dealsallList;

    }

    @Override
    public DealsallAdapter.DealsallViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(mCtx);
        View view = Inflater.inflate(R.layout.layout_deals_fragment, null);
        return new DealsallViewholder(view);
    }

    @Override
    public void onBindViewHolder(DealsallViewholder holder, int position) {
        Dealsall dealsall = dealsallList.get(position);

        holder.name.setText(dealsall.getName());
        holder.offer.setText(dealsall.getOffer());
        holder.image.setImageDrawable(mCtx.getResources().getDrawable(dealsall.getImage()));
        holder.mapicon.setImageDrawable(mCtx.getResources().getDrawable(dealsall.getMapicon()));
    }

    @Override
    public int getItemCount(){
        return dealsallList.size();
    }

      class DealsallViewholder extends RecyclerView.ViewHolder{

        TextView name, offer;
        ImageView image, mapicon;

       public DealsallViewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            offer = (TextView) itemView.findViewById(R.id.offer);
            //textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            //textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            image = (ImageView) itemView.findViewById(R.id.image);
            mapicon = itemView.findViewById(R.id.mapicon);
        }

    }
}
