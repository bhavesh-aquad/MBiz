package com.mbiz.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.application.AppConstants;
import com.mbiz.fragments.CategoryFragment;
import com.mbiz.model.CategoryChild;
import com.mbiz.model.Deals;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aquad on 18/03/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    //    private final ClickListener listener;
    private Fragment mCtx;
    private List<CategoryChild> listData;
    private List<Deals> dealsList;

    //getting the context and product list with constructor
    public CategoryAdapter(List<CategoryChild> listData, Fragment mCtx) {
        this.mCtx = mCtx;
        this.listData = listData;
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx.getActivity());
        View view = inflater.inflate(R.layout.layout_category, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {
        //getting the product of the specified position
        CategoryChild category = listData.get(position);
        holder.name.setText(category.getCategory_name());
//        holder.image.setImageResource(category.getImage());

        Picasso.with(mCtx.getActivity()).load(AppConstants.IMAGE_BASE_URL + category.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
//        private WeakReference<View.OnClickListener> listenerRef;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            //   itemView.setOnClickListener(this);
            //   image.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    item data reference
                    CategoryChild c = listData.get(getLayoutPosition());
//                    for (int i = 0; i <listData.size() ; i++) {
                    ((CategoryFragment) mCtx).callApi(c);

//                    if (getLayoutPosition() == 0)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    else if (getLayoutPosition() == 1)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    else if (getLayoutPosition() == 2)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    else if (getLayoutPosition() == 3)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    else if (getLayoutPosition() == 4)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    else
//                        mCtx.startActivity(new Intent(mCtx.getActivity(), DealsActivity.class));

//                    if (listData.get(getLayoutPosition()).getChild().size() > 0) {
//                        Object o = restaurantList.get(getLayoutPosition());
//                        if (getLayoutPosition() == 0) {
//                            mCtx.startActivity(new Intent(mCtx, AllDealsActivity.class));
//                        SingleInstance.getInstance().setSubcategoryChild().(listData.get(getLayoutPosition()).getChild());
//
//                        } else if (getLayoutPosition() == 1)
//                        mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                        else
//                            mCtx.startActivity(new Intent(mCtx, DealsActivity.class));
//                    } else {
//                        MyApp.popMessage("Message", "No data available", mCtx);
//                    }
//                    item data reference
                }
            });
        }

/*        @Override
        public void onClick(View view) {
            if (view.getId() == image.getId()) {

            }
            listener.onPositionClicked(getAdapterPosition());
        }     */
    }
}