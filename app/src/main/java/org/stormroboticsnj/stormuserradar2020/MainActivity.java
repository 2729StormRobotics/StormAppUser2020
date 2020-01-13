package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

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

    private int tPowerCell1 = 0;
    private int tPowerCell2 = 0;
    private int tPowerCell3 = 0;

    public void incScoreLVL1 () {
        scoreLVL1++;
    }
    private int score = 0;
    private int scoreTwo = 0;
    private int ePowerCell1 = 0;
    private int ePowerCell2 = 0;
    private int ePowerCell3 = 0;
    private int aPowerCell1 = 0;
    private int aPowerCell2 = 0;
    private int aPowerCell3 = 0;
    private int aPowerCellPickup = 0;

    public void incaPowerCell1 () {
        aPowerCell1++;
    }
    public void decaPowerCell1 () {
        aPowerCell1--;
    }
    public int getaPowerCell1 () {
        return aPowerCell1;
    }
    public void incaPowerCell2 () {
        aPowerCell2++;
    }
    public void decaPowerCell2 () {
        aPowerCell2--;
    }
    public int getaPowerCell2 () {
        return aPowerCell2;
    }
    public void incaPowerCell3 () {
        aPowerCell3++;
    }
    public void decaPowerCell3 () {
        aPowerCell3--;
    }
    public int getaPowerCell3 () {
        return aPowerCell3;
    }
    public void incaPowerCellPickup () {
        aPowerCellPickup++;
    }
    public void decaPowerCellPickup () {
        aPowerCellPickup--;
    }
    public int getaPowerCellPickup () {
        return aPowerCellPickup;
    }

    public void incePowerCell1(){
        ePowerCell1++;

    }

    public void decePowerCell1()  {
        ePowerCell1--;
    }
    public void incePowerCell2() {
        ePowerCell2++;
    }
    public void decePowerCell2() {
        ePowerCell2--;
    }
    public void incePowerCell3() {
        ePowerCell3++;
    }
    public void decePowerCell3() {
        ePowerCell3--;
    }
    public int getePowerCell1() {return ePowerCell1;}
    public int getePowerCell2() {return ePowerCell2;}
    public int getePowerCell3() {return ePowerCell3;}

    public void decScoreLVL1 () {tPowerCell1--; }
    public int getScoreLVL1 () {
        return tPowerCell1;
    }

    public void incScoreLVL2 () {
        tPowerCell2++;
    }
    public void decScoreLVL2 () {
        tPowerCell2--;
    }
    public int getScoreLVL2 () {
        return tPowerCell2;
    }

    public void incScoreLVL3 () {
        tPowerCell3++;
    }
    public void decScoreLVL3 () {
        tPowerCell3--;
    }
    public int getScoreLVL3 () {
        return tPowerCell3;
    }
    private long lastPauseTime;
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

        final Chronometer cm = findViewById(R.id.defenseTime);
        final ToggleButton tb = findViewById(R.id.defenseButton);

        cm.setBase(SystemClock.elapsedRealtime());

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    int stoppedMilliseconds = 0;
                    String chronoText = cm.getText().toString();
                    String array[] = chronoText.split(":");
                    if (array.length == 2) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                                + Integer.parseInt(array[1]) * 1000;
                    } else if (array.length == 3) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                                + Integer.parseInt(array[1]) * 60 * 1000
                                + Integer.parseInt(array[2]) * 1000;
                    }
                    cm.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    cm.start();
                } else {
                    lastPauseTime = SystemClock.elapsedRealtime();
                    cm.stop();
                }
            }
        });


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

        final CheckBox pc = findViewById(R.id.cboPC);
        final CheckBox rc = findViewById(R.id.cboRC);

        /* create Whoosh */
        Whoosh whoosh = new Whoosh(team, match);

        whoosh.setAlliance(alliance);
        whoosh.setaPowerCell1(aPowerCell1);
        whoosh.setaPOwerCell2(aPowerCell2);
        whoosh.setaPowerCell3(aPowerCell3);
        whoosh.setaPowerCellPickup(aPowerCellPickup);
        whoosh.settPowerCell1(tPowerCell1);
        whoosh.settPowerCell2(tPowerCell2);
        whoosh.settPowerCell3(tPowerCell3);
        whoosh.setePowerCell1(ePowerCell1);
        whoosh.setePowerCell2(ePowerCell2);
        whoosh.setePowerCell3(ePowerCell3);
        whoosh.setHang(hang);

        stormDao.insertWhooshes(whoosh);

        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
