package com.example.hamid_pc.biddingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.fragments.ProductEntryFragment;

public class ProductEntryActivity extends SingleFragmentActivity {


    public static Intent start(Context context) {
        Intent starter = new Intent(context, ProductEntryActivity.class);
        return starter;
    }

    @Override
    protected Fragment createFragment() {
        return ProductEntryFragment.newInstance(null, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
