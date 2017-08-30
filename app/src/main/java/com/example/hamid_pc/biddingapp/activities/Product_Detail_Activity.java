package com.example.hamid_pc.biddingapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hamid_pc.biddingapp.fragments.ProductDetailFragment;

public class Product_Detail_Activity extends SingleFragmentActivity {


    public static final String EXTRA_PRODUCT_ID =
            "com.example.hamid_pc.biddingapp.product.id";


    public static void start(Context context, String productId) {
        Intent intent = new Intent(context, Product_Detail_Activity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);

    }


    @Override
    protected Fragment createFragment() {
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        return ProductDetailFragment.newInstance(productId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
