package com.cowman.turlough.androidcharts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends ActionBarActivity implements StatsFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Fragment f =StatsFragment.newInstance(null, null);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.stats_fragment, f)
                    .commit();
        }

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
