package com.aras.bioup.retrofit;

import com.aras.bioup.model.Character;
import com.aras.bioup.model.Level;
import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.model.Soal;
import com.aras.bioup.model.TokenResponse;
import com.aras.bioup.model.UpscoreResponse;
import com.aras.bioup.repositories.ScoreRepo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("username") String username, @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("name") String name,@Field("school") String school,
                                    @Field("city") String city,@Field("birthyear") String birthyear);
    @POST("logout")
    Call<JsonObject> logout();

    @GET("character")
    Call<Character> getCharacters();

    @GET("character/{charID}")
    Call<Level> getLevels(
            @Path("charID") String charID
    );

    @GET("level/{levelID}")
    Call<Soal> getSoals(
            @Path("levelID") String levelID
    );

    @POST("level/upscore")
    @FormUrlEncoded
    Call<UpscoreResponse> upscore(
            @Field("levelID") String levelID, @Field("score") int score
    );

}
