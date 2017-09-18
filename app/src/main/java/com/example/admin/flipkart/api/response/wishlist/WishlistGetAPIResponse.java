package com.example.admin.flipkart.api.response.wishlist;

import com.example.admin.flipkart.models.Products;

import java.util.List;

/**
 * Created by Admin on 15-09-2017.
 */

public class WishlistGetAPIResponse {
    /**
     * success : true
     * message : Product List
     * products : [{"id":11,"name":"Dell Laptop","description":"","spec":"<p>6 GB DDR4 RAM , 64 bit Windows 10 Operating System ,15.6 inch Display.<\/p>","quantity":30,"sku":"","regular_price":"39999.00","sale_price":"39999.00","shipping_price":"100.00","delivery_days":"3-4 days","status":1,"is_in_stock":1,"is_taxable":1,"is_featured":1,"images":[{"id":60,"product_id":11,"name":"1503060104_71rqUkQnZeL._SL1500_.jpg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":70,"product_id":11,"name":"1504527786_71rqUkQnZeL._SL1500_.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":72,"product_id":11,"name":"1504527786_Dell_Inspiron_3558_4_0.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":73,"product_id":11,"name":"1504527787_dell_i3567_5820blk_inspiron_pro_i5_7200u_1346925.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":126,"product_id":11,"name":"1505302658_ff4.jpg","path":"storage/uploads/images/products","type":"BANNER"}]}]
     */

    private boolean success;
    private String message;
    private List<Products> products;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}
