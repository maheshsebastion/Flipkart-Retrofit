package com.example.admin.flipkart.product.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.api.response.ProductAPIResponse;
import com.example.admin.flipkart.api.util.APIUtil;
import com.example.admin.flipkart.app.AppActivity;
import com.example.admin.flipkart.login.SessionManager;
import com.example.admin.flipkart.models.Cart;
import com.example.admin.flipkart.models.Products;
import com.thapovan.android.commonutils.toast.ToastUtil;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDescriptionActivity extends AppActivity {

    @BindView(R.id.pPrice)        TextView price;
    @BindView(R.id.pName)         TextView name;
    @BindView(R.id.sPrice)        TextView sprice;
    @BindView(R.id.specification) TextView specification;
    @BindView(R.id.pImage)        TouchImageView productImage;
    @BindView(R.id.linear)        LinearLayout layout;
    @BindView(R.id.nav_action)    Toolbar toolbar;

    private List<Products> pList;
    int position;

    Cart cart = new Cart();

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_product_description);

        //Butter Knife binding this activity.....
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        //gettingstoredJSON DATA and POSITION using Parcelable
        pList = (ArrayList) getIntent().getParcelableArrayListExtra(APIUtil.KEY_PRODUCTS);
        position = getIntent().getIntExtra(APIUtil.KEY_POSITION,00);

        //gettingstoredJSON Featured Images from Products model and put it on glide
        Glide.with(this)
                .load(pList.get(position).getFeaturedImages().getImageUrl())
                .into(productImage);

        //GETTING and STORING the GALLERY IMAGES here
        ArrayList gallery = pList.get(position).getGalleryImages();

        for(int i=0; i<pList.get(position).getGalleryImages().size();i++)
        {
            final String url= (String) gallery.get(i);

            final ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(10,0,10,0);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));

            Glide.with(this)
                    .load(url)
                    .into(imageView);
            layout.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //gettingstoredJSON the image from one imageview and put it on another imageview in same activity
                    productImage.setImageDrawable(imageView.getDrawable());
                }
            });

        }

        price.setText(pList.get(position).getRegularPrice());
        name.setText(pList.get(position).getName());
        sprice.setText(pList.get(position).getShippingPrice());
        specification.setText(Html.fromHtml(pList.get(position).getSpec()).toString());


    }

    @OnClick(R.id.btnADDTOCART)
    public void onAddToCartClicked(){

        if (sessionManager.isLoggedIn()){

            cart.setId(pList.get(position).getId());
            sessionManager.addProductToCart(cart);
            ToastUtil.showCenterToast(getApplicationContext(),"Added to cart "+cart.getId());
        }else {
            ToastUtil.showCenterToast(getApplicationContext(),"You need to login tour account");
            sessionManager.checkLogin();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout_menu_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
