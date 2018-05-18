package com.example.android.popularmoviesstage1;

public class Movie {

    private String title;
    private String imageUrl;

    Movie(String title) {
        this.title = title;
    }

    Movie(String title, String imageUrl) {
        this.title = title;
        String BASE_URL = "http://image.tmdb.org/t/p/";
        String BASE_SIZE = "w92";
        this.imageUrl = BASE_URL + BASE_SIZE + imageUrl;
    }

    String getTitle() {
        return title;
    }

    public String getImageResource() {
        return imageUrl;
    }

}
