package com.example.stormuserradar2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class ReboundActivity extends AppCompatActivity {
    int score3 = 0;
    int score2 = 0;
    int score1 = 0;
    private TextView text3;
    private TextView text2;
    private TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebound);
    // static final String ARG_PARAM1 = "param1";
    // static final String ARG_PARAM2 = "param2"


// Increases the number for the 3-Point Score
        final Button scoreMore3 = findViewById(R.id.btn2up);
        scoreMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++score3;
                text3 = findViewById(R.id.txtv3);
                text3.setText(Integer.toString(score3));
            }

        });
// Decreases the number for the 3-Point Score
        final Button scoreLess3 = findViewById(R.id.btn3down);
        scoreLess3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --score3;
                text3 = findViewById(R.id.txtv3);
                text3.setText(Integer.toString(score3));

            }
        });
//Increases the number for the 2-Point Score
        final Button scoreMore2 = findViewById(R.id.btn2up);
        scoreMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++score2;
                text2 = findViewById(R.id.txtv2);
                text2.setText(Integer.toString(score2));
            }
        });
//Decreases the number for the 2-Point score
        final Button scoreLess2 = findViewById(R.id.btn2down);
        scoreLess2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --score2;
                text2 = findViewById(R.id.txtv2);
                text2.setText(Integer.toString(score2));
            }
        });
//Increases the number for the 1-Point score
        final Button scoreMore1 = findViewById(R.id.btn1up);
        scoreMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++score1;
                text1 = findViewById(R.id.txtv1);
                text1.setText(Integer.toString(score1));
            }
        });
//Decreases the number for the 1-Point score
        final Button ScoreLess1 = findViewById(R.id.btn1down);
        ScoreLess1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --score1;
                text1 = findViewById(R.id.txtv1);
                text1.setText(Integer.toString(score1));
            }
        });

    }

}
