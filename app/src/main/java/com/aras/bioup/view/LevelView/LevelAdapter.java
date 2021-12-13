package com.aras.bioup.view.LevelView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aras.bioup.R;
import com.aras.bioup.model.Level;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.CardViewViewHolder> {
    private Context context;
    private List<Level.Levels> levelsList;

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
        for (int i=0; i<getItemCount(); i++){
            if(i == 0){
                holder.level_name.setText("Easy");
            } else if(i == 1){
                holder.level_name.setText("Medium");
            } else if(i == 2){
                holder.level_name.setText("Hard");
            }
        }
    }

    @Override
    public int getItemCount() {
        return levelsList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView level_name, level_score;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            level_name = itemView.findViewById(R.id.text_level);
            level_score = itemView.findViewById(R.id.text_jumlah_score_level);
        }
    }
}
