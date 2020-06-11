package com.example.quizzapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */



public class QuizzUser extends RealmObject {

    @PrimaryKey
    private String userId;
    private String nickname;
    private long totalScore;
    private String avatar;
    private String city;
    private String country;
    private RealmList<Result> results;


    public QuizzUser() {
    }

    public QuizzUser(String userId, String nickname, Long totalScore, String avatar) {
        this.userId = userId;
        this.nickname = nickname;

        this.totalScore = totalScore;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RealmList<Result> getResults() {
        return results;
    }

    public void setResults(RealmList<Result> results) {
        this.results = results;
    }
}
