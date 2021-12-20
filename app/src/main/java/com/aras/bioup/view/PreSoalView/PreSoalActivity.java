package com.aras.bioup.view.PreSoalView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.aras.bioup.view.LevelView.LevelActivity;
import com.aras.bioup.view.MateriView.PilihMateriViewModel;
import com.aras.bioup.view.SoalView.SoalActivity;
import com.aras.bioup.view.SoalView.SoalViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PreSoalActivity extends AppCompatActivity {

    private TextView text_chara_pre, text_jumlah_soal, text_isi_materi;
    private ImageView img_chara_pre, img_back_icon_pre_soal;
    private PilihMateriViewModel charViewModel;
    private SoalViewModel soalViewModel;
    private Button btn_mulai_soal;
    private SharedPreferenceHelper helper;
    private String levelID, charID,totalscore;
    private int health;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LevelActivity.class);
        Bundle bundle3 = new Bundle();
        bundle3.putString("charID", charID);
        intent.putExtras(bundle3);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_soal);
        text_chara_pre = findViewById(R.id.text_nama_karakter_pre_soal);
        text_jumlah_soal = findViewById(R.id.text_jumlah_soal_pre_soal);
        text_isi_materi = findViewById(R.id.text_isi_materi_pre_soal);
        img_chara_pre = findViewById(R.id.img_character_pre_soal);
        btn_mulai_soal = findViewById(R.id.btn_mulai_soal);
        img_back_icon_pre_soal = findViewById(R.id.img_back_icon_pre_soal);

        Bundle bundle = getIntent().getExtras();
        levelID = bundle.getString("levelID");
        charID = bundle.getString("charID");
        totalscore = bundle.getString("totalscore");

        img_back_icon_pre_soal.setOnClickListener(view -> {
            Intent intent = new Intent(this, LevelActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("charID", charID);
            intent.putExtras(bundle1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        btn_mulai_soal.setOnClickListener(view -> {
            Intent intent = new Intent(PreSoalActivity.this, SoalActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("levelID", levelID);
            bundle2.putInt("health", health);
            bundle2.putString("charID", charID);
            bundle2.putString("totalscore", totalscore);
            intent.putExtras(bundle2);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        helper = SharedPreferenceHelper.getInstance(this);
        charViewModel = new ViewModelProvider(this).get(PilihMateriViewModel.class);
        charViewModel.init(helper.getAccessToken());
        charViewModel.getCharacters();
        charViewModel.getResultCharacters().observe(this, showCharacters);
        soalViewModel = new ViewModelProvider(this).get(SoalViewModel.class);
        soalViewModel.init(helper.getAccessToken());
        soalViewModel.getSoals(levelID);
        soalViewModel.getResultSoals().observe(this, jumlahSoal);
    }

    private List<Character.Allchara> allchara;
    private Observer<Character> showCharacters = character -> {
        allchara = character.getAllchara();
        for (int i = 0; i < allchara.size(); i++) {
            if (Integer.parseInt(charID) == allchara.get(i).getId()) {
                text_chara_pre.setText(allchara.get(i).getNama());
                Glide.with(PreSoalActivity.this)
                        .load(Const.BASE_URL + allchara.get(i).getCharimgpath_png())
                        .into(img_chara_pre);
                health = allchara.get(i).getHealthPoint();
                if (Integer.parseInt(charID) == 1) {
                    text_isi_materi.setText("Pertumbuhan dan Perkembangan Tumbuhan");
                } else if (Integer.parseInt(charID) == 2) {
                    text_isi_materi.setText("Metabolisme");
                } else if (Integer.parseInt(charID) == 3) {
                    text_isi_materi.setText("Hereditas");
                } else if (Integer.parseInt(charID) == 4) {
                    text_isi_materi.setText("Teori Evolusi");
                } else if (Integer.parseInt(charID) == 5) {
                    text_isi_materi.setText("Bioteknologi");
                } else if (Integer.parseInt(charID) == 6) {
                    text_isi_materi.setText("Gabungan dari materi 1 sampai 5");
                }
            }
        }

    };
    private List<Soal.Soals> allsoal;
    private Observer<Soal> jumlahSoal = soal -> {
        allsoal = soal.getSoals();
        text_jumlah_soal.setText(String.valueOf(allsoal.size()));

    };
}