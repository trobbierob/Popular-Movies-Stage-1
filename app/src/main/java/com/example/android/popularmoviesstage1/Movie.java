package com.example.android.popularmoviesstage1;

public class Movie {

    private String title;
    private String imageUrl;

    Movie(String title) {
        this.title = title;
    }

    Movie(String title, String imageUrl) {
        this.title = title;
        //this.imageUrl = imageUrl;
    }

    String getTitle() {
        return title;
    }

    public String getImageResource() {
        return imageUrl;
    }

}
