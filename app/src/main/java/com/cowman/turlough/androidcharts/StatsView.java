package com.cowman.turlough.androidcharts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.graphics.BitmapFactory.*;

/**
 * Created by turlough on 14/12/14.
 */
public class StatsView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap bitmap ;
    private MyThread thread;
    private int x=20,y=20;
    int width,height;


    public StatsView(Context context, int w, int h) {
        super(context);

        width=w;
        height=h;
        thread=new MyThread(getHolder(),this);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        thread.startrun(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bitmap = decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawColor(Color.BLUE);//To make background
        canvas.drawBitmap(bitmap,x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);


        Paint paintShape = new Paint();
        paintShape.setColor(Color.BLACK);
        paintShape.setStyle(Paint.Style.STROKE);

        Rect myRectangle = new Rect();
        myRectangle.set(0, 100, canvas.getWidth()/4, canvas.getHeight()/4);

        canvas.drawRect(myRectangle, paintShape);


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        thread.startrun(false);
        thread.stop();
    }
    public class MyThread extends Thread {

        private SurfaceHolder holder;
        private StatsView view;
        private boolean mrun = false;

        public MyThread(SurfaceHolder holder, StatsView view) {

            this.holder = holder;
            this.view = view;
        }

        public void startrun(boolean run) {

            mrun = run;
        }

        @SuppressLint("WrongCall")
        @Override
        public void run() {

            super.run();
            Canvas canvas;
            while (mrun) {
                canvas = null;
                try {
                    canvas = holder.lockCanvas(null);
                    synchronized (holder) {
                        view.onDraw(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

    }

}
