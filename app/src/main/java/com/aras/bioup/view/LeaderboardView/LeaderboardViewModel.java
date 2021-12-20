package com.aras.bioup.view.LeaderboardView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Leaderboard;
import com.aras.bioup.repositories.CharRepo;
import com.aras.bioup.repositories.LeaderRepo;

public class LeaderboardViewModel extends AndroidViewModel {
    private LeaderRepo leaderRepo;
    private static final String TAG = "LeaderboardViewModel";

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        leaderRepo = LeaderRepo.getInstance(token);
    }

    //Begin of View Model to get all Leaderboard
    private MutableLiveData<Leaderboard> resultLeaderboard = new MutableLiveData<>();
    public void getLeaderboard(){
        resultLeaderboard = leaderRepo.getLeaderboard();
    }
    public LiveData<Leaderboard> getResultLeaderboard(){
        return resultLeaderboard;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        leaderRepo.resetInstance();
    }
}
