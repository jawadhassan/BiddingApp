package com.example.hamid_pc.biddingapp.FirebaseRecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;




public class ProductListAdapter  {

    private Query mQuery;
    private Context mContext;
    private FirebaseRecyclerAdapter<Product,ProductViewHolder> mAdapter;
    public ProductListAdapter(Query query,Context context) {

        mQuery = query;
        mContext = context;

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
                viewHolder.mProductTitle.setText(model.getProductTitle());
                viewHolder.mProductDescription.setText(model.getProductDescription());
                Product product = getItem(position);
                viewHolder.bindView(product);


                Picasso.with(mContext)
                        .load(model.getPhotoUrl())
                        .into(viewHolder.mThumbnail);


            }
        };


    }

    public RecyclerView.Adapter getAdapter(){
        return  mAdapter;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        Product mProduct;
        TextView mProductTitle;
        TextView mProductDescription;

        ImageView mThumbnail;

        public ProductViewHolder(View itemView) {
            super(itemView);

            mProductTitle = (TextView) itemView.findViewById(R.id.product_title);
            mProductDescription = (TextView) itemView.findViewById(R.id.product_description);
            mThumbnail = (ImageView) itemView.findViewById(R.id.list_image);


        }


        public void bindView(Product product){
            mProduct = product;
        }
    }


}
