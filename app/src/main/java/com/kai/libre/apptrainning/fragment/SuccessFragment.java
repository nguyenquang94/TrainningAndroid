package com.kai.libre.apptrainning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kai.libre.apptrainning.R;

/**
 * Created by Kai on 1/13/2017.
 */

public class SuccessFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.tab_success, container, false);

        return V;
    }
}
