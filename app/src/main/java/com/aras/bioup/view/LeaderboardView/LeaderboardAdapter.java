package com.aras.bioup.view.LeaderboardView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aras.bioup.R;
import com.aras.bioup.model.Leaderboard;
import com.aras.bioup.model.Level;
import com.aras.bioup.view.LevelView.LevelAdapter;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.CardViewViewHolder> {
    private Context context;
    private int user;
    private List<Leaderboard.Leaderboards> leaderboards;

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }

    public List<Leaderboard.Leaderboards> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<Leaderboard.Leaderboards> leaderboards) {
        this.leaderboards = leaderboards;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    private List<Leaderboard.Allusers> allusers;

    public List<Leaderboard.Allusers> getAllUser() {
        return allusers;
    }

    public void setAllUser(List<Leaderboard.Allusers> allusers) {
        this.allusers = allusers;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_leaderboard, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Leaderboard.Leaderboards results = getLeaderboards().get(position);
        if (user == results.getUser_id()) {
            holder.rank.setText(String.valueOf(position + 1));
            holder.cardView.setCardBackgroundColor(Color.GREEN);
            holder.username.setText(allusers.get(position).getUsername());
            holder.totalscore.setText(String.valueOf(results.getTotalscore()));
        } else {
            holder.rank.setText(String.valueOf(position + 1));
            for (int i = 0; 1 < allusers.size(); i++) {
                if (results.getUser_id() == allusers.get(i).getId()) {
                    holder.username.setText(allusers.get(i).getUsername());
                    break;
                }
            }
            holder.totalscore.setText(String.valueOf(results.getTotalscore()));
        }
    }

    @Override
    public int getItemCount() {
        return leaderboards.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private TextView rank, username, totalscore;
        private CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.text_rank);
            username = itemView.findViewById(R.id.text_username);
            totalscore = itemView.findViewById(R.id.text_totalscore);
            cardView = itemView.findViewById(R.id.leaderboard_cardview);
        }
    }
}
