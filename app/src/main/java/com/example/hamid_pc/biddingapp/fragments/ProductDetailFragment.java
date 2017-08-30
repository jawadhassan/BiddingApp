package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Auctioneer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "product_id";


    private Button mSubmitButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
    private String mUserName;
    private String mUserEmail;


    private String mProductId;


    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(String productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getString(ARG_PRODUCT_ID);
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            mDatabaseReference = mFirebaseDatabase.getReference(mProductId);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        mSubmitButton = (Button) view.findViewById(R.id.button_submit_bid);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserId = mFirebaseUser.getUid();
                mUserName = mFirebaseUser.getDisplayName();
                mUserEmail = mFirebaseUser.getEmail();

                Auctioneer auctioneer = new Auctioneer(mUserId, mUserEmail, mUserName);
                mDatabaseReference.push().setValue(auctioneer);


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
