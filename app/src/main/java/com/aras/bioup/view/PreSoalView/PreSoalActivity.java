package com.aras.bioup.view.PreSoalView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;

public class PreSoalActivity extends AppCompatActivity {

    private TextView text_chara_pre, text_jumlah_soal, text_isi_materi;
    private ImageView img_chara_pre;
    private Button btn_mulai_soal;
    private SharedPreferenceHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_soal);
        text_chara_pre = findViewById(R.id.text_nama_karakter_pre_soal);
        text_jumlah_soal = findViewById(R.id.text_jumlah_soal_pre_soal);
        text_isi_materi = findViewById(R.id.text_isi_materi_pre_soal);
        img_chara_pre = findViewById(R.id.img_character_pre_soal);
        btn_mulai_soal = findViewById(R.id.btn_mulai_soal);

        helper = SharedPreferenceHelper.getInstance(PreSoalActivity.this);

        
    }
}