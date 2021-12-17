package com.aras.bioup.view.SoalView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.Soal;
import com.aras.bioup.repositories.SoalRepo;

public class SoalViewModel extends AndroidViewModel {
    private SoalRepo soalRepo;
    private static final String TAG = "SoalViewModel";

    public SoalViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        soalRepo = SoalRepo.getInstance(token);
    }

    //Begin of ViewModel to get Soal
    private MutableLiveData<Soal> resultSoals = new MutableLiveData<>();
    public void getSoals(String levelID){ resultSoals = soalRepo.getSoals(levelID); }
    public LiveData<Soal> getResultSoals(){ return resultSoals; }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        soalRepo.resetInstance();
    }
}
