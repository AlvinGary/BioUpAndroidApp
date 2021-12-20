package com.aras.bioup.model;

import com.google.gson.Gson;

import java.util.List;

public class Leaderboard {

    private int user;
    private List<Leaderboards> leaderboards;
    private List<Allusers> allusers;

    public static Leaderboard objectFromData(String str) {

        return new Gson().fromJson(str, Leaderboard.class);
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public List<Leaderboards> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<Leaderboards> leaderboards) {
        this.leaderboards = leaderboards;
    }

    public List<Allusers> getAllusers() {
        return allusers;
    }

    public void setAllusers(List<Allusers> allusers) {
        this.allusers = allusers;
    }

    public static class Leaderboards {
        private int id;
        private int user_id;
        private int totalscore;
        private String created_at;
        private String updated_at;

        public static Leaderboards objectFromData(String str) {

            return new Gson().fromJson(str, Leaderboards.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getTotalscore() {
            return totalscore;
        }

        public void setTotalscore(int totalscore) {
            this.totalscore = totalscore;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class Allusers {
        private int id;
        private String username;

        public static Allusers objectFromData(String str) {

            return new Gson().fromJson(str, Allusers.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
