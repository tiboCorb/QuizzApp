package com.example.quizzapp.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */


public class Option extends RealmObject {

    @PrimaryKey
    private String optionId;

    private String textAnswer;

    public Option(String optionID,  String textAnswer) {
        this.optionId = optionID;
        this.textAnswer = textAnswer;
    }

    public Option(){}

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionID) {
        this.optionId = optionID;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }
}
