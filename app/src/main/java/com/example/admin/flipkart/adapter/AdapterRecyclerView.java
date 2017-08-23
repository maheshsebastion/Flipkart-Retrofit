package com.example.admin.flipkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.activity.ActivityProductDescription;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.products.Products;

import java.util.ArrayList;

/**
 * Created by Admin on 14-08-2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyHolder>{

    private ArrayList<Products> list = new ArrayList<Products>();
    Context mContext;

    //Changes
    public AdapterRecyclerView(Context mContext, ArrayList<Products> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        Products products = list.get(position);
        holder.name.setText(products.getName());
        holder.color.setText(products.getDeliveryDays());

        //getting the Featured Images from Products model and put it on glide
        Glide.with(mContext)
                .load(products.getFeaturedImages().getImageUrl())
                .into(holder.image);
        holder.price.setText("INR "+ products.getRegularPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(),ActivityProductDescription.class);
                Log.i("TAG","BEFORE INTENT OF PARCELABLE");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("products",list);
                intent.putExtra("position",position);
                v.getContext().startActivity(intent);
                Log.i("TAG1","AFTER INTENT OF PARCELABLE");


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name, color,price;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.productimage);
            name = (TextView) itemView.findViewById(R.id.name);
            color = (TextView) itemView.findViewById(R.id.color);
            price= (TextView) itemView.findViewById(R.id.price);

        }


    }

}
