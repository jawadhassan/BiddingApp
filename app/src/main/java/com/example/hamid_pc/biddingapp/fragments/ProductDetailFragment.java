package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Auction;
import com.example.hamid_pc.biddingapp.models.Auctioneer;
import com.example.hamid_pc.biddingapp.models.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "product_id";
    private static final String ARG_BUYER = "buyer";
    private final String TAG = "ProductDetailFragment";
    Product mProduct;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mProductReference;
    private DatabaseReference mAuctionReference;
    private DatabaseReference mAuctioneerReference;
    private DatabaseReference mBookingReference;
    private FirebaseUser mFirebaseUser;
    private TextView mProductTitleView;
    private TextView mProductDescView;
    private TextView mProductPriceView;
    private TextView mProductTypeView;
    private TextView mAuctionStartDateView;
    private TextView mAuctionEndDateView;
    private ImageView mThumbnail;
    private Button mSubmitButton;
    private String mUserId;
    private String mUserName;
    private String mUserEmail;
    private String mProductId;
    private String mAuctionId;
    private Boolean mBuyer;
    private Boolean mBidEarlier = false;
    private Long mStartDateInMillis;
    private Long mEndDateInMillis;
    private DateTime mStartDate;
    private DateTime mEndDate;

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
            mProductReference = mFirebaseDatabase.getReference("products");
            mAuctionReference = mFirebaseDatabase.getReference("auctions");
            mBookingReference = mFirebaseDatabase.getReference("booking").child(mUserId);



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
        mAuctionStartDateView = (TextView) view.findViewById(R.id.text_view_auction_start_date);
        mAuctionEndDateView = (TextView) view.findViewById(R.id.text_view_auction_end_date);
        mSubmitButton = (Button) view.findViewById(R.id.button_submit_bid);

        mThumbnail = (ImageView) view.findViewById(R.id.image_view_product);


        if (!mBuyer || !mBidEarlier) {
            mSubmitButton.setVisibility(View.GONE);
        } else {
            mSubmitButton.setVisibility(View.VISIBLE);
        }

        mProductReference.orderByChild("productUid").equalTo(mProductId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mProduct = dataSnapshot.getValue(Product.class);
                mProductTitleView.setText(mProduct.getProductTitle());
                mProductDescView.setText(mProduct.getProductDescription());
                mProductTypeView.setText(mProduct.getProductType());
                mProductPriceView.setText(getResources().getString(R.string.product_amount, mProduct.getMinBidAmount()));

                Picasso.with(getActivity())
                        .load(mProduct.getPhotoUrl())
                        .into(mThumbnail);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mAuctionReference.orderByChild("productId").equalTo(mProductId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Auction auction = dataSnapshot.getValue(Auction.class);
                mStartDateInMillis = auction.getBidStartDate();
                mEndDateInMillis = auction.getBidEndDate();
                mAuctionId = auction.getAuctionId();

                DateTime startDateTime = new DateTime(Long.valueOf(mStartDateInMillis), DateTimeZone.UTC);
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMMM, yyyy");
                String strDate = startDateTime.toString(dateTimeFormatter);

                mAuctionStartDateView.setText(strDate);

                DateTime endDateTime = new DateTime(Long.valueOf(mEndDateInMillis), DateTimeZone.UTC);
                dateTimeFormatter = DateTimeFormat.forPattern("d MMMM, yyyy");
                String endDate = endDateTime.toString(dateTimeFormatter);

                mAuctionEndDateView.setText(endDate);

                mAuctioneerReference = mFirebaseDatabase.getReference("auctioneer").child(auction.getAuctionId());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mBookingReference.orderByChild("productUid").equalTo(mProductId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBidEarlier = true;

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAuctioneerReference != null && mProduct != null) {
                    Auctioneer auctioneer = new Auctioneer(mUserId, mUserEmail, mUserName);
                    mAuctioneerReference.push().setValue(auctioneer);
                    mBookingReference.push().setValue(mProduct);
                } else {
                    Log.d(TAG, "Firebase Error found");
                }


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
