package com.aras.bioup.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Leaderboard;
import com.aras.bioup.model.Level;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderRepo {
    private static LeaderRepo leaderRepo;
    private RetrofitService apiService;
    private static final String TAG = "LeaderRepo";

    private LeaderRepo(String token){
        Log.d(TAG, "LeaderRepo: ");
        apiService = RetrofitService.getInstance(token);
    }

    public static LeaderRepo getInstance(String token){
        if(leaderRepo == null){
            leaderRepo = new LeaderRepo(token);
        }
        return leaderRepo;
    }

    public synchronized void resetInstance(){
        if(leaderRepo != null){
            leaderRepo = null;
        }else{
            leaderRepo = null;
        }
    }

    public MutableLiveData<Leaderboard> getLeaderboard(){
        final MutableLiveData<Leaderboard> listLeader = new MutableLiveData<>();

        apiService.getLeaderboard().enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: "+response.body());
                        listLeader.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return listLeader;
    }
}
