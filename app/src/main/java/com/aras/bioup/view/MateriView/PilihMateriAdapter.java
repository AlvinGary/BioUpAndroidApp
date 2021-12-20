package com.aras.bioup.view.MateriView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aras.bioup.R;
import com.aras.bioup.helper.Const;
import com.aras.bioup.model.Character;
import com.aras.bioup.view.HomeView.HomeActivity;
import com.aras.bioup.view.LevelView.LevelActivity;
import com.aras.bioup.view.LoginView.LoginActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class PilihMateriAdapter extends RecyclerView.Adapter<PilihMateriAdapter.CardViewViewHolder> {
    private Context context;
    private List<Character.Allchara> allcharaList;
    private List<Character.Userchara> usercharaList;
    private Activity mActivity;

    public PilihMateriAdapter(Context context) {
        this.context = context;
    }

    public List<Character.Allchara> getAllcharaList() {
        return allcharaList;
    }

    public void setCharactersList(List<Character.Allchara> allcharaList) {
        this.allcharaList = allcharaList;
    }

    public List<Character.Userchara> getUsercharaList() {
        return usercharaList;
    }

    public void setUserCharaList(List<Character.Userchara> usercharaList) {
        this.usercharaList = usercharaList;
    }


    @NonNull
    @Override
    public PilihMateriAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_character, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihMateriAdapter.CardViewViewHolder holder, int position) {
        final Character.Allchara results = getAllcharaList().get(position);
        int i = 0;
        if (position < usercharaList.size()) { //2
            i = i+ usercharaList.get(position).getPivot().getScore();
            holder.char_nama.setText(results.getNama());
            holder.char_jumlah_health.setText(String.valueOf(results.getHealthPoint()));
            holder.text_jumlah_score_char.setText(String.valueOf(usercharaList.get(position).getPivot().getScore()));

            if (results.getId() == 6) {
                holder.char_jumlah_level.setText("1");
            } else {
                holder.char_jumlah_level.setText("3");
            }

            Glide.with(context)
                    .load(Const.BASE_URL + results.getCharimgpath_png())
                    .into(holder.img_char);

            holder.cardView.setOnClickListener(view -> {
                Intent intent = new Intent(context, LevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("charID", String.valueOf(results.getId()));
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                mActivity = (Activity) holder.cardView.getContext();
                mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                ((Activity) context).finish();
            });
        }else if (i > results.getReqscore()) {
            holder.char_nama.setText(results.getNama());
            holder.char_jumlah_health.setText(String.valueOf(results.getHealthPoint()));

            if (results.getId() == 6) {
                holder.char_jumlah_level.setText("1");
            } else {
                holder.char_jumlah_level.setText("3");
            }

            Glide.with(context)
                    .load(Const.BASE_URL + results.getCharimgpath_png())
                    .into(holder.img_char);

            holder.cardView.setOnClickListener(view -> {
                Intent intent = new Intent(context, LevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("charID", String.valueOf(results.getId()));
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                mActivity = (Activity) holder.cardView.getContext();
                mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                ((Activity) context).finish();
            });
        } else if (i < results.getReqscore()) {
            holder.cardView.setCardBackgroundColor(Color.BLACK);
            holder.char_reqscore.setVisibility(View.VISIBLE);
            holder.char_reqscore.setText("Dibutuhkan total score " + String.valueOf(results.getReqscore()) + " score");
            holder.char_reqscore.setTextColor(Color.WHITE);
            holder.img_gembok.setVisibility(View.VISIBLE);
            holder.img_health_char.setVisibility(View.GONE);
            holder.cardView.setOnClickListener(view -> {
                Snackbar.make(view, "Ups! Kamu tidak memenuhi syarat untuk membuka karakter ini!", Snackbar.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return allcharaList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView char_nama, char_jumlah_health, char_jumlah_score, char_jumlah_level, char_reqscore, text_jumlah_score_char;
        ImageView img_char, img_health_char, img_gembok;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            char_nama = itemView.findViewById(R.id.text_nama_char);
            char_jumlah_health = itemView.findViewById(R.id.text_jumlah_health);
            char_jumlah_score = itemView.findViewById(R.id.text_jumlah_score_char);
            char_jumlah_level = itemView.findViewById(R.id.text_jumlah_level_char);
            img_char = itemView.findViewById(R.id.img_char);
            img_health_char = itemView.findViewById(R.id.img_health_char);
            cardView = itemView.findViewById(R.id.cardview_char);
            char_reqscore = itemView.findViewById(R.id.text_reqscore);
            img_gembok = itemView.findViewById(R.id.img_gembok);
            text_jumlah_score_char = itemView.findViewById(R.id.text_jumlah_score_char);
        }
    }
}
