package com.cowman.turlough.androidcharts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turlough on 30/12/14.
 */
public class Plotter {

    private List<Double>  values;
    private int yScale = 50;
    int padding =5;
    Paint dataPaint;
    Paint linesPaint;
    Paint statsPaint;
    int maxValue = 10;
    int leftOffset = 150;
    int bottomOffset = 50;
    int topOffset = 50;
    int rightOffset = 50;


    public Plotter(){
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

    public void plotData(Canvas c){

        if(values.size() < 1) return;


        for (int i = 0; i < values.size(); i++) {
            drawBar(i, c);
        }
    }

    private void drawBar(int i,  Canvas c){

        int w = (c.getWidth() - leftOffset - rightOffset) / (values.size());
        int base = c.getHeight() - bottomOffset;
        double top = base - (values.get(i) * yScale);


        c.drawCircle(
                leftOffset + (i * w ),
                (int) top,
                2,
                dataPaint
        );

    }

    public void clear(Canvas c){

        c.drawColor(Color.BLACK);
    }

    public void add(double data, Canvas c){

        values.add(data);
        maxValue = Math.max((int)Math.round(data) + 1, maxValue);
        yScale = (c.getHeight() - topOffset - bottomOffset)/(maxValue);

        clear(c);
        drawStats(c);
        drawLines(c);
        plotData(c);
    }

    public void drawLines(Canvas c){

        int base = c.getHeight() - bottomOffset;
        int step = Math.max(1, maxValue / 10 );

        for (int i = 0; i <= maxValue ; i += step) {

            int top = base - (i * yScale);

            c.drawText(
                    Integer.toString(i),
                    (float)(leftOffset - 50),
                    (float)(top),
                    linesPaint
            );

            c.drawLine(
                    leftOffset, //startx
                    top,//starty
                    c.getWidth() - rightOffset, //stopx
                    top, //stopy
                    linesPaint
            );
        }
    }

    public void drawStats(Canvas c){


        SimpleStats stats = new SimpleStats(values);
        double mean = stats.getMean();
        int sd = (int)Math.round(stats.getStandardDeviation());
        int base = c.getHeight() - bottomOffset;
        int top = base - ((int)Math.round(mean) * yScale);

        statsPaint.setAlpha(255);

        c.drawText(
                "Mean " + (int) Math.round(mean),
                (float) (leftOffset - 130),
                (float) (top),
                statsPaint
        );

        c.drawText(
                "SD " + sd,
                (float)(leftOffset - 130),
                (float)(top + sd),
                statsPaint
        );

        c.drawText(
                "Num Samples " + values.size(),
                (float)(leftOffset - 130),
                (float)(c.getHeight() - 20),
                statsPaint
        );

        c.drawLine(
                leftOffset, //startx
                top,//starty
                c.getWidth() - rightOffset, //stopx
                top, //stopy
                statsPaint
        );

        statsPaint.setAlpha(50);

        c.drawRect(
                leftOffset,//left
                top + sd ,//top
                c.getWidth() - rightOffset,//right
                top - sd,//bottom
                statsPaint
        );

        c.drawRect(
                leftOffset,//left
                top + sd * 2,//top
                c.getWidth() - rightOffset,//right
                top - sd * 2,//bottom
                statsPaint
        );

        c.drawRect(
                leftOffset,//left
                top + sd * 3,//top
                c.getWidth() - rightOffset,//right
                top - sd * 3,//bottom
                statsPaint
        );

    }

}
