package com.cowman.turlough.androidcharts;

/**
 * Created by turlough on 30/12/14.
 */
public interface IStats {
    double getMean();

    double getVariance();

    double getStandardDeviation();

    void add(Double data);
}
