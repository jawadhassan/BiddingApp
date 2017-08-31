package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamid_pc.biddingapp.FirebaseRecyclerViewAdapters.ProductListAdapter;
import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.ViewHolders.BuyProductViewHolder;
import com.example.hamid_pc.biddingapp.activities.ProductEntryActivity;
import com.example.hamid_pc.biddingapp.models.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


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
        Query query = mDatabaseReference.orderByChild("sellerId").equalTo(mSellerId);
        ProductListAdapter productListAdapter = new ProductListAdapter(
                Product.class,
                R.layout.list_item_product,
                BuyProductViewHolder.class,
                query,
                getActivity()
        );

        mRecyclerView.setAdapter(productListAdapter);


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

}
