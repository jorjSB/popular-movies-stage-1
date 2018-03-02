package com.udacity.georgebalasca.popularmoviesstage_1;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.udacity.georgebalasca.popularmoviesstage_1.arrayadapters.MoviesListArrayAdapter;
import com.udacity.georgebalasca.popularmoviesstage_1.models.Movie;
import com.udacity.georgebalasca.popularmoviesstage_1.utils.NetUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.udacity.georgebalasca.popularmoviesstage_1.utils.JsonUtils.getMoviesArray;

public class MainActivity extends AppCompatActivity {

    TextView noInternetTV;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noInternetTV = findViewById(R.id.no_internet);
        gridView = findViewById(R.id.movies_grid);
        }

    @Override
    protected void onResume() {
        super.onResume();

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
        if(NetUtils.isOnline(this))
            new AsyncFetchData().execute(initialMoviesListURL);
        else
            noInternetTV.setVisibility(View.VISIBLE);

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

            ArrayList<Movie> moviesArray = getMoviesArray(getResources().getString(R.string.api_key_v3), data);
            if (data != null) {
                MoviesListArrayAdapter adapter = new MoviesListArrayAdapter(getApplicationContext(),
                        moviesArray );
                // attach the adapter to the GridView
                if(adapter!= null)
                    gridView.setAdapter(adapter);
            }else
                // TODO: remove or add to strings
                Log.i("Error fetching data", "Please try again");

        }
    }
}
