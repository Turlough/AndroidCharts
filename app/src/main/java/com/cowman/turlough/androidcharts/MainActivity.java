package com.cowman.turlough.androidcharts;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements StatsFragment.OnFragmentInteractionListener {

    StatsFragment fragment;
    Button btnStart;
    DataGenerator generator = new DataGenerator(200, 30);

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

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 300; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double data = generator.nextGaussian();
                    fragment.add(data);
                }

            }
        });

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
