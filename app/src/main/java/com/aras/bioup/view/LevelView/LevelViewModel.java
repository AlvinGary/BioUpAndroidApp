package com.aras.bioup.view.LevelView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Level;
import com.aras.bioup.repositories.LevelRepo;

public class LevelViewModel extends AndroidViewModel {
    private LevelRepo levelRepo;
    private static final String TAG = "LevelViewModel";

    public LevelViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        levelRepo = LevelRepo.getInstance(token);
    }

    //Begin of View Model to get Level by CharID
    private MutableLiveData<Level> resultLevels = new MutableLiveData<>();
    public void getLevels(){
        resultLevels = levelRepo.getLevels();
    }
    public LiveData<Level> getResultLevels(){
        return  resultLevels;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        levelRepo.resetInstance();
    }
}
