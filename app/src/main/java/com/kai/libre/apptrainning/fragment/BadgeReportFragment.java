package com.kai.libre.apptrainning.fragment;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/12/2017.
 */

public class BadgeReportFragment extends FragmentActivity {
    private List<EnBadgeResponse> listBadge;
    private List<String> listNameBadge;
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.dialog_bagde);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
       /* mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
                FragmentStackSupport.CountingFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
                LoaderCursorSupport.CursorLoaderListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"),
                LoaderCustomSupport.AppListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
                LoaderThrottleSupport.ThrottledLoaderListFragment.class, null);*/
    }

   /* @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bagde, null);
        Bundle bundle = getArguments();
        getBadge();
        listBadge = new ArrayList<EnBadgeResponse>();
        listNameBadge = new ArrayList<String>();
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("Frag Tab1"),
                MyNestedFragment1.class, arg1)
        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }*/

    public void getBadge()
    {
        ApiClient.getClient().getListBadge().enqueue(new Callback<EnBadgeResponse>() {
            @Override
            public void onResponse(Call<EnBadgeResponse> call, Response<EnBadgeResponse> response) {
                listBadge = response.body().getData();
                for(int i = 0; i < listBadge.size(); i ++) {
                    listNameBadge.add(listBadge.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<EnBadgeResponse> call, Throwable t) {

            }
        });
    }

}
