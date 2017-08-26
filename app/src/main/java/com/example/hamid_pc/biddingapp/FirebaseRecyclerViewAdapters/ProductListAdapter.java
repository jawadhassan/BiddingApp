package com.example.hamid_pc.biddingapp.FirebaseRecyclerViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Hamid-PC on 8/26/2017.
 */

public class ProductListAdapter  {

    private Query mQuery;
    private FirebaseRecyclerAdapter<Product,ProductViewHolder> mAdapter;
    public ProductListAdapter(Query query) {

        mQuery = query;
        Update();
    }

    public void Update(){
        mAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(
                Product.class,
                R.layout.list_item_product,
                ProductViewHolder.class,
                mQuery
        ){
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

            }
        };


    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView

        public ProductViewHolder(View itemView) {
            super(itemView);

        }
    }


}
