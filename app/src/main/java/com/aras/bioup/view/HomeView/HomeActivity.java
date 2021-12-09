package com.aras.bioup.view.HomeView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.view.LoginView.LoginActivity;

public class HomeActivity extends AppCompatActivity {
    private Button btn_keluar_home, btn_mulai_home, btn_skor_home, btn_profil_home;
    private SharedPreferenceHelper helper;
    private HomeViewModel hvm;
    private Boolean doubleBackToExitPressedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_keluar_home= findViewById(R.id.btn_keluar_home);
        btn_mulai_home = findViewById(R.id.btn_mulai_home);
        btn_skor_home = findViewById(R.id.btn_papan_skor_home);
        btn_profil_home = findViewById(R.id.btn_profil_home);

        helper = SharedPreferenceHelper.getInstance(this);
        hvm = new ViewModelProvider(this).get(HomeViewModel.class);
        hvm.init(helper.getAccessToken());

        btn_keluar_home.setOnClickListener(view -> {
            if (view.getId() == R.id.btn_keluar_home){
                hvm.logout().observe(this, s -> {
                    if (!s.isEmpty()){
                        helper.clearPref();
                        finish();
                        startActivity(new Intent(this, LoginActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}