package com.example.hamid_pc.biddingapp.FirebaseRecyclerViewAdapters;


import android.content.Context;

import com.google.firebase.database.Query;

public class BuyProductListAdapter extends ProductListAdapter {

    public BuyProductListAdapter(Query query, Context context) {
        super(query, context);
    }


}
