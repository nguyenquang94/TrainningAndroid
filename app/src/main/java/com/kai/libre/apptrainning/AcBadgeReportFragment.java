package com.kai.libre.apptrainning;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.entity.EnReportBadge;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kai on 1/12/2017.
 */

public class AcBadgeReportFragment extends TabActivity {

    private TextView tvName;

    private FragmentTabHost mTabHost;

    private ViewPager viewPager;

    private String token;

    private int userId;

    private int creatorId;

    private EnReportBadge enReportBadge;

    private Bundle bundle;

    private ArrayList<EnBadgeResponse> listDanger;

    private List<EnBadgeResponse> listBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_bagde);
        tvName = (TextView) findViewById(R.id.nameEmployee);
        listDanger = new ArrayList<EnBadgeResponse>();
        listBadge = new ArrayList<EnBadgeResponse>();
        bundle = getBundle();
        getValuesFromBundle();
        TabHost tabHost = getTabHost();

        TabHost.TabSpec u = tabHost.newTabSpec(AppConstants.TITLE_BADGE_DANGER);
        u.setIndicator(AppConstants.TITLE_BADGE_DANGER);
        Intent updatesIntent = new Intent(this, AcDangerBadge.class);
        updatesIntent.putExtras(bundle);
        u.setContent(updatesIntent);

        TabHost.TabSpec u1 = tabHost.newTabSpec(AppConstants.TITLE_BADGE_SUCCESS);
        u1.setIndicator(AppConstants.TITLE_BADGE_SUCCESS);
        Intent updatesIntent1 = new Intent(this, AcSuccessBadge.class);
        updatesIntent1.putExtras(bundle);
        u1.setContent(updatesIntent1);


        // add all tabs
        tabHost.addTab(u);
        tabHost.addTab(u1);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(2);

    }

    public void getValuesFromBundle() {
        bundle = getBundle();
        tvName.setText(bundle.getString(AppConstants.NAME_EMPLOYEE).toUpperCase());
        token = bundle.getString(AppConstants.TOKEN);
        userId = bundle.getInt(AppConstants.USER_ID);
        creatorId = bundle.getInt(AppConstants.CREATOR_ID);
        enReportBadge = new EnReportBadge(token, userId, creatorId);
    }


    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }
}

