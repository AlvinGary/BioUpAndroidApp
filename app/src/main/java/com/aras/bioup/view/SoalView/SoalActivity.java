package com.aras.bioup.view.SoalView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.helper.Const;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.model.Character;
import com.aras.bioup.model.Soal;
import com.aras.bioup.view.GameOverView.GameOverActivity;
import com.aras.bioup.view.LoginView.LoginActivity;
import com.aras.bioup.view.MateriView.PilihMateriActivity;
import com.aras.bioup.view.MateriView.PilihMateriViewModel;
import com.aras.bioup.view.PreSoalView.PreSoalActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SoalActivity extends AppCompatActivity {

    private TextView text_no_soal, text_isi_soal, text_health_player;
    private TextInputLayout text_input_jawaban;
    private ImageView img_soal;
    private AppCompatButton btn_exit_pre_soal;
    private Button btn_submit_jawaban;
    private List<Soal.Soals> soallevelList;
    private SoalViewModel soalViewModel;
    private PilihMateriViewModel charViewModel;
    private SharedPreferenceHelper helper;
    private String levelID, jawaban, charID,totalscore;
    private int nosoal = 0, score = 0, health = 0, soalsize = 0;
    private List<Soal.Soals> allsoal;

    public List<Soal.Soals> getSoalLevelList() {
        return soallevelList;
    }

    public void setSoallevelList(List<Soal.Soals> soallevelList) {
        this.soallevelList = soallevelList;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SoalActivity.this);
        builder.setTitle("Exit").setMessage("Kamu yakin mau keluar dari permainan?")
                .setPositiveButton("Iya", (dialogInterface, i) -> {
                    finish();
                    startActivity(new Intent(SoalActivity.this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                })
                .setNegativeButton("Tidak", ((dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        text_no_soal = findViewById(R.id.text_angka_no_soal);
        text_isi_soal = findViewById(R.id.text_soal_soal);
        text_health_player = findViewById(R.id.text_jumlah_health_soal);
        text_input_jawaban = findViewById(R.id.text_input_jawab_soal);
        img_soal = findViewById(R.id.img_soal);
        btn_submit_jawaban = findViewById(R.id.btn_submit_jawab_soal);
        btn_exit_pre_soal = findViewById(R.id.btn_exit_pre_soal);

        btn_exit_pre_soal.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SoalActivity.this);
            builder.setTitle("Exit").setMessage("Kamu yakin mau keluar dari permainan?")
                    .setPositiveButton("Iya", (dialogInterface, i) -> {
                        finish();
                        startActivity(new Intent(SoalActivity.this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    })
                    .setNegativeButton("Tidak", ((dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })).show();
        });

        Bundle bundle = getIntent().getExtras();
        levelID = bundle.getString("levelID");
        health = bundle.getInt("health");
        charID = bundle.getString("charID");
        totalscore = bundle.getString("totalscore");

        text_health_player.setText(String.valueOf(health));
        text_no_soal.setText(String.valueOf(nosoal + 1));

        helper = SharedPreferenceHelper.getInstance(SoalActivity.this);
        soalViewModel = new ViewModelProvider(SoalActivity.this).get(SoalViewModel.class);
        soalViewModel.init(helper.getAccessToken());
        soalViewModel.getSoals(levelID);
        soalViewModel.getResultSoals().observe(SoalActivity.this, showSoals);
        btn_submit_jawaban.setOnClickListener(view -> {
            String jawabanuser = text_input_jawaban.getEditText().getText().toString().replaceAll("\\s+", "").trim();
            if (jawabanuser != null) {
                if (jawaban.equalsIgnoreCase(jawabanuser)) {
                    score += 5;
                    nosoal++;
                    if (nosoal != soalsize) {
                        showSoals2(nosoal);
                        text_input_jawaban.getEditText().getText().clear();
                    }else{
                        Intent intent = new Intent(SoalActivity.this, GameOverActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("levelID", levelID);
                        bundle1.putString("charID", charID);
                        bundle1.putString("totalscore", totalscore);
                        bundle1.putString("check", "lolos");
                        bundle1.putInt("score", score);
                        intent.putExtras(bundle1);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                } else {
                    health -= 1;
                    text_health_player.setText(String.valueOf(health));
                    if (health == 0) {
                        Intent intent1 = new Intent(SoalActivity.this, GameOverActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("levelID", levelID);
                        bundle2.putString("charID", charID);
                        bundle2.putString("totalscore", totalscore);
                        bundle2.putString("check", "gagal");
                        bundle2.putInt("score", score);
                        intent1.putExtras(bundle2);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    } else {
                        nosoal++;
                        if (nosoal != soalsize) {
                            showSoals2(nosoal);
                            text_input_jawaban.getEditText().getText().clear();
                        }else{
                            Intent intent = new Intent(SoalActivity.this, GameOverActivity.class);
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("levelID", levelID);
                            bundle3.putString("charID", charID);
                            bundle3.putString("totalscore", totalscore);
                            bundle3.putString("check", "lolos");
                            bundle3.putInt("score", score);
                            intent.putExtras(bundle3);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    }
                }
            } else {
                Toast.makeText(SoalActivity.this, "Jawaban tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showSoals2(int nosoal) {
        text_isi_soal.setText(allsoal.get(nosoal).getPertanyaan());
        if (allsoal.get(nosoal).getImgpath() != null) {
            Glide.with(SoalActivity.this)
                    .load(Const.BASE_URL + allsoal.get(nosoal).getImgpath())
                    .into(img_soal);
        }
        text_no_soal.setText(String.valueOf(nosoal + 1));
        jawaban = allsoal.get(nosoal).getJawaban().toString().replaceAll("\\s+", "").trim();
    }

    private Observer<Soal> showSoals = soal -> {
        allsoal = soal.getSoals();
        soalsize = allsoal.size();
        text_isi_soal.setText(allsoal.get(nosoal).getPertanyaan());
        if (allsoal.get(nosoal).getImgpath() != null) {
            Glide.with(SoalActivity.this)
                    .load(Const.BASE_URL + allsoal.get(nosoal).getImgpath())
                    .into(img_soal);
        }
        jawaban = allsoal.get(nosoal).getJawaban().toString().replaceAll("\\s+", "").trim();
    };
}