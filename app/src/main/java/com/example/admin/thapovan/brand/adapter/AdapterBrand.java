package com.example.admin.thapovan.brand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.thapovan.R;
import com.example.admin.thapovan.models.Brand;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 01-09-2017.
 */

public class AdapterBrand extends BaseAdapter {

    @BindView(R.id.brandITEM) TextView tv_brand;

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

        //Butter Knife binding this activity.....should come after above view statement.
        ButterKnife.bind(this,view);

        Brand brand = brandList.get(position);
        tv_brand.setText(brand.getName());

        return view;
    }
}
