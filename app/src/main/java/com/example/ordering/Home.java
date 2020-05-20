package com.example.ordering;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


public class Home extends Activity {
    ImageView play, about, help, vibration, sound;
    String level = "1";
    TextView display_level;
    String collected = "0";
    String soundon = "no";
    String vibrationon = "no";
    String score = "1";
    int soundonoff = 0;
    int vibrationonoff = 0;

    DatabaseHelper db;
    ArrayAdapter < String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHelper(this);

        play = (ImageView) findViewById(R.id.imageView1);
        help = (ImageView) findViewById(R.id.imageView2);
        about = (ImageView) findViewById(R.id.imageView3);
        vibration = (ImageView) findViewById(R.id.imageView4);
        sound = (ImageView) findViewById(R.id.imageView5);

        display_level = (TextView) findViewById(R.id.level_home);
        display_level.setTypeface(null, Typeface.ITALIC);
        display_level.setTextColor(Color.parseColor("#a77d46"));
        Cursor cursor2 = db.alldata();

        if (cursor2.getCount() != 0) {

            while (cursor2.moveToNext()) {
                //Toast.makeText(getApplicationContext(), "First Name:"+cursor.getString(0), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Last Name:"+cursor.getString(1), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Department:"+cursor.getString(2), Toast.LENGTH_SHORT).show();

                level = cursor2.getString(0);

                collected = cursor2.getString(1);


                //Toast.makeText(getApplicationContext(), "level "+level+" coll "+ collected+" score " +score, Toast.LENGTH_SHORT).show();

            }

            display_level.setText("Your Best Score is Level " + level);
        }



        about.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent call = new Intent(getApplicationContext(), About.class);
                call.putExtra("val1", "about");


                startActivity(call);

            }
        });

        help.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent call = new Intent(getApplicationContext(), About.class);
                call.putExtra("val1", "help");


                startActivity(call);

            }
        });

        vibration.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (vibrationonoff == 0) {
                    vibrationon = "off";
                    vibration.setBackgroundResource(R.drawable.voff);
                    vibrationonoff = 1;
                } else if (vibrationonoff == 1) {
                    vibrationon = "on";
                    vibration.setBackgroundResource(R.drawable.von);
                    vibrationonoff = 0;
                }

            }
        });


        sound.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (soundonoff == 0) {
                    soundon = "off";
                    sound.setBackgroundResource(R.drawable.off);
                    soundonoff = 1;
                } else if (soundonoff == 1) {
                    soundon = "on";
                    sound.setBackgroundResource(R.drawable.on);
                    soundonoff = 0;
                }
            }
        });



        play.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {



                Cursor cursor = db.alldata();

                if (cursor.getCount() != 0) {

                    while (cursor.moveToNext()) {
                        //Toast.makeText(getApplicationContext(), "First Name:"+cursor.getString(0), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Last Name:"+cursor.getString(1), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Department:"+cursor.getString(2), Toast.LENGTH_SHORT).show();

                        level = cursor.getString(0);

                        collected = cursor.getString(1);

                        //Toast.makeText(getApplicationContext(), "level "+level+" coll "+ collected+" score " +score, Toast.LENGTH_SHORT).show();

                    }
                }

                Intent call = new Intent(getApplicationContext(), MainActivity.class);
                call.putExtra("sound", soundon);
                call.putExtra("vibration", vibrationon);
                call.putExtra("level", level);
                //call.putExtra("collected", collected);
                startActivity(call);



            }
        });

    }

}
