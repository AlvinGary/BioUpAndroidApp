package com.aras.bioup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.aras.bioup.view.HomeView.HomeActivity;
import com.aras.bioup.view.LevelView.LevelActivity;
import com.aras.bioup.view.MateriView.PilihMateriActivity;

public class about_us extends AppCompatActivity {
    ImageView btn_back;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(view -> {
            startActivity(new Intent(about_us.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}