package com.aras.bioup.view.LevelView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aras.bioup.R;
import com.aras.bioup.model.Level;
import com.aras.bioup.view.HomeView.HomeActivity;
import com.aras.bioup.view.PreSoalView.PreSoalActivity;
import com.aras.bioup.view.SoalView.SoalActivity;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.CardViewViewHolder> {
    private Context context;
    private List<Level.Levels> levelsList;
    private Activity mActivity;

    public LevelAdapter(Context context){
        this.context = context;
    }
    public List<Level.Levels> getLevelsList(){return levelsList;}
    public void setLevelsList(List<Level.Levels> levelsList){
        this.levelsList = levelsList;
    }

    @NonNull
    @Override
    public LevelAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_level, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelAdapter.CardViewViewHolder holder, int position) {
        final Level.Levels results = getLevelsList().get(position);
        if(position == 0){
            holder.level_name.setText("Mudah");
        }
        if(position == 1){
            holder.level_name.setText("Sedang");
        }
        if(position == 2){
            holder.level_name.setText("Susah");
        }
        if(results.getLevel_id() == 16){
            holder.level_name.setText("Ujian Akhir");
        }

        holder.level_score.setText(String.valueOf(results.getScore()));

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PreSoalActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("levelID", String.valueOf(results.getLevel_id()));
            bundle.putString("charID", String.valueOf(results.getCharacter_id()));
            bundle.putString("totalscore", String.valueOf(results.getScore()));
            intent.putExtras(bundle);
            context.startActivity(intent);
            mActivity = (Activity) holder.cardView.getContext();
            mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return levelsList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView level_name, level_score;
        ImageView back_icon;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            level_name = itemView.findViewById(R.id.text_level);
            level_score = itemView.findViewById(R.id.text_jumlah_score_level);
            back_icon = itemView.findViewById(R.id.img_back_level);
            cardView = itemView.findViewById(R.id.cardview_level);
        }
    }
}
