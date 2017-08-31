package com.example.hamid_pc.biddingapp.FirebaseRecyclerViewAdapters;

import android.content.Context;

import com.example.hamid_pc.biddingapp.ViewHolders.BuyProductViewHolder;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class ProductListAdapter extends FirebaseRecyclerAdapter<Product, BuyProductViewHolder> {

    private Query mQuery;
    private Context mContext;
    private FirebaseRecyclerAdapter<Product, BuyProductViewHolder> mAdapter;


    public ProductListAdapter(Class<Product> modelClass, int modelLayout, Class<BuyProductViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }


    @Override
    protected void populateViewHolder(BuyProductViewHolder viewHolder, Product model, int position) {


        viewHolder.mProductTitle.setText(model.getProductTitle());
        viewHolder.mProductDescription.setText(model.getProductDescription());
        Product product = getItem(position);
        viewHolder.bindView(product);


        Picasso.with(mContext)
                .load(model.getPhotoUrl())
                .into(viewHolder.mThumbnail);
    }



/*

*/



}
