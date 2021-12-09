package com.aras.bioup.view.MateriView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Character;
import com.aras.bioup.repositories.CharRepo;

public class PilihMateriViewModel extends AndroidViewModel {
    private CharRepo charRepo;
    private static final String TAG = "PilihMateriViewModel";

    public PilihMateriViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        charRepo = CharRepo.getInstance(token);
    }

    //Begin of View Model to get all Character
    private MutableLiveData<Character> resultCharacters = new MutableLiveData<>();
    public void getCharacters(){
        resultCharacters = charRepo.getCharacters();
    }
    public LiveData<Character> getResultCharacters(){
        return resultCharacters;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        charRepo.resetInstance();
    }
}
