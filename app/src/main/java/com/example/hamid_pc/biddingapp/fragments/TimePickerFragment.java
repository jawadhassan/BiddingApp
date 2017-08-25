package com.example.hamid_pc.biddingapp.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.example.hamid_pc.biddingapp.R;

import org.joda.time.DateTime;


public class TimePickerFragment extends DialogFragment {


    public static final String EXTRA_TIME = "com.example.hamid_pc.biddingapp.fragments.time";


    private TimePicker mTimePicker;
    private DateTime mDateTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.label_time_picker)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        DateTime dateTime = new DateTime();

                        int year = dateTime.getYear();
                        int month = dateTime.getMonthOfYear();
                        int day = dateTime.getDayOfMonth();
                        int hour;
                        int minute;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hour = mTimePicker.getHour();
                            minute = mTimePicker.getMinute();
                        } else {

                            hour = mTimePicker.getCurrentHour();
                            minute = mTimePicker.getCurrentMinute();

                        }



                        DateTime localDateTime = new DateTime(year, month, day, hour, minute);
                        sendResult(Activity.RESULT_OK, localDateTime);

                    }
                })
                .create();
    }


    private void sendResult(int resultCode, DateTime date) {
        if (getTargetFragment() == null) {
            return;
        } else {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TIME, date);

            getTargetFragment()
                    .onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }
}
