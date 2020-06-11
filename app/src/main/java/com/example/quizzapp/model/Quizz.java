package com.example.quizzapp.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */

public class Quizz extends RealmObject {

    @PrimaryKey
    private String quizzId;
    private String quizzName;
    private RealmList<Question> questions;
    private String language;
    private QuizzUser owner;


    public Quizz(String quizzId, String quizzName) {
        this.quizzId = quizzId;
        this.quizzName = quizzName;
    }

    public Quizz(){}

    public String getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(String quizzId) {
        this.quizzId = quizzId;
    }

    public String getQuizzName() {
        return quizzName;
    }

    public void setQuizzName(String quizzName) {
        this.quizzName = quizzName;
    }

    public String getLanguage() {
        return language;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public QuizzUser getOwner() {
        return owner;
    }

    public void setOwner(QuizzUser owner) {
        this.owner = owner;
    }
}
