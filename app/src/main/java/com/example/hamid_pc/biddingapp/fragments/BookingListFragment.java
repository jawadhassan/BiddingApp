package com.example.hamid_pc.biddingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.activities.BidOperationActivity;
import com.example.hamid_pc.biddingapp.models.Auction;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.joda.time.Interval;


public class BookingListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAuctionReference;
    private FirebaseUser mFirebaseUser;
    private Long mStartDateInMillis;
    private Long mEndDateInMillis;

    //  private Long mDateNowInMillis;
    private Boolean mBiddingTimeStarted = false;
    private String mBookerId;

    private FirebaseRecyclerAdapter<Product, ProductViewHolder> mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mBookerId = mFirebaseUser.getUid();
        mDatabaseReference = mFirebaseDatabase.getReference("booking").child(mBookerId);
        mAuctionReference = mFirebaseDatabase.getReference("auctions");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    public void UpdateUI() {
        mAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(
                Product.class,
                R.layout.list_item_product,
                ProductViewHolder.class,
                mDatabaseReference
        ) {


            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, final Product model, int position) {

                final Product product = getItem(position);

                viewHolder.mProductTitle.setText(product.getProductTitle());
                viewHolder.mProductDescription.setText(product.getProductDescription());
                Picasso.with(getActivity())
                        .load(model.getPhotoUrl())
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_error)
                        .into(viewHolder.mThumbnail);


                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mAuctionReference.orderByChild("auctionId").equalTo(product.getAuctionId()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Auction auction = dataSnapshot.getValue(Auction.class);
                                mStartDateInMillis = auction.getBidStartDate();
                                mEndDateInMillis = auction.getBidEndDate();
                                Interval interval = new Interval(mStartDateInMillis, mEndDateInMillis);
                                mBiddingTimeStarted = interval.containsNow();


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

                        if (mBiddingTimeStarted) {


                            Intent intent = BidOperationActivity.NewIntent(getActivity(), model.getProductUid(), model.getAuctionId());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), R.string.bidding_time_warning, Toast.LENGTH_LONG).show();
                        }


                    }
                });


            }
        };


    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {


        public TextView mProductTitle;
        public TextView mProductDescription;
        public ImageView mThumbnail;


        public ProductViewHolder(View itemView) {
            super(itemView);
            mProductTitle = (TextView) itemView.findViewById(R.id.product_title);
            mProductDescription = (TextView) itemView.findViewById(R.id.product_description);
            mThumbnail = (ImageView) itemView.findViewById(R.id.list_image);
        }
    }
}
