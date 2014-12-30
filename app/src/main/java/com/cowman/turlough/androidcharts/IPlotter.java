package com.cowman.turlough.androidcharts;

import android.graphics.Canvas;

/**
 * Created by turlough on 30/12/14.
 */
public interface IPlotter {
    void add(double data);
    void refresh();
}
