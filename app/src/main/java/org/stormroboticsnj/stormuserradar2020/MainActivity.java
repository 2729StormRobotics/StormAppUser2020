package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.stormroboticsnj.stormuserradar2020.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.stormroboticsnj.stormuserradar2020.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements Scoring.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        /* get data from Intent (passed from StartActivity) */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            team = extras.getInt("team");
            match = extras.getInt("match");
            alliance = extras.getBoolean("alliance");
        }

    }

    public void submit() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("team", team);
        intent.putExtra("match", match);
        intent.putExtra("alliance", alliance);

        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}