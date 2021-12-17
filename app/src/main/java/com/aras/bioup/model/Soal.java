package com.aras.bioup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Soal implements Parcelable {

    private List<Soals> soals;

    protected Soal(Parcel in) {
    }

    public static final Creator<Soal> CREATOR = new Creator<Soal>() {
        @Override
        public Soal createFromParcel(Parcel in) {
            return new Soal(in);
        }

        @Override
        public Soal[] newArray(int size) {
            return new Soal[size];
        }
    };

    public static Soal objectFromData(String str) {

        return new Gson().fromJson(str, Soal.class);
    }

    public List<Soals> getSoals() {
        return soals;
    }

    public void setSoals(List<Soals> soals) {
        this.soals = soals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Soals {
        private int id;
        private String pertanyaan;
        private String imgpath;
        private String jawaban;
        private Pivot pivot;

        public static Soals objectFromData(String str) {

            return new Gson().fromJson(str, Soals.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPertanyaan() {
            return pertanyaan;
        }

        public void setPertanyaan(String pertanyaan) {
            this.pertanyaan = pertanyaan;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getJawaban() {
            return jawaban;
        }

        public void setJawaban(String jawaban) {
            this.jawaban = jawaban;
        }

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
        }

        public static class Pivot {
            private int level_id;
            private int soal_id;

            public static Pivot objectFromData(String str) {

                return new Gson().fromJson(str, Pivot.class);
            }

            public int getLevel_id() {
                return level_id;
            }

            public void setLevel_id(int level_id) {
                this.level_id = level_id;
            }

            public int getSoal_id() {
                return soal_id;
            }

            public void setSoal_id(int soal_id) {
                this.soal_id = soal_id;
            }
        }
    }
}
