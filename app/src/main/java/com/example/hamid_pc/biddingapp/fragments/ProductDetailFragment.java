package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Auctioneer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "product_id";
    private static final String ARG_BUYER = "buyer";
    private static final String ARG_PRODUCT_TITLE = "product_title";
    private static final String ARG_PRODUCT_DESC = "product_desc";
    private static final String ARG_PRODUCT_AMOUNT = "product_amount";
    private static final String ARG_PRODUCT_TYPE = "product_type";
    private static final String ARG_PHOTO_URL = "photo_url";


    private Button mSubmitButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mBookingReference;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
    private String mUserName;
    private String mUserEmail;
    private Boolean mBuyer;
    private TextView mProductTitleView;
    private TextView mProductDescView;
    private TextView mProductPriceView;
    private TextView mProductTypeView;
    private ImageView mThumbnail;



    private String mProductId;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    //TODO:Change the parameter named Buyer and Fix Submit Button
    public static ProductDetailFragment newInstance(String productId, Boolean Buyer) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        args.putBoolean(ARG_BUYER, Buyer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getString(ARG_PRODUCT_ID);
            mBuyer = getArguments().getBoolean(ARG_BUYER);

            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            mUserId = mFirebaseUser.getUid();
            mUserName = mFirebaseUser.getDisplayName();
            mUserEmail = mFirebaseUser.getEmail();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference("auctions");
            mBookingReference = mFirebaseDatabase.getReference("bookings").child(mUserId);


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        mProductTitleView = (TextView) view.findViewById(R.id.text_view_product_title);
        mProductDescView = (TextView) view.findViewById(R.id.text_view_product_description);
        mProductPriceView = (TextView) view.findViewById(R.id.text_view_min_bid);
        mProductTypeView = (TextView) view.findViewById(R.id.text_view_product_type);
        mThumbnail = (ImageView) view.findViewById(R.id.image_view_product);

        /*mProductTitleView.setText(mProductTitle);
        mProductDescView.setText(mProductDesc);
        mProductTypeView.setText(mProductType);
        mProductPriceView.setText(getResources().getString(R.string.product_amount, mProductAmount));

        Picasso.with(getActivity())
                .load(mPhotoUrl)
                .into(mThumbnail);*/

        mSubmitButton = (Button) view.findViewById(R.id.button_submit_bid);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Auctioneer auctioneer = new Auctioneer(mUserId, mUserEmail, mUserName);
                mDatabaseReference.push().setValue(auctioneer);


            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBuyer) {
            mSubmitButton.setEnabled(true);
        } else {
            mSubmitButton.setEnabled(false);
        }
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
