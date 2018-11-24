package com.example.mukesh.androidclock;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClockActivity extends AppCompatActivity {
    private MySurfaceView mySurfaceView;
    private int colorHands;
    private int colorMarks;
    private int colorBackground;
    private int colorNumbers;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getApplicationContext().getSharedPreferences("ClockColors", Context.MODE_PRIVATE);
        //if preferences found, then set preference color else set the standard colors for the respective element
        colorHands = preferences.getInt("hands",0) == 0 ? Color.BLACK : preferences.getInt("hands",0);
        colorMarks = preferences.getInt("marks",0) == 0 ? Color.BLACK : preferences.getInt("marks",0);
        colorBackground = preferences.getInt("background",0) == 0 ? Color.WHITE : preferences.getInt("background",0);
        colorNumbers = preferences.getInt("numbers",0) == 0 ? Color.BLACK : preferences.getInt("numbers",0);

        mySurfaceView = new MySurfaceView(this,400, colorHands,colorMarks,colorBackground,colorNumbers);
                //context,length,colorHands,colorMarks,colorBackground,colorNumbers
        setContentView(mySurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.onPauseSurfaceView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.onResumeSurfaceView();
    }
}
