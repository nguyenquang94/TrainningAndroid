package com.kai.libre.apptrainning.services;

import com.kai.libre.apptrainning.entity.EnLoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Kai on 1/11/2017.
 */

public interface LibreServices {

    @POST("/service/user/login")
    public Call<EnLoginResponse> onLogin(@Part("email") String email, @Part("password") String password);
/*

    @Multipart
    @POST("/service/shift")
    public void onShift(@Part("token") String token, @Part("clock_in") int clock_in, Callback<EnCommonResponse> callback);

    @Multipart
    @POST("/service/user")
    public void onShift(@Query("charater") String charater , Callback<EnSearchResponse> callback);

    @Multipart
    @POST("/service/badge")
    public void getListBadge(Callback<EnBadgeResponse> callback);

    @Multipart
    @POST("/service/image/{avatarID}")
    public void getAvatarImage(@Path("avatarId") String avatarId ,Callback<EnCommonResponse> callback);
*/



}
