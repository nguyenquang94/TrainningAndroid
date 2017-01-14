package com.kai.libre.apptrainning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.kai.libre.apptrainning.AppConstants;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.adapter.DangerAdapter;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.entity.EnReportBadge;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/13/2017.
 */

public class DangerFragment extends android.support.v4.app.Fragment {

    private GridView gridView;

    private List<EnBadgeResponse> listBadge;

    private String token;

    private int userId;

    private int creatorId;

    private ArrayList<EnBadgeResponse> listEnBadgeResponses = new ArrayList<>();

    private EnReportBadge enReportBadge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_danger, container, false);
        gridView = (GridView) v.findViewById(R.id.gridviewDanger);
        listBadge = new ArrayList<EnBadgeResponse>();
        getValuesFromBundle();
        getBadge();

        return v;
    }

    public void getValuesFromBundle() {
        Bundle bundle = getArguments();
        token = bundle.getString(AppConstants.TOKEN);
        userId = bundle.getInt(AppConstants.USER_ID);
        creatorId = bundle.getInt(AppConstants.CREATOR_ID);
        enReportBadge = new EnReportBadge(token, userId, creatorId);
    }

    public void getBadge() {
        ApiClient.getClient().getListBadge().enqueue(new Callback<EnBadgeResponse>() {
            @Override
            public void onResponse(Call<EnBadgeResponse> call, Response<EnBadgeResponse> response) {
                listBadge = response.body().getData();
                for (int i = 0; i < listBadge.size(); i++) {
                    String colorClass = listBadge.get(i).getColorClass();
                    switch (colorClass) {
                        case AppConstants.BADGE_DANGER:
                            EnBadgeResponse enBadgeResponse = new EnBadgeResponse(listBadge.get(i).getId(), listBadge.get(i).getName());
                            listEnBadgeResponses.add(enBadgeResponse);


                            break;

                        case AppConstants.BADGE_SUCCESS:
                            break;
                    }
                }
                final DangerAdapter dangerAdapter = new DangerAdapter(getActivity(), listEnBadgeResponses);
                gridView.post(new Runnable() {
                    public void run() {
                        gridView.setAdapter(dangerAdapter);
                        dangerAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(Call<EnBadgeResponse> call, Throwable t) {

            }
        });
    }
}
