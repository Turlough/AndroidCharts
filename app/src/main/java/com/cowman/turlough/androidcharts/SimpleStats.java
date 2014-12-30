package com.cowman.turlough.androidcharts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turlough on 30/12/14.
 */
public class SimpleStats {
    List<Double> values = new ArrayList<Double>();

    public SimpleStats(List<Double> values) {
        this.values = values;
    }

    public double getMean(){
        if (values.size() <1) return 0;
        double total = 0;

        for (Double value : values) {
            total += value;
        }
        return total/values.size();
    }

    public double getVariance(){
        if (values.size() < 2) return 0;
        double total = 0;
        double mean = getMean();

        for (Double value : values) {
            total += Math.pow(value - mean, 2);
        }
        return total/(values.size() - 1);
    }
    public double getStandardDeviation(){
        return Math.sqrt(getVariance());
    }

    public void add(Double data){
        values.add(data);
    }
}
