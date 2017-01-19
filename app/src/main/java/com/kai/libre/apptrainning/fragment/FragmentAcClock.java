package com.kai.libre.apptrainning.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kai.libre.apptrainning.AcBadge;
import com.kai.libre.apptrainning.AcLogin;
import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.common.AppConstants;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.entity.EnClockInStatus;
import com.kai.libre.apptrainning.intents.IntentManager;
import com.kai.libre.apptrainning.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kai on 1/18/2017.
 */

public class FragmentAcClock extends Fragment implements View.OnClickListener {
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

    private Bundle bundle;

    private int avatarId;

    private boolean isCheckId;

    private int statusCheckIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock, container, false);
        bundle = getBundle();
        init(view);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        return view;
    }

    public void init(View view) {
        btnCheckIn = (Button) view.findViewById(R.id.btnCheckIn);
        btnCheckOut = (Button) view.findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(this);

        btnCheckIn.setOnClickListener(this);
        imgMenuNav = (ImageView) view.findViewById(R.id.imgMenuNav);
        imgMenuNav.setOnClickListener(this);
        tvBadge = (TextView) view.findViewById(R.id.tvBadge);
        tvBadge.setOnClickListener(this);
        tvCheck = (TextView) view.findViewById(R.id.tvCheck);
        tvLogut = (TextView) view.findViewById(R.id.tvLogout);
        tvCheck.setOnClickListener(this);
        tvLogut.setOnClickListener(this);
        tvNameEmployee = (TextView) view.findViewById(R.id.tvNameEmployeeClock);
        tvEmail = (TextView) view.findViewById(R.id.tvEmailEmployeeClock);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatarEmployee);
        tokenEmployee = bundle.getString(AppConstants.TOKEN);
        tvNameEmployee.setText(bundle.getString(AppConstants.NAME_EMPLOYEE));
        tvEmail.setText(bundle.getString(AppConstants.EMAIL_EMPLOYEE));
        avatarId = bundle.getInt(AppConstants.AVATAR_ID);
        statusCheckIn = bundle.getInt(AppConstants.STATUS_CHECKIN);
        getAvatarId(avatarId);
        checkClockIn(tokenEmployee);
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
                IntentManager.startActivity(getActivity(), AcBadge.class, bundle, null);
                break;
            case R.id.tvCheck:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tvLogout:
                bundle.clear();
                final ProgressDialog   prgDialog = new ProgressDialog(getActivity());
                prgDialog.setMessage(getActivity().getResources().getText(R.string.logout));
                prgDialog.setCancelable(false);
                prgDialog.show();
                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        prgDialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);
                IntentManager.startActivity(getActivity(), AcLogin.class, null, null);
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

    public void checkClockIn(String tokenEmployee) {
        ApiClient.getClient().checkInStatus(tokenEmployee).enqueue(new Callback<EnClockInStatus>() {
            @Override
            public void onResponse(Call<EnClockInStatus> call, Response<EnClockInStatus> response) {
                statusCheckIn = response.body().getCode();
                if (statusCheckIn == 200) {
                    btnCheckIn.setEnabled(false);
                    btnCheckIn.setBackgroundResource(R.drawable.btn_app_gray_selected);
                } else {
                    btnCheckIn.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<EnClockInStatus> call, Throwable t) {

            }
        });
    }

    public void checkIn() {
        try {
            ApiClient.getClient().onShift(tokenEmployee, AppConstants.CLOCK_IN).enqueue(new Callback<EnClockInStatus>() {
                @Override
                public void onResponse(Call<EnClockInStatus> call, Response<EnClockInStatus> response) {
                    EnClockInStatus enClockInStatus = response.body();
                    Toast.makeText(getActivity(), enClockInStatus.getDescription() + " !", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<EnClockInStatus> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkOut() {
        try {
            ApiClient.getClient().onShift(tokenEmployee, AppConstants.CLOCK_OUT).enqueue(new Callback<EnClockInStatus>() {
                @Override
                public void onResponse(Call<EnClockInStatus> call, Response<EnClockInStatus> response) {
                    EnClockInStatus enClockInStatus = response.body();
                    Toast.makeText(getActivity(), enClockInStatus.getDescription() + " !", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<EnClockInStatus> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public Bundle getBundle() {
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }

    @Override
    public void onResume() {
        super.onResume();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        drawer.closeDrawer(GravityCompat.START);
    }
}
