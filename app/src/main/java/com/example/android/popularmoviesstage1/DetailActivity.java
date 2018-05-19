package com.example.android.popularmoviesstage1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView movieTitle = (TextView) findViewById(R.id.detail_title);
        ImageView movieImage = (ImageView) findViewById(R.id.detail_movieImage);

        TextView movieSynopsis = (TextView) findViewById(R.id.detail_synopsis);
        TextView movieRating = (TextView) findViewById(R.id.detail_user_rating);
        TextView movieReleaseDate = (TextView) findViewById(R.id.detail_release_date);

        movieTitle.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getStringExtra("image_resource")).into(movieImage);
        movieSynopsis.setText(getIntent().getStringExtra("overview"));
        movieRating.setText(getIntent().getStringExtra("user_rating"));
        movieReleaseDate.setText(getIntent().getStringExtra("release_date"));
    }
}
