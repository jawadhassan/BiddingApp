package com.example.hamid_pc.biddingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Bid;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BidOperationFragment extends Fragment {


    private static final String ARG_PRODUCT_ID = "product_id";
    private static final String ARG_AUCTION_ID = "auction_id";
    private EditText mEditTextBid;
    private RecyclerView mRecyclerView;
    private Button mButtonBidSubmit;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String mAuctionId;
    private String mProductId;
    private Query mQuery;
    private FirebaseRecyclerAdapter<Bid, BidViewHolder> mAdapter;
    private int mBidAmount;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
    private String mUserEmail;
    private Long mEndTimeInMillis;

    private String TAG = "BidOperationActivity";

    public static BidOperationFragment NewInstance(String productId, String auctionId) {
        BidOperationFragment bidOperationFragment = new BidOperationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PRODUCT_ID, productId);
        bundle.putString(ARG_AUCTION_ID, auctionId);
        bidOperationFragment.setArguments(bundle);
        return bidOperationFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("bids");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        mUserEmail = mFirebaseUser.getEmail();
        mAuctionId = getArguments().getString(ARG_AUCTION_ID);
        mProductId = getArguments().getString(ARG_PRODUCT_ID);
        mQuery = mFirebaseDatabase.getReference("bids");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_operation, container, false);
        mEditTextBid = (EditText) view.findViewById(R.id.bidEditText);
        mButtonBidSubmit = (Button) view.findViewById(R.id.bidSubmitButton);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.bidRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.activity_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mEditTextBid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() > 0) {
                    mButtonBidSubmit.setEnabled(true);
                } else {
                    mButtonBidSubmit.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButtonBidSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBidAmount = Integer.parseInt(mEditTextBid.getText().toString());
                Bid bid = new Bid(mBidAmount, mAuctionId, mProductId, mAuctionId, mUserEmail);
                mDatabaseReference.push().setValue(bid);

            }
        });


        mAdapter = new FirebaseRecyclerAdapter<Bid, BidViewHolder>(
                Bid.class,
                R.layout.list_item_bid,
                BidViewHolder.class,
                mQuery
        ) {
            @Override
            protected void populateViewHolder(BidViewHolder viewHolder, Bid model, int position) {

                Bid bid = getItem(position);
                Log.d(TAG, "Check" + bid.getBidderId());
                viewHolder.mTextViewBid.setText(getResources().getString(R.string.product_amount, bid.getBidAmount()));
                viewHolder.mTextViewUserName.setText(bid.getBidderEmail());

            }


        };


        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    public static class BidViewHolder extends RecyclerView.ViewHolder {


        TextView mTextViewUserName;
        TextView mTextViewBid;

        public BidViewHolder(View itemView) {
            super(itemView);
            mTextViewUserName = (TextView) itemView.findViewById(R.id.UserNameTextView);
            mTextViewBid = (TextView) itemView.findViewById(R.id.BidTextView);
        }


    }
}
