package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.activities.ProductDetailActivity;
import com.example.hamid_pc.biddingapp.activities.ProductEntryActivity;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class SellProductListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FloatingActionButton mFAB;



    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private String mSellerId;
    private Query mQuery;

    private FirebaseRecyclerAdapter<Product, ProductViewHolder> mAdapter;
    private DividerItemDecoration mDividerItemDecoration;



    public SellProductListFragment() {
        // Required empty public constructor
    }


    public static SellProductListFragment newInstance(String param1, String param2) {
        SellProductListFragment fragment = new SellProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("products");
        mSellerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selling_product_list, container, false);
        mFAB = (FloatingActionButton) view.findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQuery = mDatabaseReference.orderByChild("sold").equalTo(false);
        UpdateUI();


        mDividerItemDecoration = new DividerItemDecoration(getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mRecyclerView.setAdapter(mAdapter);



        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductEntryActivity.start(getActivity());
                startActivity(intent);

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void UpdateUI() {


        mAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(
                Product.class,
                R.layout.list_item_product,
                ProductViewHolder.class,
                mQuery

        ) {


            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

                final Product product = getItem(position);
                viewHolder.mProductTitle.setText(product.getProductTitle());
                viewHolder.mProductDescription.setText(product.getProductDescription());
                viewHolder.bindView(product);
                Picasso.with(getActivity())
                        .load(model.getPhotoUrl())
                        .into(viewHolder.mThumbnail);


                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = ProductDetailActivity.NewIntent(getActivity(), product.getProductUid(), false);
                        startActivity(intent);
                    }
                });


            }


        };


    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public Product mProduct;
        public TextView mProductTitle;
        public TextView mProductDescription;

        public ImageView mThumbnail;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mProductTitle = (TextView) itemView.findViewById(R.id.product_title);
            mProductDescription = (TextView) itemView.findViewById(R.id.product_description);
            mThumbnail = (ImageView) itemView.findViewById(R.id.list_image);

        }


        public void bindView(Product product) {
            mProduct = product;
        }
    }

}
