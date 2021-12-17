package com.aras.bioup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Level implements Parcelable {

    private List<Levels> levels;

    protected Level(Parcel in) {
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public static Level objectFromData(String str) {

        return new Gson().fromJson(str, Level.class);
    }

    public List<Levels> getLevels() {
        return levels;
    }

    public void setLevels(List<Levels> levels) {
        this.levels = levels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Levels {
        private int id;
        private int character_id;
        private Pivot pivot;

        public static Levels objectFromData(String str) {

            return new Gson().fromJson(str, Levels.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCharacter_id() {
            return character_id;
        }

        public void setCharacter_id(int character_id) {
            this.character_id = character_id;
        }

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
        }

        public static class Pivot {
            private int user_id;
            private int level_id;
            private int score;

            public static Pivot objectFromData(String str) {

                return new Gson().fromJson(str, Pivot.class);
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getLevel_id() {
                return level_id;
            }

            public void setLevel_id(int level_id) {
                this.level_id = level_id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }
}
