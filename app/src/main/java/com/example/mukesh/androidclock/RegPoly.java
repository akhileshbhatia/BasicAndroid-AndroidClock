package com.example.mukesh.androidclock;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RegPoly {
    private float x0, y0, r;
    private int n;
    private float x[], y[];

    private Canvas canvas;
    private Paint paint;

    public RegPoly(int n, float r, float x0, float y0, Canvas canvas, Paint paint) {
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
        this.n = n;
        this.canvas = canvas;
        this.paint = paint;

        x = new float[n];
        y = new float[n];

        for (int i = 0; i < n; i++) {
            x[i] = (float) (x0 + r * Math.cos(2 * Math.PI* i / n));
            y[i] = (float) (y0 + r * Math.sin(2 * Math.PI* i / n));
        }
    }

    public float getX(int i) {
        return x[i % n];
    }

    public float getY(int i) {
        return y[i % n];
    }

    public void drawRegPoly() {
        for (int i = 0; i < n; i++) {
            canvas.drawLine(x[i], y[i], x[(i + 1) % n], y[(i + 1) % n], paint);
        }
    }

    public void drawPoints() {
        for (int i = 0; i < n; i++) {
            canvas.drawCircle(x[i], y[i], 4, paint);
        }
    }

    public void drawNumbers(){
        int number;
        for(int i =0; i<12; i++){
            number =  i< 10 ? (i+3) : (i-9);
            canvas.drawText(String.valueOf(number),x[i],y[i],paint);
        }
    }

    public void drawRadius(int i) {
        canvas.drawLine(x0, y0, x[i % n], y[i % n], paint);
    }


}
