package com.aras.bioup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class UpscoreResponse implements Parcelable {

    private int score;

    protected UpscoreResponse(Parcel in) {
        score = in.readInt();
    }

    public static final Creator<UpscoreResponse> CREATOR = new Creator<UpscoreResponse>() {
        @Override
        public UpscoreResponse createFromParcel(Parcel in) {
            return new UpscoreResponse(in);
        }

        @Override
        public UpscoreResponse[] newArray(int size) {
            return new UpscoreResponse[size];
        }
    };

    public static UpscoreResponse objectFromData(String str) {

        return new Gson().fromJson(str, UpscoreResponse.class);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(score);
    }
}
