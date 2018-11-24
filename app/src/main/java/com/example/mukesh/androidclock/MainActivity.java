package com.example.mukesh.androidclock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private Button btnViewClock;
    private Button btnSetPreferences;
    private Button btnSetAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnSetPreferences =findViewById(R.id.btnSetPreferences);
        btnViewClock = findViewById(R.id.buttonViewClock);

        btnViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewClockIntent = new Intent(getApplicationContext(),ClockActivity.class);
                startActivity(viewClockIntent);
            }
        });

        btnSetPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setPrefIntent = new Intent(getApplicationContext(),PreferencesActivity.class);
                startActivity(setPrefIntent);
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setAlarmIntent = new Intent(getApplicationContext(),AlarmActivity.class);
                startActivity(setAlarmIntent);
            }
        });

    }


}
