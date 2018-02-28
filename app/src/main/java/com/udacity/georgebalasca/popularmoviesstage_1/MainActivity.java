package com.udacity.georgebalasca.popularmoviesstage_1;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.udacity.georgebalasca.popularmoviesstage_1.models.Movie;
import com.udacity.georgebalasca.popularmoviesstage_1.utils.NetUtils;

import java.io.IOException;
import java.net.URL;

import static com.udacity.georgebalasca.popularmoviesstage_1.utils.JsonUtils.getMoviesArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMoviesData();
    }

    /**
     * Creates the initial url and loads the movie data
     *
     * TODO: clear the unnecessary code
     */
    private void loadMoviesData() {
        // Build my URL for fetching data based on my private key, and the type of data needed!
        URL initialMoviesListURL =  NetUtils.getMoviesListSortedUrl(getResources().getString(R.string.api_key_v3), NetUtils.SORT_BY_POPULAR);
        // NetUtils.getMoviePosterURL(getResources().getString(R.string.api_key_v3), "kqjL17yufvn9OVLyXYpvtyrFfak.jpg");

        // fetch result
        new AsyncFetchData().execute(initialMoviesListURL);


    }

    /**
     * AsyncTask to fetch data from the internet (avoid networking in main tread) add inflate the views
     */
    public class AsyncFetchData extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            try {
                // use netUtils to fetch data from url param provided
                String response = NetUtils.getResponseFromHttpUrl(params[0]);

                return response;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {

            if (data != null) {
                for (Movie movie:
                        getMoviesArray(getResources().getString(R.string.api_key_v3), data) ) {
                    Log.i("Title", movie.getTitle());
                    Log.i("Poster", movie.getPosterURL().toString());
                }

                // TODO: remove or add to strings
                Log.i("Result", data);

            }else
                // TODO: remove or add to strings
                Log.i("Error fetching data", "Please try again");

        }
    }
}
