package com.example.stormuserradar2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReboundActivity extends AppCompatActivity {
    int score1 = 0;
    // this is the number of 1 point baskets scored
    int score2 = 0;
    // this is the number of 2 point baskets scored
    int score3 = 0;
    // this is the number of 3 point baskets scored
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebound);

        final TextView text1 = findViewById(R.id.text1);
        final TextView text3 = findViewById(R.id.text3);
        final TextView text5 = findViewById(R.id.text5);

        final Button button1up = findViewById(R.id.button1up);
        button1up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++score1;
                t = findViewById(R.id.text1);
                t.setText(Integer.toString(score1));
            }
        }


        final Button button1down = findViewById(R.id.button1down);
        button1down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --score1;
                t = findViewById(R.id.text1);
                t.setText(Integer.toString(score1));
            }
        }

        final Button button2up = findViewById(R.id.button2up);
        button2up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++score2;
                t = findViewById(R.id.text3)
                t.setText(Integer.toString(score2));
            }
        }

        final Button button2down = findViewById(R.id.button2down);
         button2down.setOnClickListener(new View.OnClickListener());
             @Override
        public void onClick(View view) {
                   --score2;
                   t = findViewById(R.id.text3);
                            t.setText(Integer.toString(score2));
              }
          }
         final Button button3up = findViewById(R.id.button3up);
         button3up.setOnClickListener(new View.OnClickListener() {
               @Override
        public void onClick(View view) {
                    ++score3;
                    t = findViewById(R.id.text5);
                    t.setText(Integer.toString(score3));
                }
          }

         final Button button3down = findViewById(R.id.button3down);
         button3down.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                      --score3;
                      t = findViewById(R.id.text5);
                      t.setText(Integer.toString(score3));
                      }
                 }
     }
}
