package com.aras.bioup.model;

import com.google.gson.Gson;

import java.util.List;

public class Level {

    private List<Levels> levels;

    public static Level objectFromData(String str) {

        return new Gson().fromJson(str, Level.class);
    }

    public List<Levels> getLevels() {
        return levels;
    }

    public void setLevels(List<Levels> levels) {
        this.levels = levels;
    }

    public static class Levels {
        private int levelID;
        private int charID;

        public static Levels objectFromData(String str) {

            return new Gson().fromJson(str, Levels.class);
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
    }
}
