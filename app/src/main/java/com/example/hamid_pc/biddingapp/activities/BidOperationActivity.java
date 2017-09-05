package com.example.hamid_pc.biddingapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hamid_pc.biddingapp.fragments.BidOperationFragment;

public class BidOperationActivity extends SingleFragmentActivity {

    public static final String EXTRA_PRODUCT_ID =
            "com.example.hamid_pc.biddingapp.product.id";

    public static final String EXTRA_AUCTION_ID =
            "com.example.hamid_pc.biddingapp.auction.id";

    public static Intent NewIntent(Context packageContext, String productId, String auctionId) {
        Intent intent = new Intent(packageContext, BidOperationActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        intent.putExtra(EXTRA_AUCTION_ID, auctionId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        String auctionId = getIntent().getStringExtra(EXTRA_AUCTION_ID);
        return BidOperationFragment.NewInstance(productId, auctionId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
