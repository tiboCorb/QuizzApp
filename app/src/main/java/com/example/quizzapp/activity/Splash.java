package com.example.quizzapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quizzapp.service.InitDataBase;
import com.example.quizzapp.R;
import com.example.quizzapp.model.QuizzUser;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by corb on 06/05/2020
 */
public class Splash extends Activity {


    /** Durée totale de l'affichage du splashScreen */
    private static  int SPLASH_TIMEOUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        ImageView  ImageToBounce =  findViewById(R.id.imageView);

        // lancement de l'animation
        ImageToBounce.startAnimation(bounceAnimation);

        // On récupère la liste des utilisteurs enregistrées
        final ArrayList<QuizzUser> quizzUsers =  this.getUserList();

        /**
         * une fois le delais écoulé execute le code ci-dessous
         * */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Si ce n'est pas la permière connection on récupère l'utilisteur et on le transmet a l'activité principale
                if(quizzUsers != null && quizzUsers.size() > 0){
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    i.putExtra("quizzUsersName",  quizzUsers.get(0).getNickname());
                    startActivity(i);
                    finish();
                // sinon on initialise la base de données et on créé un nouvelle utilisateur
                }else{
                    startService(new Intent(Splash.this, InitDataBase.class));
                    Intent i = new Intent(Splash.this, CreateUserActivity.class);
                    startActivity(i);
                    finish();
                }
            }


        },SPLASH_TIMEOUT);

    }

    /**
     * fonction qui renvoie la liste des utilisateurs
     */
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


