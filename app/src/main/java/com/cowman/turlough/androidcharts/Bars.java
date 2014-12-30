package com.cowman.turlough.androidcharts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turlough on 30/12/14.
 */
public class Bars {

    private List<Integer>  values;
    private int yScale = 50;
    int padding =5;
    Paint barPaint;
    Paint linesPaint;
    int maxValue = 10;
    int leftOffset = 100;
    int bottomOffset = 50;
    int topOffset = 50;
    int rightOffset = 50;




    public Bars(){
        values = new ArrayList<Integer>();

        barPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setColor(Color.argb(100, 255, 255, 100));
        barPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //barPaint.setStrokeWidth(5f);

        linesPaint = new Paint();
        linesPaint.setAntiAlias(true);
        linesPaint.setColor(Color.argb(200, 200, 200, 0));
        linesPaint.setStyle(Paint.Style.STROKE);
        linesPaint.setStrokeWidth(1f);


    }

    public void drawOn(Canvas c){

        c.drawColor(Color.BLACK);

        if(values.size() < 1) return;


        for (int i = 0; i < values.size(); i++) {
            drawBar(i, c);
        }
    }

    private void drawBar(int i,  Canvas c){

        int w = (c.getWidth() - leftOffset - rightOffset) / (values.size() +2);
        int base = c.getHeight() - bottomOffset;
        int top = base - (values.get(i) * yScale);

//        c.drawRect(
//                leftOffset + (i * w + padding),//left
//                top ,//top
//                (i + 1) * w ,//right
//                base,//bottom
//                barPaint
//        );

        c.drawCircle(
                leftOffset + (i * w ),
                top,
                5,
                barPaint
        );

        c.drawText(
                Integer.toString(values.get(i)),
                (float)(leftOffset + (i * w )),
                (float)(top - 10),
                barPaint
        );
    }

    public void add(int data, Canvas c){

        values.add(data);
        maxValue = Math.max(data +1, maxValue);
        yScale = (c.getHeight() - topOffset - bottomOffset)/(maxValue);

        drawOn(c);
        drawLines(c);
    }

    public void drawLines(Canvas c){

        int base = c.getHeight() - bottomOffset;
        int step = Math.max(1, maxValue / 10 );

        for (int i = 0; i <= maxValue; i += step) {

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



}
