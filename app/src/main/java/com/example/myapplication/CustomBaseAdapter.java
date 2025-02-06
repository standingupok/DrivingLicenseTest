package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String listSignsShort[];
    String listSignsLong[];
    int listImages[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, String [] listSignsShort,String [] listSignsLong, int [] listImages){
        this.context = context;
        this.listSignsShort = listSignsShort;
        this.listSignsLong = listSignsLong;
        this.listImages = listImages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listSignsShort.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custome_list_view,null);
        TextView textViewShort = (TextView) convertView.findViewById(R.id.text_view_short);
        TextView textViewLong = (TextView) convertView.findViewById(R.id.text_view_long);
        ImageView imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
        textViewShort.setText(listSignsShort[position]);
        textViewLong.setText(listSignsLong[position]);
        imageViewIcon.setImageResource(listImages[position]);
        return convertView;
    }
}
