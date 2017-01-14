package com.kai.libre.apptrainning.services;

import com.kai.libre.apptrainning.AppConstants;
import com.kai.libre.apptrainning.entity.EnAvatar;
import com.kai.libre.apptrainning.entity.EnBadgeResponse;
import com.kai.libre.apptrainning.entity.EnClockInStatus;
import com.kai.libre.apptrainning.entity.EnLoginResponse;
import com.kai.libre.apptrainning.entity.EnUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Kai on 1/11/2017.
 */

public interface LibreServices {

    @POST(AppConstants.URL_LOGIN)
    @FormUrlEncoded
    Call<EnLoginResponse> onLogin(@Field("email") String email, @Field("password") String password);

    @POST(AppConstants.URL_CHECK_IN_OUT)
    Call<EnClockInStatus> onShift(@Query("token") String token, @Query("clock_in") int clock_in);

    @GET(AppConstants.URL_USER)
    Call<EnUserResponse> getListUser();

    @GET(AppConstants.URL_CHECK_IN_STATUS)
    Call<EnClockInStatus> checkInStatus(@Query("token") String token);

    @GET(AppConstants.URL_BADGE)
    Call<EnBadgeResponse> getListBadge();

    @GET(AppConstants.URL_AVATAR_ID)
    Call<EnAvatar> getAvatarImage(@Path("avatarId") int avatarId);

}
