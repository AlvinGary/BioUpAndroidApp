package com.aras.bioup.view.RegisterView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aras.bioup.model.RegisterResponse;
import com.aras.bioup.repositories.AuthRepo;

public class RegisterViewModel  extends AndroidViewModel {
    private AuthRepo authRepo;
    private static final String TAG = "RegisterViewModel";

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        authRepo = AuthRepo.getInstance();
    }

    public MutableLiveData<RegisterResponse> register(String username, String email, String password
            , String password_confirmation, String name, String school, String city, String birthyear){
        return authRepo.register(username, email, password, password_confirmation, name, school, city, birthyear);
    }
}
