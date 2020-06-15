package com.example.quizzapp.activity;


import android.content.Intent;
import android.os.Bundle;

import com.example.quizzapp.fragment.CreateFragment;
import com.example.quizzapp.R;
import com.example.quizzapp.fragment.ChooseLanguageFragment;
import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.Quizz;
import com.example.quizzapp.model.QuizzUser;

import java.util.ArrayList;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;



public class CreateActivity extends AppCompatActivity implements ChooseLanguageFragment.SendLanguage , CreateFragment.SendQuestions {

    String language;
    String quizName;
    RealmList<Question> questions;
    final ArrayList<QuizzUser> quizzUsers =  this.getUserList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        questions= new RealmList();

    }

    @Override
    public void sendData(String message, String quizzName) {
        this.language = message;
        this.quizName = quizzName;

    }

    @Override
    public void sendArray(RealmList<Question> questions) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Quizz quizz =realm.createObject(Quizz.class,UUID.randomUUID().toString());
        quizz.setLanguage(language);
        quizz.setQuizzName(quizName);
        quizz.setQuestions(questions);
        quizz.setOwner( realm.where(QuizzUser.class)
                .equalTo("userId",  quizzUsers.get(0).getUserId())
                .findFirst());
        realm.commitTransaction();
        Intent i = new Intent(CreateActivity.this, MainActivity.class);
        i.putExtra("quizzUsersName",  quizzUsers.get(0).getNickname());
        startActivity(i);
        finish();

    }

    public ArrayList<QuizzUser> getUserList() {
        ArrayList<QuizzUser> list = new ArrayList<>();
        Realm realm;
        realm = Realm.getDefaultInstance();
        try {

            RealmResults<QuizzUser> results = realm
                    .where(QuizzUser.class)
                    .findAll();
            list.addAll(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return list;
    }


}
