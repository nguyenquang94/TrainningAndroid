package com.kai.libre.apptrainning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kai.libre.apptrainning.fragment.FragmentAcClock;

public class AcClock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_clock);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentAcClock())
                    .commit();
        }
    }
}
