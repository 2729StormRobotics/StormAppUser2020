package org.stormroboticsnj;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.stormroboticsnj.dao.StormDao;
import org.stormroboticsnj.mainactivity_fragments.Auto;
import org.stormroboticsnj.mainactivity_fragments.Endgame;
import org.stormroboticsnj.mainactivity_fragments.Map;
import org.stormroboticsnj.mainactivity_fragments.SectionsPagerAdapter;
import org.stormroboticsnj.mainactivity_fragments.Teleop;
import org.stormroboticsnj.models.Whoosh;
import org.stormroboticsnj.stormuserradar2020.R;

/**
 * This Activity is the main data collection activity. It has a tabbed layout; each tab corresponds
 * to a different fragment, as defined in SectionsPagerAdapter. It contains private variables for
 * each field of data collected and increment/decrement or setter as well as getter methods for
 * each.
 */
public class MainActivity extends AppCompatActivity implements Auto.OnFragmentInteractionListener, Teleop.OnFragmentInteractionListener, Map.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener {

    public static AppDatabase db; //built on creation of Activity
    /* brought from StartActivity */
    private int team; // Team number
    private int match; // Match number

    //** recorded in this activity **//
    private boolean alliance; //true = red, false = blue
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

    public boolean isPositionControl() {
        return positionControl;
    }

    public void setPositionControl(boolean positionControl) {
        this.positionControl = positionControl;
    }

    private boolean positionControl = false;

    public boolean isRotationControl() {
        return rotationControl;
    }

    public void setRotationControl(boolean rotationControl) {
        this.rotationControl = rotationControl;
    }

    private boolean rotationControl = false;
    private String locations = "";
    private boolean[] both = new boolean[4];
    // Endgame
    private int ePowerCell1 = 0; // Power cell score in bottom port
    private int ePowerCell2 = 0; // Power cell score in outer port
    private int ePowerCell3 = 0; // Power cell score in inner port
    private String endgameOutcome = "";

    public void setClimbSecs(int climbSecs) {
        this.climbSecs = climbSecs;
    }

    private int climbSecs = 0;
    private long lastPauseTime; // Defense timer

    /*** Increment/Decrement, return, and set methods***/

    // *** Team number, match number, alliance color get methods *** //
    public boolean getAlliance() {
        return alliance;
    }

    public void setAlliance(boolean alliance) {
        this.alliance = alliance;
    }

    // ***Autonomous*** //
    public void incaPowerCell1() { // Increment power cell score in bottom port
        if (aPowerCell1 < 99) aPowerCell1++;
    }

    public void decaPowerCell1() { // Decrement power cell score in bottom port
        if (aPowerCell1 > 0) aPowerCell1--;
    }

    public void incaPowerCell2() { // Increment power cell score in outer port
        if (aPowerCell2 < 99) aPowerCell2++;
    }

    public void decaPowerCell2() { // Decrement power cell score in outer port
        if (aPowerCell2 > 0) aPowerCell2--;
    }

    public void incaPowerCell3() { // Increment power cell score in inner port
        if (aPowerCell3 < 99) aPowerCell3++;
    }

    public void decaPowerCell3() { // Decrement power cell score in inner port
        if (aPowerCell3 > 0) aPowerCell3--;
    }

    public void incaPowerCellPickup() { // Increment number of power cells picked up during Auto
        if (aPowerCellPickup < 99) aPowerCellPickup++;
    }

    public void decaPowerCellPickup() { // Decrement number of power cells picked up during Auto
        if (aPowerCellPickup > 0) aPowerCellPickup--;
    }

    /**
     * Get the power cell score on bottom port
     *
     * @return aPowerCell1
     */
    public int getaPowerCell1() { if (aPowerCell1 == 2) both[0]=true;else both[0] = false;
        return aPowerCell1;
    }

    /**
     * Get the power cell score on outer port
     *
     * @return aPowerCell2
     */
    public int getaPowerCell2() {if (aPowerCell2 == 7) both[1] = true;else both[1] = false;
        return aPowerCell2;
    }

    // ***Teleop*** //

    /**
     * Get the power cell score on inner port
     *
     * @return aPowerCell3
     */
    public int getaPowerCell3() {if (aPowerCell3 == 2) both[2] = true;else both[2] = false;
        return aPowerCell3;
    }

    /**
     * Get number of power cells picked up during Auto
     *
     * @return aPowerCellPickup
     */
    public int getaPowerCellPickup() {if (aPowerCellPickup == 9) both[3] = true;else both[3] = false;
        return aPowerCellPickup;
    }

    public void inctPowerCell1() { // Increment power cell score in bottom port
        if (tPowerCell1 < 99) tPowerCell1++;
    }

    public void dectPowerCell1() { // Decrement power cell score in bottom port
        if (tPowerCell1 > 0) tPowerCell1--;
    }

    public void inctPowerCell2() { // Increment power cell score in outer port
        if (tPowerCell2 < 99) tPowerCell2++;
    }

    public void dectPowerCell2() { // Decrement power cell score in outer port
        if (tPowerCell2 > 0) tPowerCell2--;
    }

    public void inctPowerCell3() { // Increment power cell score in inner port
        if (tPowerCell3 < 99) tPowerCell3++;
    }

    public void dectPowerCell3() { // Decrement power cell score in inner port
        if (tPowerCell3 > 0) tPowerCell3--;
    }

    /**
     * Get the power cell score on bottom port
     *
     * @return tPowerCell1
     */
    public int gettPowerCell1() {
        return tPowerCell1;
    }

    // ***Endgame*** //

    /**
     * Get the power cell score on outer port
     *
     * @return tPowerCell2
     */
    public int gettPowerCell2() {
        return tPowerCell2;
    }

    /**
     * Get the power cell score on inner port
     *
     * @return tPowerCell3
     */
    public int gettPowerCell3() {
        return tPowerCell3;
    }

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
    public boolean getBoth(){return both[0] && both[1] && both[2] && both[3];}
    public void decePowerCell3() { // Decrement power cell score in inner port
        if (ePowerCell3 > 0) ePowerCell3--;
    }
    public String getEndgameOutcome() {
        return endgameOutcome;
    }

    public void setEndgameOutcome(String incomingEndgameString) {
        endgameOutcome = incomingEndgameString;
    }

    /**
     * Get the power cell score on bottom port
     *
     * @return ePowerCell1
     */
    public int getePowerCell1() {
        return ePowerCell1;
    }

    /**
     * Get the power cell score on outer port
     *
     * @return ePowerCell2
     */
    public int getePowerCell2() {
        return ePowerCell2;
    }

    /**
     * Get the power cell score on inner port
     *
     * @return ePowerCell3
     */
    public int getePowerCell3() {
        return ePowerCell3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout is activity_main

        /* this handles the switching between fragments. see ui.main.SectionsPagerAdapter */
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager());

        final ViewPager viewPager = findViewById(R.id.view_pager); //this is the area that changes to each fragment
        viewPager.setAdapter(sectionsPagerAdapter); //tell it to be controlled by the instance
        TabLayout tabs = findViewById(R.id.tabs); //this is the physical tabs
        tabs.setupWithViewPager(viewPager); //sync the two together


        /*final Chronometer cm = findViewById(R.id.defenseTime); // Defense chonometer object
        final ToggleButton tb = findViewById(R.id.defenseButton); // Toggle Button for starting and stopping defense timer*/

        /* Chronometer (Stopwatch) */
        final Chronometer cm = findViewById(R.id.defenseTime); //get stopwatch
        final ToggleButton tb = findViewById(R.id.defenseButton); //get on/off button
        final ViewPager vp = findViewById(R.id.view_pager);

        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        tb.setAnimation(scaleAnimation);

        cm.setBase(SystemClock.elapsedRealtime()); //setup stopwatch

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.startAnimation(scaleAnimation);
                if (isChecked) {
                    /*  when the stopwatch is started, parse the current text to see what the last
                        paused time was, and start counting from there
                     */
                    int stoppedMilliseconds = 0;
                    String chronoText = cm.getText().toString();
                    String[] array = chronoText.split(":"); //split at colons
                    if (array.length == 2) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 //convert to milliseconds
                                + Integer.parseInt(array[1]) * 1000;
                    } else if (array.length == 3) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                                + Integer.parseInt(array[1]) * 60 * 1000
                                + Integer.parseInt(array[2]) * 1000;
                    }
                    tb.setTextColor(Color.rgb(204, 0, 0));
                    cm.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    cm.start();
                    viewPager.setVisibility(View.INVISIBLE);
                } else {
                    /* save and stop the stopwatch */
                    lastPauseTime = SystemClock.elapsedRealtime();
                    tb.setTextColor(Color.rgb(34, 34, 34));
                    cm.stop();
                    viewPager.setVisibility(View.VISIBLE);
                }
            }
        });

//        final ImageView logo = findViewById(R.id.irLogo);
//        logo.setMaxWidth(logo.getHeight() * 616/709);


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
        db = AppDatabase.getDatabase(getApplicationContext());
    }

    /* submit button pressed */
    public void submit() {

        //get timer
        final Chronometer cm = findViewById(R.id.defenseTime);
        cm.stop();
        String time = cm.getText().toString();
        String[] arr = time.split(":");
        int stoppedSeconds = 0;
        if (arr.length == 2) {
            stoppedSeconds = Integer.parseInt(arr[0]) * 60 //convert to milliseconds
                    + Integer.parseInt(arr[1]);
        } else if (arr.length == 3) {
            stoppedSeconds = Integer.parseInt(arr[0]) * 60 * 60
                    + Integer.parseInt(arr[1]) * 60
                    + Integer.parseInt(arr[2]);
        }

        final StormDao stormDao = db.stormDao(); //get interface object
        final CheckBox rc = findViewById(R.id.cboRC); // Checkbox for rotation control
        final CheckBox pc = findViewById(R.id.cboPC); // Checkbox for position control

        /*build locations string*/
        ToggleButton bs, bw, fw, fs, bl, fl, sz;
        if (alliance) {
            bs = findViewById(R.id.cboBehindShieldRed);
            bw = findViewById(R.id.cboBehindControlPanelRed);
            fw = findViewById(R.id.cboFrontControlPanelRed);
            fs = findViewById(R.id.cboFrontShieldRed);
            bl = findViewById(R.id.cboBehindLineRed);
            fl = findViewById(R.id.cboFrontLineRed);
            sz = findViewById(R.id.cboPortSafeZoneRed);
        } else {
            bs = findViewById(R.id.cboBehindShieldBlue);
            bw = findViewById(R.id.cboBehindControlPanelBlue);
            fw = findViewById(R.id.cboFrontControlPanelBlue);
            fs = findViewById(R.id.cboFrontShieldBlue);
            bl = findViewById(R.id.cboBehindLineBlue);
            fl = findViewById(R.id.cboFrontLineBlue);
            sz = findViewById(R.id.cboPortSafeZoneBlue);
        }

        if (bs.isChecked()) locations += "BS."; // BS - Behind Shield
        if (fs.isChecked()) locations += "FS."; // FS - Front Shield
        if (bw.isChecked()) locations += "BW."; // BW - Behind Wheel
        if (fw.isChecked()) locations += "FW."; // FW - Front Wheel
        if (bl.isChecked()) locations += "BL."; // BL - Behind Initiation Line
        if (fl.isChecked()) locations += "FL."; // FL - Front Initiation Line
        if (sz.isChecked()) locations += "SZ."; // SZ - Safe Zone

        if (locations.endsWith(".")) {
            locations = locations.substring(0, locations.length() - 1);
        }

        /* create new Whoosh object */
        final Whoosh whoosh = new Whoosh(team, match);
        whoosh.setAlliance(alliance); // Set Whoosh alliance instance data to "alliance"

        whoosh.setAPowerCell1(aPowerCell1); // Set auto bottom power cell to "aPowerCell1"
        whoosh.setAPowerCell2(aPowerCell2); // Set auto outer power cell to "aPowerCell2"
        whoosh.setAPowerCell3(aPowerCell3); // Set auto inner power cell to "aPowerCell3"
        whoosh.setAPowerCellPickup(aPowerCellPickup); // Set auto power cell pickup to "aPowerCellPickup"
        whoosh.setTPowerCell1(tPowerCell1); // Set teleop bottom power cell to "tPowerCell1"
        whoosh.setTPowerCell2(tPowerCell2); // Set teleop outer power cell to "tPowerCell2"

        whoosh.setRotationControl(rotationControl);
        whoosh.setPositionControl(positionControl);
        whoosh.setTPowerCell3(tPowerCell3); // Set teleop inner power cell to "tPowerCell3"
        whoosh.setEPowerCell1(ePowerCell1); // Set endgame bottom power cell to "ePowerCell1"
        whoosh.setEPowerCell2(ePowerCell2); // Set endgame outer power cell to "ePowerCell2"
        whoosh.setEPowerCell3(ePowerCell3); // Set endgame inner power cell to "ePowerCell3"
        if (endgameOutcome.equals("")) endgameOutcome = "N"; //"N" - None
        whoosh.setEndgameOutcome(endgameOutcome); // Set endgame outcome: "P" for Park, "H" for Hang, "L" for Level Hang

        whoosh.setLocations(locations); //set locations

        whoosh.setDefenseSecs(stoppedSeconds); // Set

        whoosh.setClimbSecs(climbSecs);

        //whoosh.setHang(hang);

        stormDao.insertWhooshes(whoosh); // Insert data into database

        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent); // Start Main Activity page

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
