package com.kai.libre.apptrainning;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kai.libre.apptrainning.common.AppConstants;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.fragment.DialogFragmentConfirm;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/17/2017.
 */

public class AcSuccessBadge extends AppCompatActivity {

    private GridView gridView;

    private List<EnBadgeResponse> listBadge;

    private ArrayList<EnBadgeResponse> listDanger;

    private SuccessAdapter successAdapter;

    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_danger);
        bundle = getBundle();
        listBadge = new ArrayList<EnBadgeResponse>();
        listDanger = new ArrayList<EnBadgeResponse>();
        createListBadge();
        gridView = (GridView) findViewById(R.id.gridviewDanger);

    }


    public void createListBadge() {
        ApiClient.getClient().getListBadge().enqueue(new Callback<EnBadgeResponse>() {
            @Override
            public void onResponse(Call<EnBadgeResponse> call, Response<EnBadgeResponse> response) {
                listBadge = response.body().getData();
                for (int i = 0; i < listBadge.size(); i++) {
                    String colorClass = listBadge.get(i).getColorClass();
                    switch (colorClass) {
                        case AppConstants.BADGE_SUCCESS:
                            EnBadgeResponse enBadgeResponse = new EnBadgeResponse(listBadge.get(i).getId(), listBadge.get(i).getName());
                            listDanger.add(enBadgeResponse);

                            break;

                        case AppConstants.BADGE_DANGER:
                            break;
                    }
                }
                successAdapter = new SuccessAdapter(AcSuccessBadge.this, bundle, listDanger);
                gridView.setAdapter(successAdapter);
            }

            @Override
            public void onFailure(Call<EnBadgeResponse> call, Throwable t) {

            }
        });
    }

    public class SuccessAdapter extends BaseAdapter {

        private Context mContext;

        private List<EnBadgeResponse> list;

        private Bundle bundle;

        public SuccessAdapter(Context context, Bundle bundle, ArrayList<EnBadgeResponse> listDanger) {
            this.mContext = (AcSuccessBadge) context;
            this.bundle = bundle;
            this.list = listDanger;
        }

        @Override
        public int getCount() {
            return list.size();
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

            EnBadgeResponse enBadgeResponse = list.get(i);
            String token = bundle.getString(AppConstants.TOKEN);
            int userId = bundle.getInt(AppConstants.USER_ID);
            int badgeId = list.get(i).getId();
            int creatorId = bundle.getInt(AppConstants.CREATOR_ID);
            bundle.putInt(AppConstants.BADGE_ID, badgeId);
            MyViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(AcSuccessBadge.this).inflate(R.layout.custom_danger, viewGroup, false);
                viewHolder = new SuccessAdapter.MyViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (MyViewHolder) view.getTag();
            }
            viewHolder.tvNameBadge.setText(enBadgeResponse.getName());
            viewHolder.imgDanger.setImageResource(R.drawable.ic_success);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragmentConfirm dialogFragmentConfirm = new DialogFragmentConfirm();
                    dialogFragmentConfirm.setArguments(bundle);
                    dialogFragmentConfirm.show(getSupportFragmentManager(), AppConstants.DIALOG_TAG);
                }
            });
            return view;
        }


        public class MyViewHolder {
            private TextView tvNameBadge;
            private ImageView imgDanger;

            public MyViewHolder(View view) {
                tvNameBadge = (TextView) view.findViewById(R.id.tvDanger);
                imgDanger = (ImageView) view.findViewById(R.id.imageDanger);
            }
        }
    }

    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }
}