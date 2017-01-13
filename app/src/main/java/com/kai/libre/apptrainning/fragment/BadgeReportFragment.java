package com.kai.libre.apptrainning.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;

import com.kai.libre.apptrainning.AppConstants;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnReportBadge;

import static com.kai.libre.apptrainning.R.id.nameEmployeeTab;


/**
 * Created by Kai on 1/12/2017.
 */

public class BadgeReportFragment extends DialogFragment {



    private AlertDialog dialog;

    private FragmentTabHost mTabHost;

    private ViewPager viewPager;

    private String token;

    private int userId;

    private int creatorId;

    private TextView tvName;

    private EnReportBadge enReportBadge;

    private Context mContext;

    private GridView gridView;

    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bagde, container);
        mContext = getActivity();
        bundle = getArguments();
        mTabHost = (FragmentTabHost) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tvName = (TextView) view.findViewById(nameEmployeeTab);
        gridView = (GridView) view.findViewById(R.id.gridviewDanger);
        getValuesFromBundle();

        mTabHost.setup(getActivity(), getChildFragmentManager());
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(AppConstants.BADGE_DANGER), DangerFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(AppConstants.BADGE_SUCCESS), SuccessFragment.class, bundle);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int i = mTabHost.getCurrentTab();
                viewPager.setCurrentItem(i);
            }
        });

        return view;
    }



    public void getValuesFromBundle() {
        bundle = getArguments();
        getDialog().setTitle(bundle.getString(AppConstants.NAME_EMPLOYEE));
        token = bundle.getString(AppConstants.TOKEN);
        userId = bundle.getInt(AppConstants.USER_ID);
        creatorId = bundle.getInt(AppConstants.CREATOR_ID);
        enReportBadge = new EnReportBadge(token, userId, creatorId);
    }

    public void getBadge() {


    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 800;
        params.height = 1000;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

}
