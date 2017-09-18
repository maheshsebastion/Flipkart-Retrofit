package com.example.admin.flipkart.wishlist.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.Products;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 15-09-2017.
 */

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.MyHolder>{


    private ArrayList<Products> list = new ArrayList<Products>();
    Context mContext;

    MyWishlistClickListener wishlistClickListener;

    //Changes
    public AdapterWishlist(Context mContext, ArrayList<Products> list, MyWishlistClickListener wishlistClickListener) {
        this.list = list;
        this.mContext = mContext;
        this.wishlistClickListener = wishlistClickListener;
    }


    @Override
    public AdapterWishlist.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wishlist_item,parent,false);
        AdapterWishlist.MyHolder myHolder = new AdapterWishlist.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(AdapterWishlist.MyHolder holder, final int position) {

        Products products = list.get(position);
        holder.name.setText(products.getName());

        //getStoredJSON the Featured Images from Products model and put it on glide
        Glide.with(mContext)
                .load(products.getFeaturedImages().getImageUrl())
                .into(holder.image);
        holder.price.setText(products.getRegularPrice());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nameWishlist)            TextView name;
        @BindView(R.id.priceWishlist)           TextView price;
        @BindView(R.id.productimageWishlist)    ImageView image;

        @BindView(R.id.removefromWishlist)      TextView removeWishlist;
        @BindView(R.id.card_view_wishlist)      CardView cartCardView;


        public MyHolder(View itemView) {
            super(itemView);

            //Butter Knife binding this activity.....
            ButterKnife.bind(this,itemView);

            removeWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wishlistClickListener.onRemoveFromWishlist(getAdapterPosition());
                }
            });
            cartCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wishlistClickListener.onWishlistClicked(view,getAdapterPosition());
                }
            });

        }
    }
    public interface MyWishlistClickListener {
        void onRemoveFromWishlist(int position);
        void onWishlistClicked(View view,int position);
    }
}
