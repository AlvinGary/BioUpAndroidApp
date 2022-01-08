package com.aras.bioup.view.LevelView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.model.Level;
import com.aras.bioup.view.HomeView.HomeActivity;
import com.aras.bioup.view.MateriView.PilihMateriActivity;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    private LevelViewModel levelViewModel;
    private LevelAdapter levelAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private String charID;
    private ImageView img_back_level;
    private ProgressDialog dialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        dialog = ProgressDialog.show(LevelActivity.this, "", "Loading. Please wait...", true);
        img_back_level = findViewById(R.id.img_back_level);
        img_back_level.setOnClickListener(view -> {
            startActivity(new Intent(LevelActivity.this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        Bundle bundle = getIntent().getExtras();
        charID = bundle.getString("charID");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
        recyclerView = findViewById(R.id.rv_level);
        helper = SharedPreferenceHelper.getInstance(LevelActivity.this);
        levelViewModel = new ViewModelProvider(LevelActivity.this).get(LevelViewModel.class);
        levelViewModel.init(helper.getAccessToken());
        levelViewModel.getLevels(charID);
        levelViewModel.getResultLevels().observe(LevelActivity.this, showLevels);
        }else{
            Toast.makeText(this, "Pastikan kamu terhubung ke jaringan internet.", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    List<Level.Levels> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Level> showLevels = level -> {
        results = level.getLevels();
        linearLayoutManager = new LinearLayoutManager(LevelActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        levelAdapter = new LevelAdapter(LevelActivity.this);
        levelAdapter.setLevelsList(results);
        recyclerView.setAdapter(levelAdapter);
        if (results != null) {
            dialog.dismiss();
        }
    };
}