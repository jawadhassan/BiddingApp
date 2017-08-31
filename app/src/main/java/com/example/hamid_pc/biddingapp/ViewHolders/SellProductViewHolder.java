package com.example.hamid_pc.biddingapp.ViewHolders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.activities.NavigationActivity;
import com.example.hamid_pc.biddingapp.activities.Product_Detail_Activity;
import com.example.hamid_pc.biddingapp.models.Product;

public class SellProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Product mProduct;
    public TextView mProductTitle;
    public TextView mProductDescription;

    public ImageView mThumbnail;

    public SellProductViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mProductTitle = (TextView) itemView.findViewById(R.id.product_title);
        mProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        mThumbnail = (ImageView) itemView.findViewById(R.id.list_image);


    }


    @Override
    public void onClick(View v) {

        AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
        if (appCompatActivity instanceof NavigationActivity) {
            Intent intent = Product_Detail_Activity.NewIntent(appCompatActivity, mProduct.getProductUid(), false);
            appCompatActivity.startActivity(intent);
        }

    }

    public void bindView(Product product) {
        mProduct = product;
    }

}
