package com.cowman.turlough.androidcharts;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements StatsFragment.OnFragmentInteractionListener {

    StatsFragment fragment;
    Button btnStart;
    DataGenerator generator = new DataGenerator(200, 30);
    TestTask test;
    IStats stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            fragment =StatsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.stats_fragment, fragment)
                    .commit();

        }

        stats = new SimpleStats(new ArrayList<Double>());


        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int numTests = 300;
                TestTask [] tasks = new TestTask[numTests];
                MultiTestTask runner = new MultiTestTask(MainActivity.this);
                for (int i = 0; i < numTests; i++) {
                    tasks [i] = new TestTask(runner, stats, fragment);
                }
                runner.execute(tasks);

            }
        });

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
