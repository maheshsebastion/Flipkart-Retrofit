package com.example.admin.flipkart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SingleItemDescription extends AppCompatActivity {

    ImageView productImage,fImage,bImage,sImage,vImage;
    TextView price,name,color;

    private List<Product> pList;
    int position;

    String imageURL,bimageURL,simageURL,vimageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_description);

        productImage = (ImageView) findViewById(R.id.pImage);
        fImage = (ImageView) findViewById(R.id.fImage);
        bImage = (ImageView) findViewById(R.id.bImage);
        sImage = (ImageView) findViewById(R.id.sImage);
        vImage = (ImageView) findViewById(R.id.vImage);
        price = (TextView) findViewById(R.id.pPrice);
        name = (TextView) findViewById(R.id.pName);
        color = (TextView) findViewById(R.id.pColor);

        //getting DATA and POSITION using Parcelable
        pList = (ArrayList) getIntent().getParcelableArrayListExtra("products");
        position = getIntent().getIntExtra("position",00);

        imageURL = pList.get(position).getImageurl();
        bimageURL = pList.get(position).getBimageurl();
        simageURL = pList.get(position).getSimageurl();
        vimageURL = pList.get(position).getVimageurl();
        Glide.with(this)
                .load(imageURL)
                .into(productImage);
        Glide.with(this)
                .load(imageURL)
                .into(fImage);
        Glide.with(this)
                .load(bimageURL)
                .into(bImage);
        Glide.with(this)
                .load(simageURL)
                .into(sImage);
        Glide.with(this)
                .load(vimageURL)
                .into(vImage);
       // productImage.setImageResource(pList.get(position).getProductid());
        price.setText(pList.get(position).getPrice());
        name.setText(pList.get(position).getProductname());
        color.setText(pList.get(position).getColor());



    }

}
