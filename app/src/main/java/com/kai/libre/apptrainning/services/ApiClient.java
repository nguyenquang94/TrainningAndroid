package com.kai.libre.apptrainning.services;

import com.kai.libre.apptrainning.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    private static LibreServices libreServices = null;
    public static LibreServices getClient() {
        if (retrofit==null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(AppConstants.URL_SERVICES);
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
            libreServices = retrofit.create(LibreServices.class);
        }
        return libreServices;
    }
}
