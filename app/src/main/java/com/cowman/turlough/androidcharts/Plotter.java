package com.cowman.turlough.androidcharts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turlough on 30/12/14.
 */
public class Plotter{

    private List<Double>  values;
    private int yScale = 50;
    Paint dataPaint;
    Paint linesPaint;
    Paint statsPaint;
    int maxValue = 10;
    int leftOffset = 150;
    int bottomOffset = 50;
    int topOffset = 50;
    int rightOffset = 50;
    IStats stats;


    public Plotter(IStats stats){

        this.stats = stats;
        values = new ArrayList<Double>();

        dataPaint = new Paint();
        dataPaint.setAntiAlias(true);
        dataPaint.setColor(Color.argb(255, 255, 255, 50));
        dataPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //dataPaint.setStrokeWidth(5f);

        linesPaint = new Paint();
        linesPaint.setAntiAlias(true);
        linesPaint.setColor(Color.argb(200, 200, 200, 0));
        linesPaint.setStyle(Paint.Style.STROKE);
        linesPaint.setStrokeWidth(1f);

        statsPaint = new Paint();
        statsPaint.setAntiAlias(true);
        statsPaint.setColor(Color.argb(255, 0, 200, 255));
        statsPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        statsPaint.setStrokeWidth(1f);


    }

    public void refresh(Canvas canvas){

        if(values.size() < 1) return;

        yScale = (canvas.getHeight() - topOffset - bottomOffset)/(maxValue);

        clear(canvas);
        drawStats(canvas, stats.getMean(), stats.getStandardDeviation());
        drawGrid(canvas);

        for (int i = 0; i < values.size(); i++) {
            plot(canvas, i);
        }

    }

    private void plot(Canvas canvas, int index){

        int w = (canvas.getWidth() - leftOffset - rightOffset) / (values.size());
        int base = canvas.getHeight() - bottomOffset;
        double top = base - (values.get(index) * yScale);


        canvas.drawCircle(
                leftOffset + (index * w),
                (int) top,
                2,
                dataPaint
        );

    }

    private void clear(Canvas canvas){

        canvas.drawColor(Color.BLACK);
    }

    public synchronized void add(double data){

        values.add(data);
        stats.add(data);
        maxValue = Math.max((int)Math.round(data) + 1, maxValue);


    }

    public void drawGrid(Canvas canvas){

        int base = canvas.getHeight() - bottomOffset;
        int step = Math.max(1, maxValue / 10 );

        for (int i = 0; i <= maxValue ; i += step) {

            int top = base - (i * yScale);

            canvas.drawText(
                    Integer.toString(i),
                    (float) (leftOffset - 50),
                    (float) (top),
                    linesPaint
            );

            canvas.drawLine(
                    leftOffset, //startx
                    top,//starty
                    canvas.getWidth() - rightOffset, //stopx
                    top, //stopy
                    linesPaint
            );
        }
    }

    public void drawStats(Canvas canvas, double mean, double deviation){

        int base = canvas.getHeight() - bottomOffset;
        int centre = base - ((int)Math.round(mean) * yScale);
        int sd = (int)Math.round(deviation);

        statsPaint.setAlpha(255);

        canvas.drawText(
                "Mean " + (int) Math.round(mean),
                (float) (leftOffset - 130),
                (float) (centre),
                statsPaint
        );

        canvas.drawText(
                "SD " + sd,
                (float) (leftOffset - 130),
                (float) (centre + sd),
                statsPaint
        );

        canvas.drawText(
                "Num Samples " + values.size(),
                (float) (leftOffset - 130),
                (float) (canvas.getHeight() - 20),
                statsPaint
        );
        //mean line
        canvas.drawLine(
                leftOffset, //startx
                centre,//starty
                canvas.getWidth() - rightOffset, //stopx
                centre, //stopy
                statsPaint
        );

        //stats rectangles
        statsPaint.setAlpha(50);

        drawSdRectangle(canvas, centre, sd, 1);
        drawSdRectangle(canvas, centre, sd, 2);
        drawSdRectangle(canvas, centre, sd, 3);

    }

    private void drawSdRectangle(Canvas canvas, int mean, int sd, float tolerance) {
        canvas.drawRect(
                leftOffset,//left
                mean + sd * tolerance,//top
                canvas.getWidth() - rightOffset,//right
                mean - sd * tolerance,//bottom
                statsPaint
        );
    }

}
