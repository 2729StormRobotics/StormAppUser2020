package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.stormroboticsnj.stormuserradar2020.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.stormroboticsnj.stormuserradar2020.dao.StormDao;
import org.stormroboticsnj.stormuserradar2020.models.Whoosh;
import org.stormroboticsnj.stormuserradar2020.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements Scoring.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener{
    /* brought from StartActivity */
    private int team;
    private int match;
    private boolean alliance; //true = red

    /* recorded in this activity */
    private int score = 0;
    private int scoreTwo = 0;

    public void incScore () {
        score++;
    }
    public void decScore () {
        score--;
    }
    public int getScore () {
        return score;
    }

    public void incScoreTwo () {
        scoreTwo++;
    }
    public void decScoreTwo () {
        scoreTwo--;
    }
    public int getScoreTwo () {
        return scoreTwo;
    }
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout is activity_main

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager()); //create instance

        ViewPager viewPager = findViewById(R.id.view_pager); //this is the area that changes to each fragment
        viewPager.setAdapter(sectionsPagerAdapter); //tell it to be controlled by the instance
        TabLayout tabs = findViewById(R.id.tabs); //this is the physical tabs
        tabs.setupWithViewPager(viewPager); //sync the two together


        /* get data from Intent (passed from StartActivity) */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            team = extras.getInt("team");
            match = extras.getInt("match");
            alliance = extras.getBoolean("alliance");
        }

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stormdb").allowMainThreadQueries().build(); //build database
    }

    /* submit button pressed, send all data to QR */
    public void submit() {

        StormDao stormDao = db.stormDao(); //get interface object

        /* create Whoosh */
        Whoosh whoosh = new Whoosh(team, match);

        whoosh.setAlliance(alliance);

        whoosh.setScore(score);
        whoosh.setScoreTwo(scoreTwo);

        stormDao.insertWhooshes(whoosh);

        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}