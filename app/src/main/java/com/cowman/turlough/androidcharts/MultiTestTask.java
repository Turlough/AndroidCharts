package com.cowman.turlough.androidcharts;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by turlough on 30/12/14.
 */
public class MultiTestTask extends AsyncTask<TestTask, IPlotter, Void> {

    DataGenerator generator = new DataGenerator(174, 33);
    Activity parent;

    public MultiTestTask(Activity parent) {
        this.parent = parent;
    }

    @Override
    protected Void doInBackground(TestTask... testTasks) {

        for (TestTask testTask : testTasks) {

            testTask.execute(generator.nextValue());

        }

        return null;
    }

    public void subTaskCompleted(IPlotter plotter){
        //Log.i(getClass().getName(), "SubTaskCompleted");
        publishProgress(plotter);
    }

    @Override
    protected void onProgressUpdate(IPlotter... values) {

        super.onProgressUpdate(values[0]);
        final IPlotter plotter = values[0];

        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                plotter.refresh();
            }
        });


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    private void sleep (int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
