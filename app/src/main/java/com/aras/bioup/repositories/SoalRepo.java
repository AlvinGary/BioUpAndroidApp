package com.aras.bioup.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Soal;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalRepo {
    private static SoalRepo soalRepo;
    private RetrofitService apiService;
    private static final String TAG = "SoalRepo";

    private SoalRepo(String token){
        Log.d(TAG, "token"+token);
        apiService = RetrofitService.getInstance(token);
    }

    public static SoalRepo getInstance(String token){
        if(soalRepo == null){
            soalRepo = new SoalRepo(token);
        }
        return soalRepo;
    }

    public synchronized void resetInstance(){
        if(soalRepo != null){
            soalRepo = null;
        }else{
            soalRepo = null;
        }
    }

    public MutableLiveData<Soal> getSoals(String levelID){
        final MutableLiveData<Soal> listSoals = new MutableLiveData<>();

        apiService.getSoals(levelID).enqueue(new Callback<Soal>() {
            @Override
            public void onResponse(Call<Soal> call, Response<Soal> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: "+response.body());
                        listSoals.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Soal> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return listSoals;
    }
}
