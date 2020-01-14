package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import org.stormroboticsnj.stormuserradar2020.dao.StormDao;
import org.stormroboticsnj.stormuserradar2020.models.Whoosh;
import org.stormroboticsnj.stormuserradar2020.ui.main.SectionsPagerAdapter;

/**
 * This Activity is the main data collection activity. It has a tabbed layout; each tab corresponds
 * to a different fragment, as defined in SectionsPagerAdapter. It contains private variables for
 * each field of data collected and increment/decrement or setter as well as getter methods for
 * each.
 */
public class MainActivity extends AppCompatActivity implements Scoring.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener{
    /* brought from StartActivity */
    private int team;
    private int match;
    private boolean alliance; //true = red

    /* recorded in this activity */
    private int score = 0;
    private int scoreTwo = 0;
    private int aPowerCell1 = 0;
    private int aPowerCell2 = 0;
    private int aPowerCell3 = 0;
    private int aPowerCellPickup = 0;

    public void incaPowerCell1 () {
       if (aPowerCell1 < 99) aPowerCell1++;
    }
    public void decaPowerCell1 () {
       if (aPowerCell1 > 0) aPowerCell1--;
    }
    public int getaPowerCell1 () {
        return aPowerCell1;
    }
    public void incaPowerCell2 () {
       if (aPowerCell2 < 99) aPowerCell2++;
    }
    public void decaPowerCell2 () {
       if (aPowerCell2 > 0) aPowerCell2--;
    }
    public int getaPowerCell2 () {
        return aPowerCell2;
    }
    public void incaPowerCell3 () {
       if (aPowerCell3 < 99) aPowerCell3++;
    }
    public void decaPowerCell3 () {
       if (aPowerCell3 > 0) aPowerCell3--;
    }
    public int getaPowerCell3 () {
        return aPowerCell3;
    }
    public void incaPowerCellPickup () {
       if (aPowerCellPickup < 99) aPowerCellPickup++;
    }
    public void decaPowerCellPickup () {
       if (aPowerCellPickup > 0) aPowerCellPickup--;
    }
    public int getaPowerCellPickup () {
        return aPowerCellPickup;
    }


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
    private AppDatabase db; //built on creation of Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout is activity_main

        /* this handles the switching between fragments. see ui.main.SectionsPagerAdapter */
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager()); //\\

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

        /* get database, or build if it doesn't exist. This exact line must be included in the onCreate
        method of every Activity that uses the database. db can be a class-wide variable or local
        within onCreate. */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stormdb").allowMainThreadQueries().build(); //build database
    }

    /* submit button pressed */
    public void submit() {

        StormDao stormDao = db.stormDao(); //get interface object

        /* create Whoosh */
        Whoosh whoosh = new Whoosh(team, match);

        whoosh.setAlliance(alliance);

        //whoosh.setScore(score);
        //whoosh.setScoreTwo(scoreTwo);

        stormDao.insertWhooshes(whoosh);

        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}