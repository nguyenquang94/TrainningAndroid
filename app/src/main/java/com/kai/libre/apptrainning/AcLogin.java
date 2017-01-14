package com.kai.libre.apptrainning;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kai.libre.apptrainning.entity.EnClockInStatus;
import com.kai.libre.apptrainning.entity.EnLoginResponse;
import com.kai.libre.apptrainning.intents.IntentManager;
import com.kai.libre.apptrainning.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcLogin extends AppCompatActivity {

    private static final String TAG = AcLogin.class.getSimpleName();

    private static final String SPF_NAME = "saveInfo";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private EditText edtUsername;

    private EditText edtPassword;

    private CheckBox cbRemeber;

    private Button btnLogin;

    protected ProgressDialog prgDialog;

    private int statusCheckIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAction();
            }
        });

    }

    public void init() {
        edtUsername = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbRemeber = (CheckBox) findViewById(R.id.rememberInfo);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public void loginAction() {
        SharedPreferences settings = getSharedPreferences(SPF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        String userName = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (null == userName || userName.length() == 0) {
            Toast.makeText(AcLogin.this, R.string.empty_username, Toast.LENGTH_SHORT).show();
        } else if (null == password || password.length() == 0) {
            Toast.makeText(AcLogin.this, R.string.empty_password, Toast.LENGTH_SHORT).show();
        } else {
            prgDialog = new ProgressDialog(AcLogin.this);
            prgDialog.setMessage(AcLogin.this.getResources().getText(R.string.login));
            prgDialog.setCancelable(false);
            prgDialog.show();
            if (cbRemeber.isChecked()) {
                editor.putString(USERNAME, userName);
                editor.putString(PASSWORD, password);
                editor.commit();
            } else {
                settings = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
                settings.edit().clear().commit();
            }
            onLogin(userName, password);
            prgDialog.dismiss();
        }

    }

    private void onLogin(String email, String password) {

        ApiClient.getClient().onLogin(email, password).enqueue(new Callback<EnLoginResponse>() {
            @Override
            public void onResponse(Call<EnLoginResponse> call, Response<EnLoginResponse> response) {
                EnLoginResponse enLoginResponse = response.body();
                if (enLoginResponse.getToken() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstants.TOKEN, enLoginResponse.getToken());
                    checkInStatus(enLoginResponse.getToken());
                    bundle.putInt(AppConstants.STATUS_CHECKIN, statusCheckIn);
                    bundle.putString(AppConstants.EMAIL_EMPLOYEE, enLoginResponse.getEmail());
                    bundle.putString(AppConstants.NAME_EMPLOYEE, enLoginResponse.getName());
                    bundle.putInt(AppConstants.AVATAR_ID, enLoginResponse.getAvatarId());
                    IntentManager.startActivity(AcLogin.this, AcClock.class, bundle, null);
                }else
                {
                    showAlert();
                }
            }

            @Override
            public void onFailure(Call<EnLoginResponse> call, Throwable t) {

            }
        });
    }

    public void checkInStatus(String tokenEmployee) {
        ApiClient.getClient().checkInStatus(tokenEmployee).enqueue(new Callback<EnClockInStatus>() {
            @Override
            public void onResponse(Call<EnClockInStatus> call, Response<EnClockInStatus> response) {
                statusCheckIn = response.body().getCode();
            }

            @Override
            public void onFailure(Call<EnClockInStatus> call, Throwable t) {

            }
        });
    }

    public void showAlert() {
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage(R.string.eror).setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();

        dialog.show();
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(SPF_NAME,
                Context.MODE_PRIVATE);

        String userName = settings.getString(USERNAME, AppConstants.STRING_BLANK);
        String passWord = settings.getString(PASSWORD, AppConstants.STRING_BLANK);
        edtUsername.setText(userName);
        edtPassword.setText(passWord);
        cbRemeber.setChecked(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    @Override
    public void onPause() {
        super.onPause();
        loginAction();

    }

}
