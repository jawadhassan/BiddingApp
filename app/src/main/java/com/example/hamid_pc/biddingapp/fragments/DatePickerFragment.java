package com.example.hamid_pc.biddingapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.hamid_pc.biddingapp.R;

import org.joda.time.DateTime;


public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE =
            "com.example.hamid_pc.biddingapp.fragments";


    private DatePicker mDatePicker;
    private DateTime mDateTime;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        mDateTime = DateTime.now();
        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
        mDatePicker.setMinDate(mDateTime.getMillis());

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.label_date_picker)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        DateTime date = new DateTime(year, month + 1, day, 0, 0, 0);
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();


    }

    public void sendResult(int resultCode, DateTime date) {
        if (getTargetFragment() == null)
            return;

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
