package com.aras.bioup.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Character implements Parcelable {

    private List<Userchara> userchara;
    private List<Allchara> allchara;

    protected Character(Parcel in) {
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public static Character objectFromData(String str) {

        return new Gson().fromJson(str, Character.class);
    }

    public List<Userchara> getUserchara() {
        return userchara;
    }

    public void setUserchara(List<Userchara> userchara) {
        this.userchara = userchara;
    }

    public List<Allchara> getAllchara() {
        return allchara;
    }

    public void setAllchara(List<Allchara> allchara) {
        this.allchara = allchara;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Userchara {
        private int id;
        private int healthPoint;
        private String nama;
        private String charimgpath;
        private int reqscore;
        private String charimgpath_png;
        private Pivot pivot;

        public static Userchara objectFromData(String str) {

            return new Gson().fromJson(str, Userchara.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHealthPoint() {
            return healthPoint;
        }

        public void setHealthPoint(int healthPoint) {
            this.healthPoint = healthPoint;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getCharimgpath() {
            return charimgpath;
        }

        public void setCharimgpath(String charimgpath) {
            this.charimgpath = charimgpath;
        }

        public int getReqscore() {
            return reqscore;
        }

        public void setReqscore(int reqscore) {
            this.reqscore = reqscore;
        }

        public String getCharimgpath_png() {
            return charimgpath_png;
        }

        public void setCharimgpath_png(String charimgpath_png) {
            this.charimgpath_png = charimgpath_png;
        }

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
        }

        public static class Pivot {
            private int user_id;
            private int character_id;
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

            public int getCharacter_id() {
                return character_id;
            }

            public void setCharacter_id(int character_id) {
                this.character_id = character_id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }

    public static class Allchara {
        private int id;
        private int healthPoint;
        private String nama;
        private String charimgpath;
        private int reqscore;
        private String charimgpath_png;

        public static Allchara objectFromData(String str) {

            return new Gson().fromJson(str, Allchara.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHealthPoint() {
            return healthPoint;
        }

        public void setHealthPoint(int healthPoint) {
            this.healthPoint = healthPoint;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getCharimgpath() {
            return charimgpath;
        }

        public void setCharimgpath(String charimgpath) {
            this.charimgpath = charimgpath;
        }

        public int getReqscore() {
            return reqscore;
        }

        public void setReqscore(int reqscore) {
            this.reqscore = reqscore;
        }

        public String getCharimgpath_png() {
            return charimgpath_png;
        }

        public void setCharimgpath_png(String charimgpath_png) {
            this.charimgpath_png = charimgpath_png;
        }
    }
}
