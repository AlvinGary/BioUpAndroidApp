package com.aras.bioup.view.MateriView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.model.Character;

import java.util.ArrayList;
import java.util.List;

public class PilihMateriActivity extends AppCompatActivity {

    private PilihMateriViewModel charViewModel;
    private PilihMateriAdapter charAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_materi);

        recyclerView = findViewById(R.id.rv_character);
        helper = SharedPreferenceHelper.getInstance(PilihMateriActivity.this);
        charViewModel = new ViewModelProvider(PilihMateriActivity.this).get(PilihMateriViewModel.class);
        charViewModel.init(helper.getAccessToken());
        charViewModel.getCharacters();
        charViewModel.getResultCharacters().observe(PilihMateriActivity.this, showCharacters);


    }

    List<Character.Characters> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Character> showCharacters = new Observer<Character>() {
        @Override
        public void onChanged(Character character) {
            results = character.getCharacters();
            linearLayoutManager = new LinearLayoutManager(PilihMateriActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            charAdapter = new PilihMateriAdapter(PilihMateriActivity.this);
            charAdapter.setCharactersList(results);
            recyclerView.setAdapter(charAdapter);
        }
    };


}