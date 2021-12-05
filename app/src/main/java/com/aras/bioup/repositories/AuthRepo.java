package com.aras.bioup.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.model.TokenResponse;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepo {
    private static AuthRepo authRepository;
    private RetrofitService apiService;
    private static final String TAG = "AuthRepo";
    private Context context;

    private AuthRepo() {
        apiService = RetrofitService.getInstance("");
    }

    public static AuthRepo getInstance() {
        if (authRepository == null) {
            authRepository = new AuthRepo();
        }
        return authRepository;
    }

    public MutableLiveData<TokenResponse> login(String username, String password) {
        MutableLiveData<TokenResponse> tokenResponse = new MutableLiveData<>();
        apiService.login(username, password).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            Log.d(TAG, "onResponse: " + response.body().getAccess_token());
                            tokenResponse.postValue(response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return tokenResponse;
    }

    public MutableLiveData<RegisterResponse> register(String username, String email, String password
            , String password_confirmation, String name, String school, String city, String birthyear) {
        MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();

        apiService.register(username, email, password, password_confirmation, name, school, city, birthyear).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            Log.d(TAG, "message: " + response.message());
                            registerResponse.postValue(response.body());
                        }
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                    Log.d(TAG, "message: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

        return registerResponse;
    }
}
