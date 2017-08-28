package com.example.admin.flipkart.product.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.products.Products;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;
import java.util.List;

public class ProductDescriptionActivity extends AppCompatActivity {
    TouchImageView productImage;
    TextView price,name,delivery;

    private List<Products> pList;
    int position,gImagePos;

    String imageURL;
    String iPath,iName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_product_description);

        productImage = (TouchImageView) findViewById(R.id.pImage);
        price = (TextView) findViewById(R.id.pPrice);
        name = (TextView) findViewById(R.id.pName);
        delivery = (TextView) findViewById(R.id.pDelivery);

        //getting DATA and POSITION using Parcelable
        Log.i("TAG2","BEFORE RECIEVAL OF PARCELABLE");
        pList = (ArrayList) getIntent().getParcelableArrayListExtra("products");
        position = getIntent().getIntExtra("position",00);

//        gImagePos = pList.get(position).getGalleryImages();

        //getting Featured Images from Products model and put it on glide
        Glide.with(this)
                .load(pList.get(position).getFeaturedImages().getImageUrl())
                .into(productImage);


        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);

        //GETTING and STORING the GALLERY IMAGES here
        ArrayList gallery = pList.get(position).getGalleryImages();

        for(int i=0; i<pList.get(position).getGalleryImages().size();i++)
        {
            final String url= (String) gallery.get(i);

            final ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(250, 200));

            Glide.with(this)
                    .load(url)
                    .into(imageView);
            layout.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //getting the image from one imageview and put it on another imageview in same activity
                    productImage.setImageDrawable(imageView.getDrawable());
                }
            });

        }

        price.setText(pList.get(position).getRegularPrice());
        name.setText(pList.get(position).getName());
        delivery.setText(pList.get(position).getDeliveryDays());



    }

}
