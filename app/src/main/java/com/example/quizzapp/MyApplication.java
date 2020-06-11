package com.example.quizzapp;

import android.app.Application;

import com.blongho.country_data.World;

import io.realm.Realm;
/**
 * Created by tiboCorb on 07/05/2020
 * init realm object
 */

public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        World.init(getApplicationContext());
    }
}
