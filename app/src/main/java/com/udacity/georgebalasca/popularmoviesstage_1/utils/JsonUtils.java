package com.udacity.georgebalasca.popularmoviesstage_1.utils;

import com.udacity.georgebalasca.popularmoviesstage_1.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jorj on 02/28/2018.
 *
 * Will be used to parse the response to json
 */

public class JsonUtils {

    /**
     * Returns a list with all movie objects after parsing the response to json
     *
     * @param data
     * @param api_key - needed to get the poster URL
     * @return
     */
    public static ArrayList<Movie> getMoviesArray(String api_key, String data){

        // check if data
        if(data != null && data.isEmpty())
            return null;

        // try to convert string result to Json Object
        JSONObject movies = null;
        try {
            movies = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        // get the movie json array
        JSONArray moviesJsonArray = movies.optJSONArray("results");

        // create the array containing movie objects
        ArrayList<Movie> moviesArray = new ArrayList<>();

        // add movie objects in the array created
        for(int i=0; i<moviesJsonArray.length(); i++)
                moviesArray.add( getMovieObjectFromJsonObject( api_key, moviesJsonArray.optJSONObject(i) ) );

        return moviesArray;
    }

    /**
     * Parses a jsonObject into a Movie object
     *
     * @param data
     * @param api_key - needed to get the poster URL
     * @return
     */
    public static Movie getMovieObjectFromJsonObject(String api_key, JSONObject data){

        Movie movie = new Movie();

        movie.setVoteCount(data.optInt("vote_count"));
        movie.setId(data.optInt("id"));
        movie.setVoteCount(data.optInt("vote_average"));
        movie.setTitle(data.optString("title"));
        movie.setOriginalTitle(data.optString("title"));
        movie.setOverview(data.optString("overview"));
        movie.setReleaseDate(data.optString("release_date"));
        movie.setPosterURL(NetUtils.getMoviePosterURL( api_key ,data.optString("poster_path").replaceAll("\\/","")));

        return movie;
    }
}
