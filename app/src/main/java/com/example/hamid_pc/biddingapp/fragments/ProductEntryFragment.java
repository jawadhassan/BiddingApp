package com.example.hamid_pc.biddingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hamid_pc.biddingapp.R;
import com.example.hamid_pc.biddingapp.models.Auction;
import com.example.hamid_pc.biddingapp.models.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static org.joda.time.DateTimeZone.UTC;

public class ProductEntryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mProductTitleField;
    private EditText mProductDescriptionField;
    private EditText mProductBidAmountField;
    private EditText mHourPickerText;
    private Button mTimePickerButton;
    private Button mDatePickerButton;
    private Button mPhotoPickerButton;
    private Spinner mProductTypeSpinner;
    private Button mSubmitButton;


    private String mProductTitle;
    private String mProductDesc;
    private String mSellerId;
    private String mProductType;
    private String mOwnerId_ProductType_Sold;
    private int mBidAmount;
    private int mHour;
    private Long mDateInMilli;
    private Long mEndDateInMilli;


    private DateTime mDate;
    private DateTime mDateTime;

    private String TAG = "ProductEntryActivity";

    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final int RC_PHOTO_PICKER = 2;


    private Uri mSelectedImageUri;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAuctionReference;
    private DatabaseReference mAuctionRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseUser mFirebaseUser;
    private String mProductId;
    private String mAuctionId;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mProductPhotosStorageReference;



    private String mParam1;
    private String mParam2;

    public ProductEntryFragment() {
        // Required empty public constructor
    }


    public static ProductEntryFragment newInstance(String param1, String param2) {
        ProductEntryFragment fragment = new ProductEntryFragment();
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
        mAuctionReference = mFirebaseDatabase.getReference("auctions");
        mFirebaseStorage = FirebaseStorage.getInstance();
        mProductPhotosStorageReference = mFirebaseStorage.getReference().child("product_photos");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSellerId = mFirebaseUser.getUid();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_entry, container, false);
        mProductTitleField = (EditText) view.findViewById(R.id.product_title);
        mProductDescriptionField = (EditText) view.findViewById(R.id.product_description);
        mProductBidAmountField = (EditText) view.findViewById(R.id.product_min_bid_amount);
        mHourPickerText = (EditText) view.findViewById(R.id.num_of_hour);
        mDatePickerButton = (Button) view.findViewById(R.id.button_date_picker);
        mTimePickerButton = (Button) view.findViewById(R.id.button_time_picker);
        mPhotoPickerButton = (Button) view.findViewById(R.id.button_image_picker);
        mProductTypeSpinner = (Spinner) view.findViewById(R.id.product_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.product_titles,android.R.layout.simple_spinner_item);

        mProductTypeSpinner.setAdapter(adapter);
        mProductTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mProductType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSubmitButton = (Button) view.findViewById(R.id.button_submit);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.activity_toolbar);
         AppCompatActivity appCompatActivity =  (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(ProductEntryFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(ProductEntryFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);

            }
        });


        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Complete Action Using"),RC_PHOTO_PICKER);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductTitle = mProductTitleField.getText().toString();
                mProductDesc = mProductDescriptionField.getText().toString();
                mBidAmount = Integer.valueOf(mProductBidAmountField.getText().toString());


                StorageReference photoRef =
                        mProductPhotosStorageReference.child(mSelectedImageUri.getLastPathSegment());


                photoRef.putFile(mSelectedImageUri)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG,"Unsuccessful"+e.toString());
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        mDatabaseRef = mDatabaseReference.push().getRef();
                        mProductId = mDatabaseRef.getKey();
                        mOwnerId_ProductType_Sold = mSellerId+mProductType+false;
                        Product product = new
                                Product(mProductId,mProductTitle,mProductDesc,
                                downloadUrl.toString(),mSellerId,"",mBidAmount,
                                mProductType,mOwnerId_ProductType_Sold,false);
                        mDatabaseRef.setValue(product);

                        mAuctionRef = mAuctionReference.push().getRef();
                        mAuctionId = mAuctionRef.getKey();



                        //Taking Date Input from User,

                        mHour = Integer.parseInt(mHourPickerText.getText().toString());
                        mDate = mDate.hourOfDay().setCopy(mDateTime.getHourOfDay());
                        mDate = mDate.minuteOfHour().setCopy(mDateTime.getMinuteOfHour());
                        mDate = mDate.secondOfMinute().setCopy(mDateTime.getSecondOfMinute());
                        mDateInMilli = mDate.toDateTime(UTC).getMillis();
                        mEndDateInMilli = mDate.plusHours(mHour).toDateTime(UTC).getMillis();

                        Auction auction = new Auction(mAuctionId,mProductId,mSellerId,"",
                                mBidAmount,0,mDateInMilli,mEndDateInMilli);


                        mAuctionRef.setValue(auction);

                    }


                });


            }
        });




        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_DATE){
            mDate = (DateTime) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d,MMMM,yyyy");
            String str = mDate.toString(dateTimeFormatter);
            mDatePickerButton.setText(str);
        }else if (requestCode == REQUEST_TIME) {
            mDateTime = (DateTime) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm a");
            String str = mDateTime.toString(fmt);
            mTimePickerButton.setText(str);
        }else if (requestCode == RC_PHOTO_PICKER ) {
             mSelectedImageUri = data.getData();
            Log.d(TAG, "" + mSelectedImageUri.toString());
            mPhotoPickerButton.setText(mSelectedImageUri.getLastPathSegment());

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
