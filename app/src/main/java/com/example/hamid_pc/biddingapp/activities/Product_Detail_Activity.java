package com.example.hamid_pc.biddingapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hamid_pc.biddingapp.fragments.ProductDetailFragment;

public class Product_Detail_Activity extends SingleFragmentActivity {


    public static final String EXTRA_PRODUCT_ID =
            "com.example.hamid_pc.biddingapp.product.id";


    public static final String EXTRA_BUYER =
            "com.example.hamid_pc.biddingapp.product.buyer";
    public static final String EXTRA_PRODUCT_TITLE =
            "com.example.hamid_pc.biddingapp.product.title";

    public static final String EXTRA_PRODUCT_DESC =
            "com.example.hamid_pc.biddingapp.product.desc";

    public static final String EXTRA_PRODUCT_AMOUNT =
            "com.example.hamid_pc.biddingapp.product.amount";


    public static final String EXTRA_PHOTO_URL =
            "com.example.hamid_pc.biddingapp.photo.url";

    public static final String EXTRA_PRODUCT_TYPE =
            "com.example.hamid_pc.biddingapp.product.type";


    public static Intent NewIntent(Context context, String productId, String productTitle,
                                   String productDescription, int productAmount, String productType,
                                   String photoUrl, Boolean buyer) {
        Intent intent = new Intent(context, Product_Detail_Activity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        intent.putExtra(EXTRA_BUYER, buyer);
        intent.putExtra(EXTRA_PRODUCT_TITLE, productTitle);
        intent.putExtra(EXTRA_PRODUCT_DESC, productDescription);
        intent.putExtra(EXTRA_PRODUCT_AMOUNT, productAmount);
        intent.putExtra(EXTRA_PRODUCT_TYPE, productType);
        intent.putExtra(EXTRA_PHOTO_URL, photoUrl);
        return intent;

    }


    @Override
    protected Fragment createFragment() {
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        Boolean buyer = getIntent().getBooleanExtra(EXTRA_BUYER, false);
        String productTitle = getIntent().getStringExtra(EXTRA_PRODUCT_TITLE);
        String productDesc = getIntent().getStringExtra(EXTRA_PRODUCT_DESC);
        int productAmount = getIntent().getIntExtra(EXTRA_PRODUCT_AMOUNT, 0);
        String productType = getIntent().getStringExtra(EXTRA_PRODUCT_TYPE);
        String photoUrl = getIntent().getStringExtra(EXTRA_PHOTO_URL);
        return ProductDetailFragment.newInstance(productId, buyer, productTitle, productDesc,
                productAmount, productType, photoUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
