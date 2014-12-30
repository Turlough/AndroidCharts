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
    int maxValue = 10;
    int leftOffset = 50;
    int bottomOffset = 50;
    int topOffset = 50;
    int rightOffset = 50;




    public Bars(){
        values = new ArrayList<Integer>();

        barPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setColor(Color.argb(100, 255, 255, 100));
        barPaint.setStyle(Paint.Style.STROKE);

    }

    public void drawOn(Canvas c){

        c.drawColor(Color.BLACK);

        if(values.size() < 1) return;


        for (int i = 0; i < values.size(); i++) {
            drawBar(i, c);
        }
    }

    private void drawBar(int i,  Canvas c){

        int w = (c.getWidth() - leftOffset - rightOffset) / values.size();

        c.drawRect(
                leftOffset + (i * w + padding),//left
                (values.get(i) * yScale) ,//top
                (i + 1) * w ,//right
                c.getHeight() - bottomOffset,//bottom
                barPaint
        );
    }

    public void add(int data, Canvas c){
        values.add(data);
        maxValue = Math.max(data, maxValue);
        yScale = (c.getHeight() - topOffset - bottomOffset)/(maxValue);

        drawOn(c);
    }

}
