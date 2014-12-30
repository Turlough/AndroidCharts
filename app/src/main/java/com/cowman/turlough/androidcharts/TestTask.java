package com.cowman.turlough.androidcharts;

import android.os.AsyncTask;

/**
 * Created by turlough on 30/12/14.
 */
public class TestTask extends AsyncTask<Double, Void, IPlotter> {
    IStats stats;
    IPlotter plotter;
    MultiTestTask parent;

    public TestTask(MultiTestTask parent, IStats stats, IPlotter plotter) {

        this.parent = parent;
        this.stats = stats;
        this.plotter = plotter;
    }

    @Override
    protected IPlotter doInBackground(Double... doubles) {

        for (Double d : doubles) {
            stats.add(d);
            plotter.add(d);
        }

        return plotter;

    }

    @Override
    protected void onPostExecute(IPlotter p) {
        parent.subTaskCompleted(p);
    }
}
