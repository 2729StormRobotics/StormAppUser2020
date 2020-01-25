package org.stormroboticsnj.stormuserradar2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * This is the default activity for the User App. This Activity collects the Team Number, Match Number,
 * and Alliance that the team is on, before starting the Main Activity. All of these fields are
 * required. This screen also acts as a main menu and can launch the QR activity as well as clear
 * the database.
 */
public class StartActivity extends AppCompatActivity {
    /* data to be collected and transferred to next activity */
    private int team;
    private int match;
    private boolean alliance; //red = true

    private AppDatabase db; //built on creation of Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build(); //build database

        /* get data from Intent, from QR */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            /* if any data exists, this activity was launched by QR.
               Ask User if database should be wiped. Database should be cleared after each scan
               to make future QR codes smaller and not redundant. */
            deleteData();

        }

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
                /* check that all fields are completed and show errors*/
                String strTeamNum = teamText.getText().toString();
                String strMatchNum = matchText.getText().toString();
                int checkedButton = groupAlliance.getCheckedRadioButtonId();
                boolean error = false;

                if (strMatchNum.equals("")) { //no match number entered
                    matchText.setError("Enter a Match Number");
                    error = true;
                    matchText.requestFocus();
                } else if (matchText.length() > 3) {
                    matchText.setError("Match Number Too Large");
                    error = true;
                    matchText.requestFocus();
                } else {
                    team = Integer.parseInt(strMatchNum);
                }

                if (strTeamNum.equals("")) { //no team number entered
                    teamText.setError("Enter a Team Number");
                    error = true;
                    teamText.requestFocus();
                } else if (teamText.length() > 5){
                    teamText.setError("Team Number Too Large");
                    error = true;
                    teamText.requestFocus();
                }else {
                    match = Integer.parseInt(strTeamNum);
                }

                if (checkedButton == -1) { //no alliance selected
                    Context context = getApplicationContext();
                    CharSequence text = "You must pick an Alliance Color";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    error = true;
                } else {
                    alliance = checkedButton == R.id.red;
                }

                if (!error) { //no errors, launch MainActivity, pass on collected data
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("team", team);
                    intent.putExtra("match", match);
                    intent.putExtra("alliance", alliance);

                    startActivity(intent);
                }

            }
        });

        /* delete button */
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        /* qr button */
        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, QrActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * This method creates a Yes/No Dialog Box and clears the database upon confirmation
     */
    private void deleteData() {
        new AlertDialog.Builder(this) //confirm with user
                .setTitle("Clear Data")
                .setMessage("If the data has been scanned, click yes to delete it. Otherwise, keep the data until it is scanned.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //clear database table
                        db.clearAllTables();
                        Toast.makeText(getApplicationContext(), "Database cleared", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }
}
