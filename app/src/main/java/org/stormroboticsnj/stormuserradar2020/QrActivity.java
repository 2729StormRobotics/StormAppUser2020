package org.stormroboticsnj.stormuserradar2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.stormroboticsnj.stormuserradar2020.dao.StormDao;
import org.stormroboticsnj.stormuserradar2020.models.Whoosh;
import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * This class uses the database to create a QR code. This class should not change much from year to year, if at all.
 */
public class QrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QrActivity.this, StartActivity.class);
                intent.putExtra("delete", true);
                startActivity(intent);
            }
        });

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stormdb").allowMainThreadQueries().build(); //build/import database

        StormDao stormDao = db.stormDao(); //get interface object

        List<Whoosh> whooshList = stormDao.getAllWhooshes(); //get database

        /* build string for qr */
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < whooshList.size(); ++i) {
            output.append(whooshList.get(i).toString());
        }

    if (output.toString().equals("")) {
        new AlertDialog.Builder(this) //confirm with user
                .setTitle("QR Generation")
                .setMessage("No teams have been scouted. At least one team must be scouted before a QR may be generated")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
}   else {
        /* make and display QR code */
        /* this is the code that should never change */

        Display disp = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        ByteArrayOutputStream code = QRCode.from(output.toString()).withSize(size.x-10,size.x-10).stream();
        byte[] byteArray = code.toByteArray();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView myImage = (ImageView) findViewById(R.id.imgQr);

        myImage.setMinimumHeight(size.y - 10);
        myImage.setMinimumWidth(size.x - 10);
        myImage.setMaxHeight(size.y - 10);
        myImage.setMaxWidth(size.x - 10);
        myImage.setImageBitmap(bmp);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
}

    }
}
