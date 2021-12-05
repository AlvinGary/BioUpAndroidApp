package com.aras.bioup.retrofit;

import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.model.TokenResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("username") String username, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("username") String username, @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("name") String name,@Field("school") String school,
                                    @Field("city") String city,@Field("birthyear") String birthyear);
    @POST("logout")
    Call<JsonObject> logout();
}
