package com.example.mukesh.androidclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {
    //context,length,colorHands,colorMarks,colorBackground,colorNumbers
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private Spinner handsColorSpinner;
    private Spinner marksColorSpinner;
    private Spinner backgroundColorSpinner;
    private Spinner numbersColorSpinner;
    private final int[] colorValues = {Color.BLACK,Color.BLUE,Color.RED,Color.WHITE};
    private final ArrayList<Integer> colorValuesList = new ArrayList<>();
    private final String[] colorNames = {"Black","Blue","Red","White"};
    private ArrayAdapter<String> handsColorAdapter;
    private ArrayAdapter<String> marksColorAdapter;
    private ArrayAdapter<String> backgroundColorAdapter;
    private ArrayAdapter<String> numbersColorAdapter;
    private int[] position = {0,0,0,0};
    private Button btnPref;
    private Button btnSaveAndViewClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setupMenu();
        preferences = getApplicationContext().getSharedPreferences("ClockColors", Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        btnPref = findViewById(R.id.btnSavePref);
        btnSaveAndViewClock = findViewById(R.id.btnSaveAndGoToClock);

        for(int i: colorValues){
            colorValuesList.add(i);
        }

        handsColorSpinner = findViewById(R.id.spinnerHandsColor);
        handsColorAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,colorNames);
        handsColorSpinner.setAdapter(handsColorAdapter);
        int lastColor = preferences.getInt("hands",0) == 0 ? Color.BLACK : preferences.getInt("hands",0);
        handsColorSpinner.setSelection(colorValuesList.indexOf(lastColor));

        marksColorSpinner = findViewById(R.id.spinnerMarksColor);
        marksColorAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,colorNames);
        marksColorSpinner.setAdapter(marksColorAdapter);
        lastColor = preferences.getInt("marks",0) == 0 ? Color.BLACK : preferences.getInt("marks",0);
        marksColorSpinner.setSelection(colorValuesList.indexOf(lastColor));

        backgroundColorSpinner = findViewById(R.id.spinnerBackgroundColor);
        backgroundColorAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,colorNames);
        backgroundColorSpinner.setAdapter(backgroundColorAdapter);
        lastColor = preferences.getInt("background",0) == 0 ? Color.WHITE : preferences.getInt("background",0);
        backgroundColorSpinner.setSelection(colorValuesList.indexOf(lastColor));

        numbersColorSpinner = findViewById(R.id.spinnerNumColor);
        numbersColorAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,colorNames);
        numbersColorSpinner.setAdapter(numbersColorAdapter);
        lastColor = preferences.getInt("numbers",0) == 0 ? Color.BLACK : preferences.getInt("numbers",0);
        numbersColorSpinner.setSelection(colorValuesList.indexOf(lastColor));

        handsColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position[0] = colorValues[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //position[0] = colorValues[0];

            }
        });

        marksColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position[1] = colorValues[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //position[1] = colorValues[0];

            }
        });

        backgroundColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position[2] = colorValues[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //position[2] =colorValues[0];
            }
        });

        numbersColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position[3] = colorValues[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //position[3] = colorValues[0];
            }
        });

        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavePref();
                Toast.makeText(getApplicationContext(),"Preferences saved successfully",Toast.LENGTH_LONG).show();
            }
        });

        btnSaveAndViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavePref();
                Toast.makeText(getApplicationContext(),"Preferences saved. Redirecting now to the clock",Toast.LENGTH_LONG).show();
                final Intent gotToClockIntent = new Intent(getApplicationContext(),ClockActivity.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(gotToClockIntent);
                    }
                },2000);
            }
        });

    }

    private void SavePref(){
        preferencesEditor.putInt("hands",position[0]);
        preferencesEditor.putInt("marks",position[1]);
        preferencesEditor.putInt("background",position[2]);
        preferencesEditor.putInt("numbers",position[3]) ;
        preferencesEditor.commit();
    }

    private void setupMenu(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Android Clock");
        actionBar.setSubtitle("Update Preferences");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(),ClockActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
