package com.aras.bioup.view.LevelView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Bundle bundle = getIntent().getExtras();
        charID = bundle.getString("charID");

        recyclerView = findViewById(R.id.rv_level);
        helper = SharedPreferenceHelper.getInstance(LevelActivity.this);
        levelViewModel = new ViewModelProvider(LevelActivity.this).get(LevelViewModel.class);
        levelViewModel.init(helper.getAccessToken());
        levelViewModel.getLevels(charID);
        levelViewModel.getResultLevels().observe(LevelActivity.this, showLevels);

    }

    List<Level.Levels> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Level>  showLevels = new Observer<Level>() {
        @Override
        public void onChanged(Level level) {
            results = level.getLevels();
            linearLayoutManager = new LinearLayoutManager(LevelActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            levelAdapter = new LevelAdapter(LevelActivity.this);
            levelAdapter.setLevelsList(results);
            recyclerView.setAdapter(levelAdapter);
        }
    };
}