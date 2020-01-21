package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CheckBox;
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
public class MainActivity extends AppCompatActivity implements Auto.OnFragmentInteractionListener, Teleop.OnFragmentInteractionListener, PathTeleop.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener{
    /* brought from StartActivity */
    private int team; // Team number
    private int match; // Match number
    private boolean alliance; //true = red, false = blue

    //** recorded in this activity **//

    /* Declare variables*/
    // Autonomous
    private int aPowerCell1 = 0; // Power cell score in bottom port
    private int aPowerCell2 = 0; // Power cell score in outer port
    private int aPowerCell3 = 0; // Power cell score in inner port
    private int aPowerCellPickup = 0; // Power cells picked up during Auto

    // Teleop
    private int tPowerCell1 = 0; // Power cell score in bottom port
    private int tPowerCell2 = 0; // Power cell score in outer port
    private int tPowerCell3 = 0; // Power cell score in inner port

    // Robot Map
    private boolean shootSafePortZoneR = false; // Can robot shoot power cell in red safe port zone
    private boolean shootFrontInitLineR = false; // Can robot shoot power cell in front of red initiation line
    private boolean shootBehindInitLineR = false; // Can robot shoot power cell behind red initiation line
    private boolean shootFrontControlPanelR = false; // Can robot shoot power cell in front of red control panel
    private boolean shootBehindControlPanelR = false; // Can robot shoot power cell behind red control panel
    private boolean shootFrontShieldR = false; // Can robot shoot power cell in front of red shield
    private boolean shootBehindShieldR = false; // Can robot shoot power cell behind red shield

    private boolean shootSafePortZoneB = false; // Can robot shoot power cell in blue safe port zone
    private boolean shootFrontInitLineB = false; // Can robot shoot power cell in front of blue initiation line
    private boolean shootBehindInitLineB = false; // Can robot shoot power cell behind blue initiation line
    private boolean shootFrontControlPanelB = false; // Can robot shoot power cell in front of blue control panel
    private boolean shootBehindControlPanelB = false; // Can robot shoot power cell behind blue control panel
    private boolean shootFrontShieldB = false; // Can robot shoot power cell in front of blue shield
    private boolean shootBehindShieldB = false; // Can robot shoot power cell behind blue shield

    // Endgame
    private int ePowerCell1 = 0; // Power cell score in bottom port
    private int ePowerCell2 = 0; // Power cell score in outer port
    private int ePowerCell3 = 0; // Power cell score in inner port

    /*** Increment/Decrement, return, and set methods***/

    // *** Team number, match number, alliance color get methods *** //
    public boolean getAlliance() {
        return alliance;
    }

    public void setAlliance(boolean alliance) {
        this.alliance = alliance;
    }

    // ***Autonomous*** //

    public void incaPowerCell1 () { // Increment power cell score in bottom port
        if (aPowerCell1 < 99) aPowerCell1++;
    }
    public void decaPowerCell1 () { // Decrement power cell score in bottom port
        if (aPowerCell1 > 0) aPowerCell1--;
    }

    public void incaPowerCell2 () { // Increment power cell score in outer port
        if (aPowerCell2 < 99) aPowerCell2++;
    }
    public void decaPowerCell2 () { // Decrement power cell score in outer port
        if (aPowerCell2 > 0) aPowerCell2--;
    }

    public void incaPowerCell3 () { // Increment power cell score in inner port
        if (aPowerCell3 < 99) aPowerCell3++;
    }
    public void decaPowerCell3 () { // Decrement power cell score in inner port
        if (aPowerCell3 > 0) aPowerCell3--;
    }

    public void incaPowerCellPickup () { // Increment number of power cells picked up during Auto
        if (aPowerCellPickup < 99) aPowerCellPickup++;
    }
    public void decaPowerCellPickup () { // Decrement number of power cells picked up during Auto
        if (aPowerCellPickup > 0) aPowerCellPickup--;

        aPowerCell1++;
    }

    /**
     * Get the power cell score on bottom port
     * @return aPowerCell1
     */
    public int getaPowerCell1 () {
        return aPowerCell1;
    }

    /**
     * Get the power cell score on outer port
     * @return aPowerCell2
     */
    public int getaPowerCell2 () {
        return aPowerCell2;
    }

    /**
     * Get the power cell score on inner port
     * @return aPowerCell3
     */
    public int getaPowerCell3 () {
        return aPowerCell3;
    }

    /**
     * Get number of power cells picked up during Auto
     * @return aPowerCellPickup
     */
    public int getaPowerCellPickup () {
        return aPowerCellPickup;
    }

    // ***Teleop*** //

    public void inctPowerCell1 () { // Increment power cell score in bottom port
        if (tPowerCell1 < 99) tPowerCell1++;
    }
    public void dectPowerCell1 () { // Decrement power cell score in bottom port
        if (tPowerCell1 > 0) tPowerCell1--;
    }

    public void inctPowerCell2 () { // Increment power cell score in outer port
        if (tPowerCell2 < 99) tPowerCell2++;
    }
    public void dectPowerCell2 () { // Decrement power cell score in outer port
        if (tPowerCell2 > 0) tPowerCell2--;
    }

    public void inctPowerCell3 () { // Increment power cell score in inner port
        if (tPowerCell3 < 99) tPowerCell3++;
    }
    public void dectPowerCell3 () { // Decrement power cell score in inner port
        if (tPowerCell3 > 0) tPowerCell3--;
    }


    /**
     * Get the power cell score on bottom port
     * @return tPowerCell1
     */
    public int gettPowerCell1() {
        return tPowerCell1;
    }

    /**
     * Get the power cell score on outer port
     * @return tPowerCell2
     */
    public int gettPowerCell2() {
        return tPowerCell2;
    }

    /**
     * Get the power cell score on inner port
     * @return tPowerCell3
     */
    public int gettPowerCell3 () {
        return tPowerCell3;
    }

    // ***Robot Map*** //
        // **Red** //
            public boolean isShootSafePortZoneR() {
                return shootSafePortZoneR;
            }

            public boolean isShootFrontInitLineR() {
                return shootFrontInitLineR;
            }

            public boolean isShootBehindInitLineR() {
                return shootBehindInitLineR;
            }

            public boolean isShootFrontControlPanelR() {
                return shootFrontControlPanelR;
            }

            public boolean isShootBehindControlPanelR() {
                return shootBehindControlPanelR;
            }

            public boolean isShootFrontShieldR() {
                return shootFrontShieldR;
            }

            public boolean isShootBehindShieldR() {
                return shootBehindShieldR;
            }

            public void setShootSafePortZoneR (boolean portZoneR) {
                shootSafePortZoneR = portZoneR;
            }

            public void setShootFrontInitLineR(boolean frontInitLineR) {
                shootFrontInitLineR = frontInitLineR;
            }

            public void setShootBehindInitLineR(boolean behindInitLineR) {
                shootBehindInitLineR = behindInitLineR;
            }

            public void setShootFrontControlPanelR(boolean frontControlPanelR) {
                shootFrontControlPanelR = frontControlPanelR;
            }

            public void setShootBehindControlPanelR(boolean behindControlPanelR) {
                shootBehindControlPanelR = behindControlPanelR;
            }

            public void setShootFrontShieldR(boolean frontShieldR) {
                shootFrontShieldR = frontShieldR;
            }

            public void setShootBehindShieldR(boolean behindShieldR) {
                shootBehindShieldR = behindShieldR;
            }

        // Blue
            public void setShootSafePortZoneB (boolean portZoneB) {
                shootSafePortZoneB = portZoneB;
            }

            public boolean isShootSafePortZoneB() {
                return shootSafePortZoneB;
            }

            public boolean isShootFrontInitLineB() {
                return shootFrontInitLineB;
            }

            public boolean isShootBehindInitLineB() {
                return shootBehindInitLineB;
            }

            public boolean isShootFrontControlPanelB() {
                return shootFrontControlPanelB;
            }

            public boolean isShootBehindControlPanelB() {
                return shootBehindControlPanelB;
            }

            public boolean isShootFrontShieldB() {
                return shootFrontShieldB;
            }

            public boolean isShootBehindShieldB() {
                return shootBehindShieldB;
            }



    // ***Endgame*** //

    public void incePowerCell1() { // Increment power cell score in bottom port
        if (ePowerCell1 < 99) ePowerCell1++;
    }
    public void decePowerCell1() { // Decrement power cell score in bottom port
        if (ePowerCell1 > 0) ePowerCell1--;
    }

    public void incePowerCell2() { // Increment power cell score in outer port
        if (ePowerCell2 < 99) ePowerCell2++;
    }
    public void decePowerCell2() { // Decrement power cell score in outer port
        if (ePowerCell2 > 0) ePowerCell2--;
    }

    public void incePowerCell3() { // Increment power cell score in inner port
        if (ePowerCell3 < 99) ePowerCell3++;
    }
    public void decePowerCell3() { // Decrement power cell score in inner port
        if (ePowerCell3 > 0) ePowerCell3--;
    }


    /**
     * Get the power cell score on bottom port
     * @return ePowerCell1
     */
    public int getePowerCell1() {return ePowerCell1;}

    /**
     * Get the power cell score on outer port
     * @return ePowerCell2
     */
    public int getePowerCell2() {return ePowerCell2;}

    /**
     * Get the power cell score on inner port
     * @return ePowerCell3
     */
    public int getePowerCell3() {return ePowerCell3;}

    private long lastPauseTime; // Defense timer
    private AppDatabase db; //built on creation of Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout is activity_main

        /* this handles the switching between fragments. see ui.main.SectionsPagerAdapter */
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager); //this is the area that changes to each fragment
        viewPager.setAdapter(sectionsPagerAdapter); //tell it to be controlled by the instance
        TabLayout tabs = findViewById(R.id.tabs); //this is the physical tabs
        tabs.setupWithViewPager(viewPager); //sync the two together


        /*final Chronometer cm = findViewById(R.id.defenseTime); // Defense chonometer object
        final ToggleButton tb = findViewById(R.id.defenseButton); // Toggle Button for starting and stopping defense timer*/

        /* Chronometer (Stopwatch) */
        final Chronometer cm = findViewById(R.id.defenseTime); //get stopwatch
        final ToggleButton tb = findViewById(R.id.defenseButton); //get on/off button


        cm.setBase(SystemClock.elapsedRealtime()); //setup stopwatch

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /*  when the stopwatch is started, parse the current text to see what the last
                        paused time was, and start counting from there
                     */
                    int stoppedMilliseconds = 0;
                    String chronoText = cm.getText().toString();
                    String array[] = chronoText.split(":"); //split at colons
                    if (array.length == 2) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 //convert to milliseconds
                                + Integer.parseInt(array[1]) * 1000;
                    } else if (array.length == 3) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                                + Integer.parseInt(array[1]) * 60 * 1000
                                + Integer.parseInt(array[2]) * 1000;
                    }
                    cm.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    cm.start();
                } else {
                    /* save and stop the stopwatch */
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

        final CheckBox rc = findViewById(R.id.cboRC); // Checkbox for rotation control
        final CheckBox pc = findViewById(R.id.cboPC); // Checkbox for position control

        /* create new Whoosh object */
        Whoosh whoosh = new Whoosh(team, match);
        whoosh.setAlliance(alliance); // Set Whoosh alliance instance data to "alliance"

        whoosh.setAPowerCell1(aPowerCell1); // Set auto bottom power cell to "aPowerCell1"
        whoosh.setAPowerCell2(aPowerCell2); // Set auto outer power cell to "aPowerCell2"
        whoosh.setAPowerCell3(aPowerCell3); // Set auto inner power cell to "aPowerCell3"
        whoosh.setAPowerCellPickup(aPowerCellPickup); // Set auto power cell pickup to "aPowerCellPickup"

        whoosh.setTPowerCell1(tPowerCell1); // Set teleop bottom power cell to "tPowerCell1"
        whoosh.setTPowerCell2(tPowerCell2); // Set teleop outer power cell to "tPowerCell2"
        whoosh.setTPowerCell3(tPowerCell3); // Set teleop inner power cell to "tPowerCell3"
        whoosh.setEPowerCell1(ePowerCell1); // Set endgame bottom power cell to "ePowerCell1"
        whoosh.setEPowerCell2(ePowerCell2); // Set endgame outer power cell to "ePowerCell2"
        whoosh.setEPowerCell3(ePowerCell3); // Set endgame inner power cell to "ePowerCell3"
        //whoosh.setHang(hang);

        stormDao.insertWhooshes(whoosh); // Insert data onto database

        Intent intent = new Intent(MainActivity.this, StartActivity.class); // New intent activity - Main Activity
        startActivity(intent); // Start Main Activity page

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
