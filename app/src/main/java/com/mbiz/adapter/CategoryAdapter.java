package com.mbiz.adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.DetailsActivity;
import com.mbiz.R;
import com.mbiz.model.Category;
import com.mbiz.model.Deals;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Aquad on 18/03/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>  {

//    private final ClickListener listener;
    private Context mCtx;
    private List<Category> categoryList;

    //getting the context and product list with constructor
    public CategoryAdapter(List<Category> categoryList, Context mCtx) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
       // this.listener = listener;
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_category, null);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {
        //getting the product of the specified position
        Category category = categoryList.get(position);
        holder.name.setText(category.getName());
        holder.image.setImageDrawable(mCtx.getResources().getDrawable(category.getImage()));

       // Picasso.with(mCtx).load(deals.getImage1()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;
//        private WeakReference<View.OnClickListener> listenerRef;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
         //   itemView.setOnClickListener(this);
         //   image.setOnClickListener(this);
        }

/*        @Override
        public void onClick(View view) {
            if (view.getId() == image.getId()) {

            }
            listener.onPositionClicked(getAdapterPosition());
        }     */
    }
}
