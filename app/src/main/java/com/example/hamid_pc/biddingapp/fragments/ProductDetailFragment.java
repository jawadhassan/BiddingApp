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
import com.squareup.picasso.Picasso;


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
    private String mProductTitle;
    private String mProductDesc;
    private int mProductAmount;
    private String mProductType;
    private String mPhotoUrl;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    //TODO:Change the parameter named Buyer
    public static ProductDetailFragment newInstance(String productId, Boolean Buyer,
                                                    String productTitle,
                                                    String productDesc, int productAmount,
                                                    String productType, String photoUrl) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        args.putBoolean(ARG_BUYER, Buyer);
        args.putString(ARG_PRODUCT_TITLE, productTitle);
        args.putString(ARG_PRODUCT_DESC, productDesc);
        args.putInt(ARG_PRODUCT_AMOUNT, productAmount);
        args.putString(ARG_PHOTO_URL, photoUrl);
        args.putString(ARG_PRODUCT_TYPE, productType);
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
            mBuyer = getArguments().getBoolean(ARG_BUYER);
            mProductTitle = getArguments().getString(ARG_PRODUCT_TITLE);
            mProductDesc = getArguments().getString(ARG_PRODUCT_DESC);
            mProductAmount = getArguments().getInt(ARG_PRODUCT_AMOUNT, 0);
            mProductType = getArguments().getString(ARG_PRODUCT_TYPE);
            mPhotoUrl = getArguments().getString(ARG_PHOTO_URL);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        mProductTitleView = (TextView) view.findViewById(R.id.text_view_product_title);
        mProductDescView = (TextView) view.findViewById(R.id.text_view_product_description);
        mProductPriceView = (TextView) view.findViewById(R.id.text_view_product_price);
        mProductTypeView = (TextView) view.findViewById(R.id.text_view_product_type);
        mThumbnail = (ImageView) view.findViewById(R.id.image_view_product);

        mProductTitleView.setText(mProductTitle);
        mProductDescView.setText(mProductDesc);
        mProductPriceView.setText(getResources().getString(R.string.product_amount, mProductAmount));

        Picasso.with(getActivity())
                .load(mPhotoUrl)
                .into(mThumbnail);

        mSubmitButton = (Button) view.findViewById(R.id.button_submit_bid);
        if (mBuyer) {
            mSubmitButton.setEnabled(false);
        }
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
