package com.aras.bioup.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Character;
import com.aras.bioup.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharRepo {
    private static CharRepo charRepo;
    private RetrofitService apiService;
    private static final String TAG = "CharRepo";

    private CharRepo(String token){
        Log.d(TAG, "token" + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static CharRepo getInstance(String token){
        if(charRepo == null){
            charRepo = new CharRepo(token);
        }
        return charRepo;
    }

    public synchronized void resetInstance(){
        if(charRepo != null){
            charRepo = null;
        }else{
            charRepo = null;
        }
    }

    public MutableLiveData<Character> getCharacters(){
        final MutableLiveData<Character> listCharacters = new MutableLiveData<>();

        apiService.getCharacters().enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: "+response.body());
                        listCharacters.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return listCharacters;
    }
}
