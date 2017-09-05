package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.activities.ProductDetailActivity;
import com.example.hamid_pc.biddingapp.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class BuyProductListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Query mQuery;

    private DividerItemDecoration mDividerItemDecoration;


    private String mParam1;
    private String mParam2;

    private String TAG = "BuyProductListActivity";


    private Spinner mSpinner;

    private FirebaseRecyclerAdapter<Product, ProductViewHolder> mAdapter;


    public BuyProductListFragment() {
        // Required empty public constructor
    }


    public static BuyProductListFragment newInstance(String param1, String param2) {
        BuyProductListFragment fragment = new BuyProductListFragment();
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


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Type = parent.getItemAtPosition(position).toString();
        if (Type.equalsIgnoreCase("Android")) {
            mQuery = mDatabaseReference.orderByChild("productType").equalTo("Android");
            mAdapter.cleanup();
            UpdateUI();

        } else {
            mQuery = mDatabaseReference.orderByChild("productType").equalTo("iPhone");
            mAdapter.cleanup();
            UpdateUI();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_product_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQuery = mDatabaseReference.orderByChild("productType").equalTo("iPhone");
        mSpinner = (Spinner) view.findViewById(R.id.products_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.product_titles, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        UpdateUI();


        mDividerItemDecoration = new DividerItemDecoration(getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);


        return view;
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
                        Intent intent = ProductDetailActivity.NewIntent(getActivity(), product.getProductUid(), true);
                        startActivity(intent);
                    }
                });


            }


        };


        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
