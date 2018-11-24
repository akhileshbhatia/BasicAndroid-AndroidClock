package com.example.mukesh.androidclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class MySurfaceView extends SurfaceView implements Runnable {
    private Thread thread = null;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;
    private float length;
    private Context context;
    private int width;
    private int height;
    private Canvas canvas;
    private Paint secHandPaint;
    private Paint minAndHourHandPaint;
    private Paint clockMarksPaint;
    private Paint numbersPaint;
    private RegPoly secMarks;
    private RegPoly hourMarks;
    private RegPoly hourHand;
    private RegPoly minHand;
    private RegPoly secHand;
    private RegPoly clockNumbers;
    private int colorHands;
    private int colorBackground;
    private int colorNumbers;
    private int colorMarks;

    public MySurfaceView(Context context, float length,
                         int colorHands, int colorMarks, int colorBackground, int colorNumbers) {
        super(context);
        this.context = context;
        this.length = length;
        surfaceHolder = getHolder();
        this.colorHands = colorHands;
        this.colorMarks = colorMarks;
        this.colorBackground = colorBackground;
        this.colorNumbers = colorNumbers;
    }

    public void onResumeSurfaceView() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPauseSurfaceView() {
        boolean retry = true;
        running = false;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void initClockParameters(){
        //canvas (background)
        canvas.drawColor(colorBackground);

        //sec hand
        secHandPaint = new Paint();
        secHandPaint.setColor(colorHands);
        secHandPaint.setStrokeWidth(8);

        //min and hour hand
        minAndHourHandPaint = new Paint();
        minAndHourHandPaint.setColor(colorHands);
        minAndHourHandPaint.setStrokeWidth(12);

        //sec marks
        clockMarksPaint = new Paint();
        clockMarksPaint.setColor(colorMarks);
        clockMarksPaint.setStrokeWidth(3);

        //numbers
        numbersPaint = new Paint();
        numbersPaint.setColor(colorNumbers);
        numbersPaint.setStrokeWidth(3);
        numbersPaint.setTextSize(50);

        width = getWidth()/2;
        height = getHeight()/2;

    }

    @Override
    public void run() {
        int hour = 0, min = 0, sec = 0;
        while (running) {
            if (surfaceHolder.getSurface().isValid()) {
                canvas = surfaceHolder.lockCanvas();

                initClockParameters();

                secMarks = new RegPoly(60, length, width, height, canvas, clockMarksPaint);
                hourMarks = new RegPoly(12, length - 20, width, height, canvas, clockMarksPaint);
                hourHand = new RegPoly(60, length - 100, width, height, canvas, minAndHourHandPaint);
                minHand = new RegPoly(60, length - 50, width, height, canvas, minAndHourHandPaint);
                secHand = new RegPoly(60, length - 30, width, height, canvas, secHandPaint);
                clockNumbers = new RegPoly(12,length+50,width,height,canvas,numbersPaint);

                //draw
                secMarks.drawPoints();
                hourMarks.drawPoints();
                clockNumbers.drawNumbers();
               // canvas.drawText("12",);

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //get calendar
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR);
                min = calendar.get(Calendar.MINUTE);
                sec = calendar.get(Calendar.SECOND);

                //drawing the hands
                secHand.drawRadius(sec+45);
                minHand.drawRadius(min+45);
                hourHand.drawRadius((hour*5) + (min/12) + 45);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
