package com.example.mukesh.androidclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    private Button btnSaveAlarm;
    private TimePicker timePicker;
    private Calendar currentDateTime;
    private Calendar timePickerDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        currentDateTime = Calendar.getInstance();
        timePickerDateTime = Calendar.getInstance();

        btnSaveAlarm = findViewById(R.id.btnSaveAlarm);

        btnSaveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedHour = timePicker.getCurrentHour();
                int selectedMin = timePicker.getCurrentMinute();
                String msg = "Alarm set for "+selectedHour+" : "+selectedMin;

                timePickerDateTime.set(Calendar.HOUR_OF_DAY,selectedHour);
                timePickerDateTime.set(Calendar.MINUTE,selectedMin);

                if(currentDateTime.getTimeInMillis() > timePickerDateTime.getTimeInMillis()){
                    timePickerDateTime.add(Calendar.DATE,1);
                    msg+=" tomorrow";
                }
                else {
                    msg += " today";
                }

                timePickerDateTime.set(Calendar.SECOND,0);
                timePickerDateTime.set(Calendar.MILLISECOND,0);

                Intent alarmIntent = new Intent(getApplicationContext(),AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast
                        (getApplicationContext(),0510,alarmIntent,0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,timePickerDateTime.getTimeInMillis(),pendingIntent);

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }


}