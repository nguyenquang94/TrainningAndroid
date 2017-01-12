package com.kai.libre.apptrainning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcClock extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgMenuNav;

    private TextView tvBadge;

    private TextView tvCheck;

    private TextView tvLogut;

    private TextView tvNameEmployee;

    private TextView tvEmail;

    private Button btnCheckIn;

    private Button btnCheckOut;

    private ImageView imgAvatar;

    private DrawerLayout drawer;

    private String tokenEmployee;

    private int avatarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_clock);
        init();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void init() {
        btnCheckIn = (Button) findViewById(R.id.btnCheckIn);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(this);
        btnCheckIn.setOnClickListener(this);
        imgMenuNav = (ImageView) findViewById(R.id.imgMenuNav);
        imgMenuNav.setOnClickListener(this);
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

    public void getValuesFromBunlde() {
        Bundle bundle = getBundle();
        tokenEmployee = bundle.getString(AppConstants.TOKEN);
        tvNameEmployee.setText(bundle.getString(AppConstants.NAME_EMPLOYEE));
        tvEmail.setText(bundle.getString(AppConstants.EMAIL_EMPLOYEE));
        avatarId = bundle.getInt(AppConstants.AVATAR_ID);
        getAvatarId(avatarId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenuNav:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.tvBadge:
                startActivity(new Intent(AcClock.this, AcBadge.class));
                break;
            case R.id.tvCheck:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tvLogout:
                break;
            case R.id.btnCheckIn:
                checkIn();
                btnCheckIn.setEnabled(false);
                btnCheckIn.setBackgroundResource(R.drawable.btn_app_gray_selected);
                break;
            case R.id.btnCheckOut:
                checkOut();
                btnCheckOut.setEnabled(false);
                btnCheckOut.setBackgroundResource(R.drawable.btn_app_gray_selected);
                break;
        }
    }

    public void checkIn() {
        try {
            ApiClient.getClient().onShift(tokenEmployee, AppConstants.CLOCK_IN);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkOut() {
        try {
            ApiClient.getClient().onShift(tokenEmployee, AppConstants.CLOCK_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAvatarId(final int avatarId) {
        ApiClient.getClient().getAvatarImage(avatarId).enqueue(new Callback<EnAvatar>() {
            @Override
            public void onResponse(Call<EnAvatar> call, Response<EnAvatar> response) {
                EnAvatar enAvatar = response.body();
                Glide.with(AcClock.this).load(enAvatar.getDescription()).into(imgAvatar);
            }

            @Override
            public void onFailure(Call<EnAvatar> call, Throwable t) {

            }
        });
    }

    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }

}
