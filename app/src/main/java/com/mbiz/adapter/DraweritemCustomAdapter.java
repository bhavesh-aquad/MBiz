package com.mbiz.adapter;

/**
 * Created by Aquad on 05/03/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbiz.R;
import com.mbiz.model.DataNavigationdrawer;

public class DraweritemCustomAdapter extends ArrayAdapter<DataNavigationdrawer> {

    Context mContext;
    int layoutResourceId;
    DataNavigationdrawer data[] = null;

    public DraweritemCustomAdapter(Context mContext, int layoutResourceId, DataNavigationdrawer[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        DataNavigationdrawer folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}
