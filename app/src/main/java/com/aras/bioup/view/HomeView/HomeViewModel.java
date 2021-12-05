package com.aras.bioup.view.HomeView;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aras.bioup.repositories.UserRepo;

public class HomeViewModel extends AndroidViewModel {
    private UserRepo userRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        userRepo = UserRepo.getInstance(token);
    }

    public LiveData<String> logout(){
        userRepo.resetInstance();
        return userRepo.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        userRepo.resetInstance();
    }
}
