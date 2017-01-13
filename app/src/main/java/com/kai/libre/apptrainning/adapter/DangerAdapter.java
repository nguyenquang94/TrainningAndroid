package com.kai.libre.apptrainning.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kai.libre.apptrainning.AcBadge;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.entity.EnReportBadge;

import java.util.ArrayList;

/**
 * Created by Kai on 1/13/2017.
 */

public class DangerAdapter extends BaseAdapter {

    private AcBadge mContext;

    private EnReportBadge enReportBadge;

    private ArrayList<EnBadgeResponse> listEnBadgeResponses;

    public DangerAdapter(Context context, EnReportBadge enReportBadge, ArrayList<EnBadgeResponse> listEnBadgeResponses) {
        this.mContext =(AcBadge) context;
        this.enReportBadge = enReportBadge;
        this.listEnBadgeResponses = listEnBadgeResponses;
    }

    @Override
    public int getCount() {
        return listEnBadgeResponses.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Log.d("nguyennguyenquang","davaoday");
        view = LayoutInflater.from(mContext).inflate(R.layout.custom_danger, viewGroup, false);
        TextView tvDanger = (TextView) view.findViewById(R.id.tvDanger);
        ImageView imgDanger = (ImageView) view.findViewById(R.id.imageView);
        imgDanger.setImageResource(R.drawable.ic_danger);
        tvDanger.setText(listEnBadgeResponses.get(position).getName());
        Log.d("nguyenquang", listEnBadgeResponses.get(position).getName());
        return view;
    }
}
