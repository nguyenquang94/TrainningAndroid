package com.kai.libre.apptrainning.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnEmployee;

import java.util.List;

/**
 * Created by Kai on 1/11/2017.
 */

public class BadgeAdapter extends BaseAdapter {

    private Context mContext;
    private List<EnEmployee> employeeList;

    public BadgeAdapter(Context mContext, List<EnEmployee> employeeList) {
        this.mContext = mContext;
        this.employeeList = employeeList;
    }


    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView noti1 = (TextView) view.findViewById(R.id.noti1);
        TextView noti2 = (TextView) view.findViewById(R.id.noti2);
        TextView nameEmployee = (TextView) view.findViewById(R.id.tvEmployeeName);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgEmployee);

        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }
       noti1.setText(employeeList.get(i).getNoti1());
        noti2.setText(employeeList.get(i).getNoti2());
        nameEmployee.setText(employeeList.get(i).getNameEmployee());
        Glide.with(mContext).load(employeeList.get(i).getImageAva()).into(imageView);
        return null;
    }
}
