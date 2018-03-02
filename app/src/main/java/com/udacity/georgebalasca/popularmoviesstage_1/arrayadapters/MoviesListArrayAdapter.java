package com.udacity.georgebalasca.popularmoviesstage_1.arrayadapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.georgebalasca.popularmoviesstage_1.R;
import com.udacity.georgebalasca.popularmoviesstage_1.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorj on 03/02/2018.
 */

public class MoviesListArrayAdapter extends ArrayAdapter<Movie> {

    /**
     * Custom constructor for our adapter
     *
     * @param ctx
     * @param movies
     */
    public MoviesListArrayAdapter(Context ctx, ArrayList<Movie> movies){
        super(ctx, 0, movies);

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the Movie object from the ArrayAdapter at the appropriate position
        Movie movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);

        ImageView moviePosterView = (ImageView) convertView.findViewById(R.id.movie_poster);

        moviePosterView.setAdjustViewBounds(true);
        moviePosterView.setPadding(0, 0, 0, 0);

        Picasso.with(getContext())
                .load(movie.getPosterURL().toString())
                .into(moviePosterView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Go to movie details", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
