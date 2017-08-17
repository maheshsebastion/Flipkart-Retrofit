package com.example.admin.flipkart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14-08-2017.
 */

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.MyHolder>{

    private ArrayList<Product> list = new ArrayList<Product>();
    Context mContext;

    //Changes
    public Recycleradapter(Context mContext, ArrayList<Product> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Product product = list.get(position);
        holder.name.setText(product.getProductname());
        holder.color.setText(product.getColor());
        String image1 = product.getImageurl();
        Glide.with(mContext)
                .load(image1)
                .into(holder.image);
        holder.price.setText("INR "+product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),SingleItemDescription.class);
                intent.putExtra("products",list);
                intent.putExtra("position",position);
                v.getContext().startActivity(intent);

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
