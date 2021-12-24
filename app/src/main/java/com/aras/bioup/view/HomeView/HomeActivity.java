package com.aras.bioup.view.HomeView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.about_us;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.view.LeaderboardView.LeaderboardActivity;
import com.aras.bioup.view.LoginView.LoginActivity;
import com.aras.bioup.view.MateriView.PilihMateriActivity;

public class HomeActivity extends AppCompatActivity {
    private Button btn_keluar_home, btn_mulai_home, btn_skor_home, btn_profil_home, btn_about;
    private SharedPreferenceHelper helper;
    private HomeViewModel hvm;
    private Boolean doubleBackToExitPressedOnce=false;
    private int count = 0;
     MediaPlayer player;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        player = MediaPlayer.create(HomeActivity.this, R.raw.harvest);

        AudioManager am =
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                am.getStreamVolume(AudioManager.STREAM_MUSIC),
                0);
        if(player.isPlaying()){
                Toast.makeText(getBaseContext(), "masuk if",
                        Toast.LENGTH_LONG).show();
                player.stop();
                player.reset();
                player.release();
        }
        else if(!player.isPlaying()){
            player.start();
            player.setLooping(true);
            Toast.makeText(getBaseContext(), "masuk else",
                    Toast.LENGTH_LONG).show();


        }




        btn_keluar_home= findViewById(R.id.btn_keluar_home);
        btn_mulai_home = findViewById(R.id.btn_mulai_home);
        btn_skor_home = findViewById(R.id.btn_papan_skor_home);
        btn_about = findViewById(R.id.btn_about);

        helper = SharedPreferenceHelper.getInstance(this);
        hvm = new ViewModelProvider(this).get(HomeViewModel.class);
        hvm.init(helper.getAccessToken());








        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), about_us.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

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

        btn_mulai_home.setOnClickListener(view -> {
            if(view.getId() == R.id.btn_mulai_home){
                finish();
                startActivity(new Intent(this, PilihMateriActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btn_skor_home.setOnClickListener(view -> {
            if(view.getId() == R.id.btn_papan_skor_home){
                finish();
                startActivity(new Intent(this, LeaderboardActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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