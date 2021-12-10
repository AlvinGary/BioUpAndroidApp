package com.aras.bioup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class Level implements Parcelable {

    private int levelID;
    private int charID;

    protected Level(Parcel in) {
        levelID = in.readInt();
        charID = in.readInt();
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

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(levelID);
        parcel.writeInt(charID);
    }
}
