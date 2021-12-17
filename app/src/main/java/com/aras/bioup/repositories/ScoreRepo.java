package com.aras.bioup.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.UpscoreResponse;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreRepo {
    private static ScoreRepo scoreRepo;
    private RetrofitService apiService;
    private static final String TAG = "AuthRepo";

    private ScoreRepo(String token){
        Log.d(TAG, "token"+token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ScoreRepo getInstance(String token){
        if(scoreRepo == null){
            scoreRepo = new ScoreRepo(token);
        }
        return scoreRepo;
    }

    public synchronized void resetInstance(){
        if(scoreRepo != null){
            scoreRepo = null;
        }
        else{
            scoreRepo = null;
        }
    }

    public MutableLiveData<UpscoreResponse> upscore (String levelID, int score){
        final MutableLiveData<UpscoreResponse> upscoreResponse = new MutableLiveData<>();
        apiService.upscore(levelID,score).enqueue(new Callback<UpscoreResponse>() {
            @Override
            public void onResponse(Call<UpscoreResponse> call, Response<UpscoreResponse> response) {

            }

            @Override
            public void onFailure(Call<UpscoreResponse> call, Throwable t) {

            }
        });
        return upscoreResponse;
    }
}
