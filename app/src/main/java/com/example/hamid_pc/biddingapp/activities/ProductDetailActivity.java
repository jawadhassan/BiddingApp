package com.example.hamid_pc.biddingapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hamid_pc.biddingapp.fragments.ProductDetailFragment;

public class ProductDetailActivity extends SingleFragmentActivity {


    public static final String EXTRA_PRODUCT_ID =
            "com.example.hamid_pc.biddingapp.product.id";


    public static final String EXTRA_BUYER =
            "com.example.hamid_pc.biddingapp.product.buyer";

    public static Intent NewIntent(Context context, String productId, Boolean buyer) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        intent.putExtra(EXTRA_BUYER, buyer);
        return intent;

    }


    @Override
    protected Fragment createFragment() {
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        Boolean buyer = getIntent().getBooleanExtra(EXTRA_BUYER, false);
        return ProductDetailFragment.newInstance(productId, buyer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
