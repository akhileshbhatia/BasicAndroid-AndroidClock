package com.example.mukesh.androidclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ClockActivity extends AppCompatActivity {
    private MySurfaceView mySurfaceView;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams layoutParamsViewPref;
    private LinearLayout.LayoutParams layoutParamsSetAlarm;
    private Button viewPrefBtn;
    private Button setAlarmBtn;
    private int colorHands;
    private int colorMarks;
    private int colorBackground;
    private int colorNumbers;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeClockColors();

        mySurfaceView = new MySurfaceView(this,400, colorHands,colorMarks,colorBackground,colorNumbers);
        frameLayout = new FrameLayout(this);
        linearLayout = new LinearLayout(this);

        initializeViewPrefBtn();
        initializeSetAlarmBtn();

        linearLayout.addView(viewPrefBtn,layoutParamsViewPref);
        linearLayout.addView(setAlarmBtn,layoutParamsSetAlarm);
        frameLayout.addView(mySurfaceView);
        frameLayout.addView(linearLayout);

        setContentView(frameLayout);

        viewPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setPrefIntent = new Intent(getApplicationContext(),PreferencesActivity.class);
                startActivity(setPrefIntent);
            }
        });

        setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setPrefIntent = new Intent(getApplicationContext(),AlarmActivity.class);
                startActivity(setPrefIntent);
            }
        });
    }
    private void initializeClockColors(){
        preferences = getApplicationContext().getSharedPreferences("ClockColors", Context.MODE_PRIVATE);
        //if preferences found, then set preference color else set the standard colors for the respective element
        colorHands = preferences.getInt("hands",0) == 0 ? Color.BLACK : preferences.getInt("hands",0);
        colorMarks = preferences.getInt("marks",0) == 0 ? Color.BLACK : preferences.getInt("marks",0);
        colorBackground = preferences.getInt("background",0) == 0 ? Color.WHITE : preferences.getInt("background",0);
        colorNumbers = preferences.getInt("numbers",0) == 0 ? Color.BLACK : preferences.getInt("numbers",0);
    }

    private void initializeViewPrefBtn(){
        layoutParamsViewPref = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsViewPref.setMargins(75,1200,10,10);

        viewPrefBtn = new Button(getApplicationContext());
        viewPrefBtn.setPadding(25,25,25,25);
        viewPrefBtn.setBackgroundColor(Color.WHITE);
        viewPrefBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.buttonstyle));
        viewPrefBtn.setText("Update Preferences");

    }

    private void initializeSetAlarmBtn(){
        layoutParamsSetAlarm = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsSetAlarm.setMargins(175,1200,10,10);
        setAlarmBtn = new Button(getApplicationContext());
        setAlarmBtn.setPadding(25,25,25,25);
        setAlarmBtn.setBackgroundColor(Color.WHITE);
        setAlarmBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.buttonstyle));
        setAlarmBtn.setText("Set Alarm");
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
