package com.cowman.turlough.androidcharts;

import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by turlough on 30/12/14.
 */
public class MultiTestTask extends AsyncTask<TestTask, IPlotter, Void> {

    DataGenerator generator = new DataGenerator(174, 33);

    @Override
    protected Void doInBackground(TestTask... testTasks) {

        for (TestTask testTask : testTasks) {

            testTask.execute(generator.nextValue());

        }

        return null;
    }

    public void subTaskCompleted(IPlotter plotter){
        //sleep(100);
        publishProgress(plotter);
    }

    @Override
    protected void onProgressUpdate(IPlotter... values) {
        super.onProgressUpdate(values[0]);
        IPlotter p = values[0];
        p.refresh();

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
