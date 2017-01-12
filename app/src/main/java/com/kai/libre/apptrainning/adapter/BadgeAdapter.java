package com.kai.libre.apptrainning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.entity.EnUserData;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/11/2017.
 */

public class BadgeAdapter extends BaseAdapter {

    private Context mContext;
    private List<EnUserData> listEnUserDatas;

    public BadgeAdapter(Context mContext, List<EnUserData> listEnUserDatas) {
        this.mContext = mContext;
        this.listEnUserDatas = listEnUserDatas;
    }


    @Override
    public int getCount() {
        return listEnUserDatas.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = inflater.inflate(R.layout.custom_ac_badge, null);

        TextView noti1 = (TextView) gridView.findViewById(R.id.noti1);
        TextView noti2 = (TextView) gridView.findViewById(R.id.noti2);
        TextView nameEmployee = (TextView) gridView.findViewById(R.id.tvEmployeeName);
        final ImageView imageView = (ImageView) gridView.findViewById(R.id.imgEmployee);
        String countDanger = listEnUserDatas.get(position).getCountDanger()+"";
        noti1.setText(listEnUserDatas.get(position).getCountDanger() +"");
        noti2.setText(listEnUserDatas.get(position).getOcuntSuccess() +"");
        nameEmployee.setText(listEnUserDatas.get(position).getName());
        int avatarId = listEnUserDatas.get(position).getAvatarId();

        ApiClient.getClient().getAvatarImage(avatarId).enqueue(new Callback<EnAvatar>() {
            @Override
            public void onResponse(Call<EnAvatar> call, Response<EnAvatar> response) {
                EnAvatar enAvatar = response.body();
                Glide.with(mContext).load(enAvatar.getDescription()).into(imageView);
            }

            @Override
            public void onFailure(Call<EnAvatar> call, Throwable t) {

            }
        });
        return gridView;
    }
}
