package com.example.quizzapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.blongho.country_data.World;
import com.example.quizzapp.R;
import com.example.quizzapp.model.QuizzUser;
import com.example.quizzapp.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_create;
    private FloatingActionButton fab_play;
    private FloatingActionButton fab_share;
    private TextView userName_text;
    private TextView city_text;
    private TextView score;
    private CircleImageView profileImageView;
    private ImageView  flag;
    private Toolbar toolbar;
    private String userName;
    private QuizzUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userName = intent.getStringExtra("quizzUsersName");
        setContentView(R.layout.activity_main);
        this.initView();
        this.initListeners();
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // no inspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        fab_create = findViewById(R.id.fab_create);
        fab_play = findViewById(R.id.fab_play);
        fab_share = findViewById(R.id.fab_share);
        userName_text = findViewById(R.id.username);
        city_text = findViewById(R.id.city);
        flag  = findViewById(R.id.imgViewFlag);
        score = findViewById(R.id.score);
        profileImageView = findViewById(R.id.profileImageView);

        //TODO add chart to view
        //AnyChartView anyChartView = findViewById(R.id.chart_view);
        //Pie pie = AnyChart.pie();

        Realm realm;
        realm = Realm.getDefaultInstance();
        user = realm.where(QuizzUser.class).equalTo("nickname", userName).findFirst();
        RealmResults<Result> results  = realm.where(Result.class).findAll();;
        userName_text.append(userName);
        city_text.append(user.getCity());
        score.append("" +user.getTotalScore());
        flag.setImageResource(World.getFlagOf(user.getCountry()));
        profileImageView.setImageURI(Uri.parse(new File(user.getAvatar()).toString()));

    }

    private void initListeners() {
        fab_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(i);
                finish();
            }
        });

        fab_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Plays.class);
                i.putExtra("quizzUsersName",  user.getNickname());
                startActivity(i);
                finish();
            }
        });


    }
}
