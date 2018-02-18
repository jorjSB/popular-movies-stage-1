package com.udacity.georgebalasca.popularmoviesstage_1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.georgebalasca.popularmoviesstage_1.utils.NetUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Use asynk task w or without a loader to load data from the internet!


        // Build my URL for fetching data based on my private key, and the type of data needed!
        NetUtils.getMoviesListSortedUrl(getResources().getString(R.string.api_key_v3), NetUtils.SORT_BY_POPULAR);

        NetUtils.getMoviePoster(getResources().getString(R.string.api_key_v3), "kqjL17yufvn9OVLyXYpvtyrFfak.jpg");

    }



}
