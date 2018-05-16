package com.example.android.popularmoviesstage1;

import android.widget.TextView;

public class FetchMovie{

    private TextView mMovieTitle;
    private TextView mMoviePosterString;


    public FetchMovie(TextView mMovieTitle, TextView mMoviePosterString) {
        this.mMovieTitle = mMovieTitle;
        this.mMoviePosterString = mMoviePosterString;
    }

}
