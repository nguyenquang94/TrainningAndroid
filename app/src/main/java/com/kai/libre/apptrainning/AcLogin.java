package com.kai.libre.apptrainning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kai.libre.apptrainning.fragment.FragmentAcLogin;

public class AcLogin extends AppCompatActivity {

    private static final String TAG = AcLogin.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentAcLogin())
                    .commit();
        }

    }



}
