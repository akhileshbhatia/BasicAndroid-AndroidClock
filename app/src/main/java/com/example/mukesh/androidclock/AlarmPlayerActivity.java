package com.example.mukesh.androidclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmPlayerActivity extends AppCompatActivity {

    private Button btnStopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_player);


        final MediaPlayer player = MediaPlayer.create(this,R.raw.alarmsound);
        player.start();
        player.setLooping(true);
        btnStopAlarm = findViewById(R.id.btnStopAlarm);
        btnStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
