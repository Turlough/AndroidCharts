package com.cowman.turlough.androidcharts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class StatsView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Plotter plotter;
    private IStats stats;
    Context context;

    public StatsView(Context context) {
        super(context);

        init(context);

    }

    public StatsView(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatsView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        surfaceHolder = getHolder();
        this.context = context;
        stats = new SimpleStats(new ArrayList<Double>());
        plotter = new Plotter(stats);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                surfaceHolder = holder;
                refresh();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub

            }
        });
    }

    public synchronized void add(double data){
        plotter.add(data);

    }
    protected synchronized void refresh() {
        Canvas canvas = surfaceHolder.lockCanvas(null);
        plotter.refresh(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
        //Log.i(getClass().getName(),"Canvas refreshed");

    }
}