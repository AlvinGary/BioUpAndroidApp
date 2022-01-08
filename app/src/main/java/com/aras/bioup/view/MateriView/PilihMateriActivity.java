package com.aras.bioup.view.MateriView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
import com.aras.bioup.model.Character;
import com.aras.bioup.view.HomeView.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class PilihMateriActivity extends AppCompatActivity {

    private PilihMateriViewModel charViewModel;
    private PilihMateriAdapter charAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private ImageView img_back_pilih_materi;
    private ProgressDialog dialog;

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
        setContentView(R.layout.activity_pilih_materi);
        dialog = ProgressDialog.show(PilihMateriActivity.this, "", "Loading. Please wait...", true);
        dialog.show();
        img_back_pilih_materi = findViewById(R.id.img_back_pilih_materi);
        img_back_pilih_materi.setOnClickListener(view -> {
            startActivity(new Intent(PilihMateriActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            recyclerView = findViewById(R.id.rv_character);
            helper = SharedPreferenceHelper.getInstance(PilihMateriActivity.this);
            charViewModel = new ViewModelProvider(PilihMateriActivity.this).get(PilihMateriViewModel.class);
            charViewModel.init(helper.getAccessToken());
            charViewModel.getCharacters();
            charViewModel.getResultCharacters().observe(PilihMateriActivity.this, showCharacters);
        } else {
            Toast.makeText(this, "Pastikan kamu terhubung ke jaringan internet.", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    List<Character.Allchara> results = new ArrayList<>();
    List<Character.Userchara> results1 = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Character> showCharacters = character -> {
        results = character.getAllchara();
        results1 = character.getUserchara();
        linearLayoutManager = new LinearLayoutManager(PilihMateriActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        charAdapter = new PilihMateriAdapter(PilihMateriActivity.this);
        charAdapter.setCharactersList(results);
        charAdapter.setUserCharaList(results1);
        recyclerView.setAdapter(charAdapter);
        if (results != null) {
            dialog.dismiss();
        }
    };
}