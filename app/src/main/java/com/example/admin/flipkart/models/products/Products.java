package com.example.admin.flipkart.models.products;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14-08-2017.
 */

public class Products implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("spec")
    @Expose
    private String spec;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("shipping_price")
    @Expose
    private String shippingPrice;
    @SerializedName("delivery_days")
    @Expose
    private String deliveryDays;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_in_stock")
    @Expose
    private Integer isInStock;
    @SerializedName("is_taxable")
    @Expose
    private Integer isTaxable;
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("images")
    @Expose
    private List<Images> images = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(String shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(Integer isInStock) {
        this.isInStock = isInStock;
    }

    public Integer getIsTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(Integer isTaxable) {
        this.isTaxable = isTaxable;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public Images getFeaturedImages(){
        for(int i=0;i<images.size();i++) {
            if (images.get(i).getType().equals("FEATURED")) {

                //getting and returning only the featured images to the other classes
                return images.get(i);
            }
        }
        return null;
    }
    public ArrayList getGalleryImages(){

        ArrayList gallery=new ArrayList();

        for(int i=0;i<images.size();i++){

            if(images.get(i).getType().equals("GALLERY")){
                //getting and returning only the Gallery images to the other classes
                gallery.add(images.get(i).getImageUrl());
            }
        }
        return gallery;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.spec);
        dest.writeValue(this.quantity);
        dest.writeString(this.sku);
        dest.writeString(this.regularPrice);
        dest.writeString(this.salePrice);
        dest.writeString(this.shippingPrice);
        dest.writeString(this.deliveryDays);
        dest.writeValue(this.status);
        dest.writeValue(this.isInStock);
        dest.writeValue(this.isTaxable);
        dest.writeValue(this.isFeatured);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeList(this.images);
    }

    public Products() {
    }

    protected Products(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.spec = in.readString();
        this.quantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sku = in.readString();
        this.regularPrice = in.readString();
        this.salePrice = in.readString();
        this.shippingPrice = in.readString();
        this.deliveryDays = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isInStock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isTaxable = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isFeatured = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.images = new ArrayList<Images>();
        in.readList(this.images, Images.class.getClassLoader());
    }

    public static final Parcelable.Creator<Products> CREATOR = new Parcelable.Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel source) {
            return new Products(source);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
