package com.cowman.turlough.androidcharts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turlough on 30/12/14.
 * Does not use rolling average, recalculates all values
 * in constructor and in add()
 */
public class SimpleStats implements IStats {
    List<Double> values = new ArrayList<Double>();
    double mean;
    double variance;
    double sd;

    public SimpleStats(List<Double> values) {
        this.values = values;
        update();
    }

    private void update(){
        setMean();
        setVariance();
        setStandardDeviation();
    }

    @Override
    public double getMean(){return mean;}

    @Override
    public double getVariance(){return variance;}

    @Override
    public double getStandardDeviation(){return sd;}

    public void setMean(){
        if (values.size() <1) return ;
        double total = 0;

        for (Double value : values) {
            total += value;
        }
        mean = total/values.size();
    }


    public void setVariance(){
        if (values.size() < 2) return ;
        double total = 0;
        double mean = getMean();

        for (Double value : values) {
            total += Math.pow(value - mean, 2);
        }
        variance = total/(values.size() - 1);
    }

    public void setStandardDeviation(){
        sd = Math.sqrt(getVariance());
    }

    @Override
    public void add(Double data){
        values.add(data);
        update();
    }
}
