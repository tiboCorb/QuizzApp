package com.example.quizzapp.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */

public class Question extends RealmObject {

    @PrimaryKey
    private String questionId;
    private String textQuestion;
    private String answer;
    private Option optionA;
    private Option optionB;
    private Option optionC;
    private Option optionD;

    public Question(String questionId, String textQuestion, String answer) {
        this.questionId = questionId;
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

    public  Question(){

    }


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Option getOptionA() {
        return optionA;
    }

    public void setOptionA(Option optionA) {
        this.optionA = optionA;
    }

    public Option getOptionB() {
        return optionB;
    }

    public void setOptionB(Option optionB) {
        this.optionB = optionB;
    }

    public Option getOptionC() {
        return optionC;
    }

    public void setOptionC(Option optionC) {
        this.optionC = optionC;
    }

    public Option getOptionD() {
        return optionD;
    }

    public void setOptionD(Option optionD) {
        this.optionD = optionD;
    }
}
