package com.example.hamid_pc.biddingapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.hamid_pc.biddingapp.R;


public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment activityFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (activityFragment == null) {
            activityFragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, activityFragment)
                    .commit();
        }
    }


}
