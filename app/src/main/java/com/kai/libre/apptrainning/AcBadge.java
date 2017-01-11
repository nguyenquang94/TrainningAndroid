package com.kai.libre.apptrainning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.kai.libre.apptrainning.adapter.BadgeAdapter;
import com.kai.libre.apptrainning.entity.EnEmployee;

import java.util.ArrayList;
import java.util.List;

public class AcBadge extends AppCompatActivity {

    private BadgeAdapter badgeAdapter;

    private List<EnEmployee> employeeList;

    private GridView gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_badge);
        employeeList = new ArrayList<EnEmployee>();
        init();
        gridview.setAdapter(new BadgeAdapter(this, employeeList));

    }

    public void init() {
        gridview = (GridView) findViewById(R.id.gridview);
    }
}
