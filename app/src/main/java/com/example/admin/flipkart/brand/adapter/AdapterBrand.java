package com.example.admin.flipkart.brand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.brand.Brand;

import java.util.ArrayList;

/**
 * Created by Admin on 01-09-2017.
 */

public class AdapterBrand extends BaseAdapter {

    Context context;
    public ArrayList<Brand> brandList;
    LayoutInflater blayoutInflater;

    public AdapterBrand(ArrayList<Brand> brandList){
        this.context = context;
        this.brandList = brandList;
    }

    @Override
    public int getCount() {
        return brandList.size();
    }

    @Override
    public Object getItem(int position) {
        return brandList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = blayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_brand, viewGroup, false);
        Brand brand = brandList.get(position);
        TextView tv_brand = (TextView) view.findViewById(R.id.brandITEM);
        tv_brand.setText(brand.getName());

        return view;
    }
}
