package com.udacity.georgebalasca.popularmoviesstage_1.models;

import java.net.URL;

/**
 * Created by Jorj on 02/28/2018.
 */

public class Movie {

    private URL posterURL;
    private String title;
    private int id;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private int voteCount;


    // empty constructor
    public Movie(){
    }


    public URL getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(URL posterURL) {
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }




}
