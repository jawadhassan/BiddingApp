package com.example.hamid_pc.biddingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.activities.ProductEntryActivity;


public class SellProductListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FloatingActionButton mFAB;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SellProductListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selling_product_list, container, false);
        mFAB = (FloatingActionButton) view.findViewById(R.id.fab);

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
