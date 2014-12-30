package com.cowman.turlough.androidcharts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class StatsView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Plotter plotter = new Plotter();;

    public StatsView(Context context) {
        super(context);
        init();

    }

    public StatsView(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatsView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas(null);
                update(canvas);
                holder.unlockCanvasAndPost(canvas);
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

    public void add(double data){

        Canvas canvas = surfaceHolder.lockCanvas(null);
        plotter.add(data, canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }
    protected void update(Canvas canvas) {
        plotter.plotData(canvas);
    }
}