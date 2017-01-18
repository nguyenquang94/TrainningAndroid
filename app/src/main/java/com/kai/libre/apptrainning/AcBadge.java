package com.kai.libre.apptrainning;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.adapter.BadgeAdapter;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.entity.EnUserData;
import com.kai.libre.apptrainning.entity.EnUserResponse;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcBadge extends AppCompatActivity implements View.OnClickListener {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_badge);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        listUserBadge = new ArrayList<EnUserResponse.User_badges>();
        listBadge = new ArrayList<EnUserResponse.Badge>();
        listUser = new ArrayList<EnUserResponse.Data>();
        listEnUserDatas = new ArrayList<EnUserData>();
        listNameEmployee = new ArrayList<String>();
        init();
        getValuesFromBunlde();
        getUser();
        ArrayAdapter adapterPrimaryLanguage = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNameEmployee);
        autoCompleteTextView.setAdapter(adapterPrimaryLanguage);
        autoCompleteTextView.setThreshold(1);

        badgeAdapter = new BadgeAdapter(this, listEnUserDatas);
        gridview.setAdapter(badgeAdapter);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void getValuesFromBunlde() {
        Bundle bundle = getBundle();
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
                Glide.with(AcBadge.this).load(enAvatar.getDescription()).into(imgAvatar);
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
                    EnUserData enUserData = new EnUserData(id, token, name, countDanger, countSuccess, creatorId, avatarId , userId);
                    listEnUserDatas.add(enUserData);
                }

            }

            @Override
            public void onFailure(Call<EnUserResponse> call, Throwable t) {

            }
        });
    }

    public void init() {
        gridview = (GridView) findViewById(R.id.gridview);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edtSearch);
        imgMenuNavBadge = (ImageView) findViewById(R.id.imgMenuNavBadge);
        imgMenuNavBadge.setOnClickListener(this);
        tvBadge = (TextView) findViewById(R.id.tvBadge);
        tvBadge.setOnClickListener(this);
        tvCheck = (TextView) findViewById(R.id.tvCheck);
        tvLogut = (TextView) findViewById(R.id.tvLogout);
        tvCheck.setOnClickListener(this);
        tvLogut.setOnClickListener(this);
        tvNameEmployee = (TextView) findViewById(R.id.tvNameEmployeeClock);
        tvEmail = (TextView) findViewById(R.id.tvEmailEmployeeClock);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatarEmployee);
        getValuesFromBunlde();

    }

    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
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
                finish();
                break;
            case R.id.tvLogout:
                break;
        }
    }
    @Override
    public void onResume() {
        Log.d("davaoday3","01");
        getUser();
        badgeAdapter = new BadgeAdapter(this, listEnUserDatas);
        gridview.setAdapter(badgeAdapter);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRestart()
    {
        Log.d("davaoday3","1");
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}
