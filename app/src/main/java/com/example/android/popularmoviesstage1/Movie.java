package com.example.android.popularmoviesstage1;

public class Movie {

    private String title;
    private String imageUrl;
    private String voteAverage;
    private String overview;
    private String releaseDate;
    private String backdrop;

    Movie(String title, String imageUrl, String voteAverage, String overview, String releaseDate, String backdrop) {
        this.title = title;

        String BASE_URL = "http://image.tmdb.org/t/p/";
        String BASE_SIZE = "w500";
        this.imageUrl = BASE_URL + BASE_SIZE + imageUrl;

        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;

        String BASE_URL_BACKDROP = "http://image.tmdb.org/t/p/";
        String BASE_SIZE_BACKDROP = "w500";
        this.backdrop = BASE_URL_BACKDROP + BASE_SIZE_BACKDROP + backdrop;
    }

    String getTitle() {
        return title;
    }

    String getImageResource() {
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

    String getBackdrop() {
        return backdrop;
    }

}
