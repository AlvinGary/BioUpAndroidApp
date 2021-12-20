package com.aras.bioup.retrofit;

import com.aras.bioup.helper.Const;
import com.aras.bioup.model.Character;
import com.aras.bioup.model.Leaderboard;
import com.aras.bioup.model.Level;
import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.model.Soal;
import com.aras.bioup.model.TokenResponse;
import com.aras.bioup.model.UpscoreResponse;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndPoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    public RetrofitService(String token) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")) {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            });
        } else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token).build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder().baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(client.build())
                .build().create(ApiEndPoints.class);
    }

    public static RetrofitService getInstance(String token) {
        if (service == null) {
            service = new RetrofitService(token);
        } else if (!token.equals("")) {
            service = new RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String email, String password) {
        return api.login(email, password);
    }

    public Call<RegisterResponse> register(String username, String email, String password
            , String password_confirmation, String name, String school, String city, String birthyear) {
        return api.register(username, email, password, password_confirmation, name, school, city, birthyear);
    }

    public Call<JsonObject> logout() {
        return api.logout();
    }

    public Call<Character> getCharacters(){return api.getCharacters(); }

    public Call<Level> getLevels(String charID){return api.getLevels(charID); }

    public Call<Soal> getSoals(String levelID){ return api.getSoals(levelID); }

    public Call<UpscoreResponse> upscore(String levelID ,int score) {
        return api.upscore(levelID,score);
    }

    public Call<Leaderboard> getLeaderboard(){return api.getLeaderboard(); }
}
