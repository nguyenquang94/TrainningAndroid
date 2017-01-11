package com.kai.libre.apptrainning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kai.libre.apptrainning.entity.EnLoginResponse;
import com.kai.libre.apptrainning.services.LibreServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kai.libre.apptrainning.services.ApiClient.BASE_URL;

public class AcLogin extends AppCompatActivity {

    private static final String TAG = AcLogin.class.getSimpleName();

    private static final String SPF_NAME = "saveInfo";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private EditText edtUsername;

    private EditText edtPassword;

    private CheckBox cbRemeber;

    private Button btnLogin;

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
            if (cbRemeber.isChecked()) {
                editor.putString(USERNAME, userName);
                editor.putString(PASSWORD, password);
                editor.commit();
            } else {
                settings = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
                settings.edit().clear().commit();
            }
          //  loginProcessWithRetrofit(userName, password);
             startActivity(new Intent(AcLogin.this, AcClock.class));
        }

    }

    private void loginProcessWithRetrofit(final String email, final String password) {
        /*LibreServices mApiService = ApiClient.getClient().create(LibreServices.class);
        Call<EnLoginResponse> mService = mApiService.onLogin(email, password);
        mService.enqueue(new Callback<EnLoginResponse>() {
            @Override
            public void onResponse(Call<EnLoginResponse> call, Response<EnLoginResponse> response) {
                EnLoginResponse mLoginObject = response.body();
                Log.d("nguyenquang", mLoginObject.getName() + "");
                Log.d("username", email);
                Log.d("password", password);
            }

            @Override
            public void onFailure(Call<EnLoginResponse> call, Throwable t) {
                call.cancel();
                Toast.makeText(AcLogin.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });*/

        LibreServices mApiService = this.getInterfaceService();
        Call<EnLoginResponse> mService = mApiService.onLogin(email, password);
        mService.enqueue(new Callback<EnLoginResponse>() {
            @Override
            public void onResponse(Call<EnLoginResponse> call, Response<EnLoginResponse> response) {
                EnLoginResponse mLoginObject = response.body();
                Log.d("gdasdas", mLoginObject.getEmail());

            }
            @Override
            public void onFailure(Call<EnLoginResponse> call, Throwable t) {
                call.cancel();
                Toast.makeText(AcLogin.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    private LibreServices getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LibreServices LibreServices = retrofit.create(LibreServices.class);
        return LibreServices;
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(SPF_NAME,
                Context.MODE_PRIVATE);

        String userName = settings.getString(USERNAME, "");
        String passWord = settings.getString(PASSWORD, "");
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
