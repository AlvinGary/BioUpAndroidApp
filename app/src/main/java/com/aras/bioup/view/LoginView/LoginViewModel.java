package com.aras.bioup.view.LoginView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.TokenResponse;
import com.aras.bioup.repositories.AuthRepo;

public class LoginViewModel extends AndroidViewModel {
    private AuthRepo authRepo;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepo = AuthRepo.getInstance();
    }

    public MutableLiveData<TokenResponse> login(String username, String password) {
        return authRepo.login(username, password);
    }
}
