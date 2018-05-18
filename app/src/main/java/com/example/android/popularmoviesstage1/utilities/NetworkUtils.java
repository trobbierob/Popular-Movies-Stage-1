package com.example.android.popularmoviesstage1.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/discover/movie?";

    // TODO Remember to remove API KEY
    private static final String API_KEY = "";

    private static final String LANGUAGE = "en-US";

    private static final String SORT_BY = "popularity.desc";

    private static final String SIZE = "";

    private static final String FILE_PATH = "";

    /**
    static String getMovieInfo(String string){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJSONString = null;
        return movieJSONString;
    }
     */

    public static URL buildUrl(String string) {
        Uri builtUri = Uri.parse(BASE_MOVIE_URL).buildUpon()
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("language", LANGUAGE)
                .appendQueryParameter("sort_by", SORT_BY)
                .appendQueryParameter("include_adult", "true")
                .appendQueryParameter("include_video", "false")
                .appendQueryParameter("page", "1")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}