package com.example.android.popularmoviesstage1;

public class Movie {

    private String title;
    private String imageUrl;

    private String voteAverage;
    private String overview;
    private String releaseDate;

    Movie(String title) {
        this.title = title;
    }

    Movie(String title, String imageUrl, String voteAverage, String overview, String releaseDate) {
        this.title = title;

        String BASE_URL = "http://image.tmdb.org/t/p/";
        String BASE_SIZE = "w500";
        this.imageUrl = BASE_URL + BASE_SIZE + imageUrl;

        this.voteAverage = voteAverage;

        this.overview = overview;

        this.releaseDate = releaseDate;
    }

    String getTitle() {
        return title;
    }

    public String getImageResource() {
        return imageUrl;
    }

    String getVoteAverage() {
        return voteAverage;
    }

    String getOverview() {
        return overview;
    }

    String getReleaseDate() {
        return releaseDate;
    }

}
