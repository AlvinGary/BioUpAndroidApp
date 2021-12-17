package com.aras.bioup.view.GameOverView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.model.UpscoreResponse;
import com.aras.bioup.repositories.ScoreRepo;

public class GameOverViewModel extends AndroidViewModel {
    private ScoreRepo scoreRepo;
    private static final String TAG = "GameOverViewModel";

    public void init(String token){
        Log.d(TAG, "init: "+token);
        scoreRepo = ScoreRepo.getInstance(token);
    }

    public GameOverViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<UpscoreResponse> results = new MutableLiveData<>();

    public MutableLiveData<UpscoreResponse> uploadscore(String levelID, int score){
        return scoreRepo.upscore(levelID,score);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        scoreRepo.resetInstance();
    }
}
