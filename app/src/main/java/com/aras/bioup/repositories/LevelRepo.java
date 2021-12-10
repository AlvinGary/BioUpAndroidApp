package com.aras.bioup.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Level;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LevelRepo {
    private static LevelRepo levelRepo;
    private RetrofitService apiService;
    private static final String TAG = "LevelRepo";

    private LevelRepo(String token){
        Log.d(TAG, "token"+token);
        apiService = RetrofitService.getInstance(token);
    }

    public static LevelRepo getInstance(String token){
        if(levelRepo == null){
            levelRepo = new LevelRepo(token);
        }
        return levelRepo;
    }

    public synchronized void resetInstance(){
        if(levelRepo != null){
            levelRepo = null;
        }
        else{
            levelRepo = null;
        }
    }

    public MutableLiveData<Level> getLevels(){
        final MutableLiveData<Level> listLevels = new MutableLiveData<>();

        apiService.getLevels().enqueue(new Callback<Level>() {
            @Override
            public void onResponse(Call<Level> call, Response<Level> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: "+response.body());
                        listLevels.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Level> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return listLevels;
    }
}
