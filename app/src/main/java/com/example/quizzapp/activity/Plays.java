package com.example.quizzapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizzapp.R;


public class Plays extends AppCompatActivity {

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userName = intent.getStringExtra("quizzUsersName");
        setContentView(R.layout.plays_activity);
    }



}
