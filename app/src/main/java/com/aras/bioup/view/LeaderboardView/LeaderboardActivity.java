package com.aras.bioup.view.LeaderboardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.model.Character;
import com.aras.bioup.model.Leaderboard;
import com.aras.bioup.view.HomeView.HomeActivity;
import com.aras.bioup.view.MateriView.PilihMateriActivity;
import com.aras.bioup.view.MateriView.PilihMateriAdapter;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private LeaderboardViewModel leaderboardViewModel;
    private LeaderboardAdapter leaderboardAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private ProgressDialog dialog;
    private ImageView img_back_icon_leaderboard;

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
        setContentView(R.layout.activity_leaderboard);
        img_back_icon_leaderboard=findViewById(R.id.img_back_icon_leaderboard);
        img_back_icon_leaderboard.setOnClickListener(view -> {
            startActivity(new Intent(LeaderboardActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
        dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
        dialog.show();
        recyclerView = findViewById(R.id.rv_leaderboard);
        helper=SharedPreferenceHelper.getInstance(this);
        leaderboardViewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);
        leaderboardViewModel.init(helper.getAccessToken());
        leaderboardViewModel.getLeaderboard();
        leaderboardViewModel.getResultLeaderboard().observe(this, showLeaderboard);
    }

    private List<Leaderboard.Allusers> results = new ArrayList<>();
    private List<Leaderboard.Leaderboards> results1 = new ArrayList<>();
    private int user;
    LinearLayoutManager linearLayoutManager;

    private Observer<Leaderboard> showLeaderboard = leaderboard -> {
        results = leaderboard.getAllusers();
        results1 = leaderboard.getLeaderboards();
        user = leaderboard.getUser();
        linearLayoutManager = new LinearLayoutManager(LeaderboardActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        leaderboardAdapter = new LeaderboardAdapter(LeaderboardActivity.this);
        leaderboardAdapter.setLeaderboards(results1);
        leaderboardAdapter.setAllUser(results);
        leaderboardAdapter.setUser(user);
        recyclerView.setAdapter(leaderboardAdapter);
        if (results != null) {
            dialog.dismiss();
        }
    };
}