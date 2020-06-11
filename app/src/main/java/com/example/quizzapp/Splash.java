package com.example.quizzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quizzapp.model.QuizzUser;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by corb on 06/05/2020
 */
public class Splash extends Activity {

    private static  int SPLASH_TIMEOUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //view actions
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        ImageView  ImageToBounce =  findViewById(R.id.imageView);
        ImageToBounce.startAnimation(bounceAnimation);

        // get all recorded user
        final ArrayList<QuizzUser> quizzUsers =  this.getUserList();

        // activity transition
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(quizzUsers != null && quizzUsers.size() > 0){
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    i.putExtra("quizzUsersName",  quizzUsers.get(0).getNickname());
                    startActivity(i);
                    finish();

                }else{
                    startService(new Intent(Splash.this, InitDataBase.class));
                    Intent i = new Intent(Splash.this, CreateUserActivity.class);
                    startActivity(i);
                    finish();
                }
            }


        },SPLASH_TIMEOUT);

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


