package com.kai.libre.apptrainning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;

import com.kai.libre.apptrainning.adapter.BadgeAdapter;
import com.kai.libre.apptrainning.entity.EnUserData;
import com.kai.libre.apptrainning.entity.EnUserResponse;
import com.kai.libre.apptrainning.services.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcBadge extends AppCompatActivity {

    private BadgeAdapter badgeAdapter;

    private GridView gridview;

    private List<EnUserResponse.User_badges> listUserBadge;

    private List<EnUserResponse.Data> listUser;

    private List<EnUserResponse.Badge> listBadge;

    private List<EnUserData> listEnUserDatas;

    private String tokenEmployee;

    private List<String> listNameEmployee;

    private AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_badge);
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
    }

    public void getValuesFromBunlde() {
        Bundle bundle = getBundle();
        tokenEmployee = bundle.getString(AppConstants.TOKEN);
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
                    if (listUserBadge.size() > 0) {
                        for (int l = 0; l < listUserBadge.size(); l++) {
                            creatorId = listUserBadge.get(i).getCreator_id();
                            String colorClass = listUserBadge.get(i).getBadge().getColor_class();
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
                    Log.d("nguyenquang",countDanger +"");
                    Log.d("nguyenquang1",countSuccess +"");
                    EnUserData enUserData = new EnUserData(id, name, countDanger, countSuccess, creatorId, avatarId);
                    listEnUserDatas.add(enUserData);
                }

            }

            @Override
            public void onFailure(Call<EnUserResponse> call, Throwable t) {

            }
        });
    }

    public void countBadge() {

    }

    public void init() {
        gridview = (GridView) findViewById(R.id.gridview);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edtSearch);
    }

    public Bundle getBundle() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null)
            bundle = new Bundle();
        return bundle;
    }
}
