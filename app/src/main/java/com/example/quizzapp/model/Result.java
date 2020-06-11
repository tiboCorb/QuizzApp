package com.example.quizzapp.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */

public class Result extends RealmObject {

    @PrimaryKey
    private String resultId;

    private QuizzUser user;

    private Quizz quizz;

    private Long score;


    public Result(String resultId, Long score) {
        this.resultId = resultId;

        this.score = score;
    }


    public Result() {
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public QuizzUser getUser() {
        return user;
    }

    public void setUser(QuizzUser user) {
        this.user = user;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
