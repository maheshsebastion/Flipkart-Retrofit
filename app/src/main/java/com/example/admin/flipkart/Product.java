package com.example.admin.flipkart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 14-08-2017.
 */

public class Product implements Parcelable {

    int productid;
    String productname;
    String price;
    String instock;
    String offer;
    String color;
    String imageurl;
    String bimageurl;
    String simageurl;
    String vimageurl;


    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBimageurl() {
        return bimageurl;
    }

    public void setBimageurl(String bimageurl) {
        this.bimageurl = bimageurl;
    }

    public String getSimageurl() {
        return simageurl;
    }

    public void setSimageurl(String simageurl) {
        this.simageurl = simageurl;
    }

    public String getVimageurl() {
        return vimageurl;
    }

    public void setVimageurl(String vimageurl) {
        this.vimageurl = vimageurl;
    }

    public Product() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productid);
        dest.writeString(this.productname);
        dest.writeString(this.price);
        dest.writeString(this.instock);
        dest.writeString(this.offer);
        dest.writeString(this.color);
        dest.writeString(this.imageurl);
        dest.writeString(this.bimageurl);
        dest.writeString(this.simageurl);
        dest.writeString(this.vimageurl);
    }

    protected Product(Parcel in) {
        this.productid = in.readInt();
        this.productname = in.readString();
        this.price = in.readString();
        this.instock = in.readString();
        this.offer = in.readString();
        this.color = in.readString();
        this.imageurl = in.readString();
        this.bimageurl = in.readString();
        this.simageurl = in.readString();
        this.vimageurl = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
