package com.mbiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.DealsActivity;
import com.mbiz.R;
import com.mbiz.fragments.DealsFragment;
import com.mbiz.model.Category;
import com.mbiz.model.Deals;
import com.mbiz.model.FDeals;

import java.util.List;

/**
 * Created by Aquad on 21/03/2018.
 */

public class FDealsAdapter extends RecyclerView.Adapter<FDealsAdapter.FDealsViewHolder> {

    //    private final ClickListener listener;
    private Context mCtx;
    private List<FDeals> fdealsList;

    //getting the context and product list with constructor
    public FDealsAdapter(List<FDeals> categoryList, Context mCtx) {
        this.mCtx = mCtx;
        this.fdealsList = categoryList;
        // this.listener = listener;
    }

    @Override
    public FDealsAdapter.FDealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_category, parent,false);
        return new FDealsAdapter.FDealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FDealsAdapter.FDealsViewHolder holder, int position) {
        //getting the product of the specified position
        FDeals fDeals = fdealsList.get(position);
        holder.name.setText(fDeals.getMname());
        holder.image.setImageResource(fdealsList.get(position).getMthumbnail());

        // Picasso.with(mCtx).load(deals.getImage1()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return fdealsList.size();
    }

    class FDealsViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
//        private WeakReference<View.OnClickListener> listenerRef;

        public FDealsViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            //   itemView.setOnClickListener(this);
            //   image.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    item data reference
                    Object o = fdealsList.get(getLayoutPosition());
//                    for(int i=0; i<fdealsList.size(); i++)
//                    ((DealsFragment) mCtx).callApi(o);
                    if (getLayoutPosition() == 0)
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                    else if (getLayoutPosition() == 1)
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                    else if (getLayoutPosition() == 2)
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                    else if (getLayoutPosition() == 3)
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                    else if (getLayoutPosition() == 4)
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                    else
                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
                }
            });
        }
    }
}
