package com.aras.bioup.view.SoalView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aras.bioup.R;
import com.aras.bioup.helper.Const;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.model.Character;
import com.aras.bioup.model.Soal;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoalActivity extends AppCompatActivity {

    private TextView text_no_soal, text_isi_soal, text_health_player;
    private TextInputLayout text_input_jawaban;
    private ImageView img_soal;
    private Button btn_submit_jawaban;
    private List<Soal.Level> soallevelList;
    private SoalViewModel soalViewModel;
    private SharedPreferenceHelper helper;
    private String levelID;

    public List<Soal.Level> getSoalLevelList(){ return soallevelList; }
    public void setSoallevelList(List<Soal.Level> soallevelList){
        this.soallevelList = soallevelList;
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

        Bundle bundle = getIntent().getExtras();
        levelID = bundle.getString("levelID");

        helper = SharedPreferenceHelper.getInstance(SoalActivity.this);
        soalViewModel = new ViewModelProvider(SoalActivity.this).get(SoalViewModel.class);
        soalViewModel.init(helper.getAccessToken());
        soalViewModel.getSoals(levelID);
        soalViewModel.getResultSoals().observe(SoalActivity.this, showSoals);
    }

    List<Soal.Level> results = new ArrayList<>();
    List<Character.Allchara> results2 = new ArrayList<>();

//    Character.Allchara getChara = new Character.Allchara();
//    int health_player = getChara.getHealthPoint();
//    String isi_soal = soal.getPertanyaan();
//    String img_path = Const.IMG_URL + soal.getImgpath();
//
//            text_isi_soal.setText(isi_soal);
//            text_health_player.setText(String.valueOf(health_player));
//            Glide.with(SoalActivity.this)
//            .load(img_path)
//                    .into(img_soal);
    private Observer<Soal> showSoals = new Observer<Soal>() {
        @Override
        public void onChanged(Soal soal) {
            Soal.Level soalLevel = new Soal.Level();
            String isi_soal = soalLevel.getPertanyaan();

//            results = soal.getLevel();
            text_isi_soal.setText(isi_soal);
        }
    };
}