package org.stormroboticsnj.stormuserradar2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class StartActivity extends AppCompatActivity {
    /* data to be collected and transferred to next activity */
    private int team;
    private int match;
    private boolean alliance; //red = true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /* on creation of activity, find inputs */
        final Button buttonStart = findViewById(R.id.buttonStart);
        final EditText teamText = findViewById(R.id.teamNum);
        final EditText matchText = findViewById(R.id.matchNum);
        final Button buttonDelete = findViewById(R.id.buttonDelete);
        final Button buttonQR = findViewById(R.id.buttonQR);
        final RadioGroup groupAlliance = findViewById(R.id.allianceGroup);

        /* start button */
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* check that all fields are completed and build error message*/
                String strTeamNum = teamText.getText().toString();
                String strMatchNum = matchText.getText().toString();

                if (strTeamNum.equals("")) teamText.setError("Enter a Team Number");


            }
        });
    }
}
