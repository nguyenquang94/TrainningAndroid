package com.kai.libre.apptrainning.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kai.libre.apptrainning.AcBadge;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.entity.EnReportBadge;

import java.util.List;

/**
 * Created by Kai on 1/11/2017.
 */

public class DangerAdapter extends ArrayAdapter<EnBadgeResponse> {

    private AcBadge mContext;
    private EnReportBadge enReportBadge;
    private List<EnBadgeResponse> listEnBadgeResponses;

    public DangerAdapter(Context context, List<EnBadgeResponse> listEnBadgeResponses) {
        super(context, 0, listEnBadgeResponses);
        this.listEnBadgeResponses = listEnBadgeResponses;
        this.mContext = (AcBadge) context;
    }

    /* public DangerAdapter(Context mContext, EnReportBadge enReportBadge, List<EnBadgeResponse> listEnBadgeResponses) {
         this.mContext = (AcBadge) mContext;
         this.enReportBadge = enReportBadge;
         this.listEnBadgeResponses = listEnBadgeResponses;
     }
 */
    public int getCount() {
        Log.d("ngngngngn", listEnBadgeResponses.size() + "");
        return this.listEnBadgeResponses == null ? 0 : listEnBadgeResponses.size();

    }

    @Override
    public EnBadgeResponse getItem(int i) {
        return listEnBadgeResponses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Log.d("nguyenquang","davaoday");
        EnBadgeResponse  enBadgeResponse = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_danger, viewGroup, false);
        }
        return convertView;
    }
}
