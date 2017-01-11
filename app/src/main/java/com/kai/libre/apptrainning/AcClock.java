package com.kai.libre.apptrainning;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AcClock extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgMenuNav;

    private TextView tvBadge;

    private TextView tvCheck;

    private TextView tvLogut;

    private DrawerLayout drawer;

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
        imgMenuNav = (ImageView) findViewById(R.id.imgMenuNav);
        imgMenuNav.setOnClickListener(this);
        tvBadge = (TextView) findViewById(R.id.tvBadge);
        tvBadge.setOnClickListener(this);
        tvCheck = (TextView) findViewById(R.id.tvCheck);
        tvLogut = (TextView) findViewById(R.id.tvLogout);
        tvCheck.setOnClickListener(this);
        tvLogut.setOnClickListener(this);
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
        }
    }

}
