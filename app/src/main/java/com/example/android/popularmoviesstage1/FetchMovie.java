package com.example.android.popularmoviesstage1;

import android.os.AsyncTask;
import android.widget.TextView;

public class FetchMovie extends AsyncTask<String,Void,String>{

    private TextView mMovieTitle;
    private TextView mMoviePosterString;


    public FetchMovie(TextView mMovieTitle, TextView mMoviePosterString) {
        this.mMovieTitle = mMovieTitle;
        this.mMoviePosterString = mMoviePosterString;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
    }
}
