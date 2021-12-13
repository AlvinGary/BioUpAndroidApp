package com.aras.bioup.view.MateriView;

import android.content.Context;
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
import com.aras.bioup.helper.Const;
import com.aras.bioup.model.Character;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class PilihMateriAdapter extends RecyclerView.Adapter<PilihMateriAdapter.CardViewViewHolder> {
    private Context context;
    private List<Character.Characters> charactersList;

    public PilihMateriAdapter(Context context) {
        this.context = context;
    }
    public List<Character.Characters> getCharactersList() {
        return charactersList;
    }
    public void setCharactersList(List<Character.Characters> charactersList){
        this.charactersList = charactersList;
    }

    @NonNull
    @Override
    public PilihMateriAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_character, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihMateriAdapter.CardViewViewHolder holder, int position) {
        final Character.Characters results = getCharactersList().get(position);
        holder.char_nama.setText(results.getNama());
        holder.char_jumlah_health.setText(String.valueOf(results.getHealthPoint()));

        Glide.with(context)
                .load(Const.IMG_URL + results.getCharimgpath_png())
                .into(holder.img_char);



        holder.cardView.setOnClickListener(view -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("charID", String.valueOf(results.getCharID()));

        });
    }

    @Override
    public int getItemCount() {
//        return null!=charactersList?charactersList.size():0;
        return charactersList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView char_nama, char_jumlah_health, char_jumlah_score;
        ImageView img_char, img_back_icon;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            char_nama = itemView.findViewById(R.id.text_nama_char);
            char_jumlah_health = itemView.findViewById(R.id.text_jumlah_health);
            char_jumlah_score = itemView.findViewById(R.id.text_jumlah_score_char);
            img_char = itemView.findViewById(R.id.img_char);
            cardView = itemView.findViewById(R.id.cardview_char);
            img_back_icon = itemView.findViewById(R.id.img_back_pilih_materi);
        }
    }
}
