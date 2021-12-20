package com.aras.bioup.view.GameOverView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.view.MateriView.PilihMateriActivity;
import com.aras.bioup.view.PreSoalView.PreSoalActivity;

public class GameOverActivity extends AppCompatActivity {
    private ImageView img_icon_game_over;
    private TextView text_title_game_over, text_score_game_over,text_score_before_game_over;
    private AppCompatButton btn_coba_lagi_game_over, btn_kembali_game_over;
    private String levelID, charID, check, totalscore;
    private int score;
    private SharedPreferenceHelper helper;
    private GameOverViewModel gameOverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        helper = SharedPreferenceHelper.getInstance(this);
        img_icon_game_over = findViewById(R.id.img_icon_game_over);
        text_title_game_over = findViewById(R.id.text_title_game_over);
        text_score_game_over = findViewById(R.id.text_score_game_over);
        btn_coba_lagi_game_over = findViewById(R.id.btn_coba_lagi_game_over);
        btn_kembali_game_over = findViewById(R.id.btn_kembali_game_over);
        text_score_before_game_over = findViewById(R.id.text_score_before_game_over);

        Bundle bundle = getIntent().getExtras();
        levelID = bundle.getString("levelID");
        charID = bundle.getString("charID");
        check = bundle.getString("check");
        score = bundle.getInt("score");
        totalscore = bundle.getString("totalscore");

        text_score_game_over.setText("Score Kamu: " + score);
        text_score_before_game_over.setText("Score Tertinggi Kamu: " + totalscore);

        gameOverViewModel= new ViewModelProvider(GameOverActivity.this).get(GameOverViewModel.class);
        gameOverViewModel.init(helper.getAccessToken());
        gameOverViewModel.uploadscore(levelID,score);



        if (check.equalsIgnoreCase("gagal")){
            btn_coba_lagi_game_over.setOnClickListener(view -> {
                Intent intent = new Intent(GameOverActivity.this, PreSoalActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("levelID", levelID);
                bundle1.putString("charID", charID);
                intent.putExtras(bundle1);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            });
            btn_kembali_game_over.setOnClickListener(view -> {
                startActivity(new Intent(GameOverActivity.this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            });
        }else if (check.equalsIgnoreCase("lolos")){
            img_icon_game_over.setImageResource(R.drawable.check);
            text_title_game_over.setText("Selamat!!!");

            btn_coba_lagi_game_over.setVisibility(View.GONE);
            btn_kembali_game_over.setOnClickListener(view -> {
                finish();
                startActivity(new Intent(GameOverActivity.this, PilihMateriActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            });
        }
    }
}