package com.kai.libre.apptrainning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.AcBadgeReport;
import com.kai.libre.apptrainning.AcLogin;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.adapter.BadgeAdapter;
import com.kai.libre.apptrainning.common.AppConstants;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.entity.EnUserData;
import com.kai.libre.apptrainning.entity.EnUserResponse;
import com.kai.libre.apptrainning.intents.IntentManager;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/18/2017.
 */

public class FragmentAcBadge extends Fragment implements View.OnClickListener {
    private BadgeAdapter badgeAdapter;

    private GridView gridview;

    private List<EnUserResponse.User_badges> listUserBadge;

    private List<EnUserResponse.Data> listUser;

    private List<EnUserResponse.Badge> listBadge;

    private List<EnUserData> listEnUserDatas;

    private String tokenEmployee;

    private List<String> listNameEmployee;

    private AutoCompleteTextView autoCompleteTextView;

    private TextView tvBadge;

    private TextView tvCheck;

    private TextView tvLogut;

    private TextView tvNameEmployee;

    private TextView tvEmail;

    private Button btnCheckIn;

    private Button btnCheckOut;

    private ImageView imgAvatar;

    private DrawerLayout drawer;

    private ImageView imgMenuNavBadge;

    private int avatarId;

    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_badge, container, false);
        listUserBadge = new ArrayList<EnUserResponse.User_badges>();
        listBadge = new ArrayList<EnUserResponse.Badge>();
        listUser = new ArrayList<EnUserResponse.Data>();
        listEnUserDatas = new ArrayList<EnUserData>();
        listNameEmployee = new ArrayList<String>();
        init(view);
        getValuesFromBunlde();
        getUser();
        ArrayAdapter adapterPrimaryLanguage = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listNameEmployee);
        autoCompleteTextView.setAdapter(adapterPrimaryLanguage);
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                int pos = -1;

                for (int a = 0; a < listNameEmployee.size(); a++) {
                    if (listNameEmployee.get(a).equals(selection)) {
                        pos = a;
                        break;
                    }
                }
                bundle.putString(AppConstants.NAME_EMPLOYEE, listEnUserDatas.get(pos).getName());
                bundle.putInt(AppConstants.USER_ID, listEnUserDatas.get(pos).getUserId());
                bundle.putString(AppConstants.TOKEN, listEnUserDatas.get(pos).getToken());
                bundle.putInt(AppConstants.CREATOR_ID, listEnUserDatas.get(pos).getCreatorId());
                IntentManager.startActivity(getActivity(), AcBadgeReport.class, bundle);
            }
        });

        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        return view;
    }

    public void getValuesFromBunlde() {
        bundle = getBundle();
        tokenEmployee = bundle.getString(AppConstants.TOKEN);
        tvNameEmployee.setText(bundle.getString(AppConstants.NAME_EMPLOYEE));
        tvEmail.setText(bundle.getString(AppConstants.EMAIL_EMPLOYEE));
        avatarId = bundle.getInt(AppConstants.AVATAR_ID);
        getAvatarId(avatarId);
    }

    public void getAvatarId(final int avatarId) {
        ApiClient.getClient().getAvatarImage(avatarId).enqueue(new Callback<EnAvatar>() {
            @Override
            public void onResponse(Call<EnAvatar> call, Response<EnAvatar> response) {
                EnAvatar enAvatar = response.body();
                Glide.with(getActivity()).load(enAvatar.getDescription()).into(imgAvatar);
            }

            @Override
            public void onFailure(Call<EnAvatar> call, Throwable t) {

            }
        });
    }


    public void getUser() {
        ApiClient.getClient().getListUser().enqueue(new Callback<EnUserResponse>() {
            @Override
            public void onResponse(Call<EnUserResponse> call, Response<EnUserResponse> response) {
                listUser = response.body().getData();
                for (int i = 0; i < listUser.size(); i++) {
                    listNameEmployee.add(listUser.get(i).getName());
                    listUserBadge = response.body().getData().get(i).getUser_badges();
                    int id = listUser.get(i).getId();
                    String name = listUser.get(i).getName();
                    int avatarId = listUser.get(i).getAvatar_id();
                    int creatorId = 0;
                    int countDanger = 0;
                    int countSuccess = 0;
                    int userId = 0;
                    String token = listUser.get(i).getToken();
                    if (listUserBadge.size() > 0) {
                        for (int l = 0; l < listUserBadge.size(); l++) {
                            creatorId = listUserBadge.get(l).getCreator_id();
                            String colorClass = listUserBadge.get(l).getBadge().getColor_class();
                            userId = listUserBadge.get(l).getUser_id();

                            switch (colorClass) {
                                case AppConstants.BADGE_DANGER:
                                    countDanger += 1;
                                    break;
                                case AppConstants.BADGE_SUCCESS:
                                    countSuccess += 1;
                                    break;
                            }
                        }
                    }
                    EnUserData enUserData = new EnUserData(id, token, name, countDanger, countSuccess, creatorId, avatarId, userId);
                    listEnUserDatas.add(enUserData);
                    badgeAdapter = new BadgeAdapter(getActivity(), listEnUserDatas);
                    gridview.setAdapter(badgeAdapter);
                }

            }

            @Override
            public void onFailure(Call<EnUserResponse> call, Throwable t) {

            }
        });
    }

    public void init(View view) {
        gridview = (GridView) view.findViewById(R.id.gridview);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.edtSearch);
        imgMenuNavBadge = (ImageView) view.findViewById(R.id.imgMenuNavBadge);
        imgMenuNavBadge.setOnClickListener(this);
        tvBadge = (TextView) view.findViewById(R.id.tvBadge);
        tvBadge.setOnClickListener(this);
        tvCheck = (TextView) view.findViewById(R.id.tvCheck);
        tvLogut = (TextView) view.findViewById(R.id.tvLogout);
        tvCheck.setOnClickListener(this);
        tvLogut.setOnClickListener(this);
        tvNameEmployee = (TextView) view.findViewById(R.id.tvNameEmployeeClock);
        tvEmail = (TextView) view.findViewById(R.id.tvEmailEmployeeClock);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatarEmployee);
        getValuesFromBunlde();

    }

    public Bundle getBundle() {
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgMenuNavBadge:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.tvBadge:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tvCheck:
                getActivity().finish();
                break;
            case R.id.tvLogout:
                bundle.clear();
                IntentManager.startActivity(getActivity(), AcLogin.class, null, null);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
