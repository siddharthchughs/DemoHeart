package com.bpk.mydemopoc.TabAdapter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bpk.mydemopoc.DrawLineGraph;
import com.bpk.mydemopoc.MainActivity;
import com.bpk.mydemopoc.R;

public class BarDemoAdapter extends BaseAdapter {
    private Context mContext;
    MainActivity acain;
    DrawLineGraph drawViewGraph;


    public BarDemoAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(270, 270));
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
  //          imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
            R.drawable.right_icon, R.drawable.right_icon,
R.drawable.right_icon, R.drawable.right_icon,
R.drawable.right_icon, R.drawable.right_icon,
R.drawable.right_icon, R.drawable.right_icon,
R.drawable.right_icon, R.drawable.right_icon,

    };
}