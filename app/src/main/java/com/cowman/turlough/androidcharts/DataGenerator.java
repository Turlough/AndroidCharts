package com.cowman.turlough.androidcharts;

import java.util.Random;

/**
 * Created by turlough on 30/12/14.
 */
public class DataGenerator {
    private int mean = 0;
    private int sd = 0;
    private Random random;

    public DataGenerator(int mean, int sd) {
        this.mean = mean;
        this.sd = sd;
        random = new Random();
    }

    public double nextGaussian(){
        return mean + ( random.nextGaussian() * sd);
    }
}
