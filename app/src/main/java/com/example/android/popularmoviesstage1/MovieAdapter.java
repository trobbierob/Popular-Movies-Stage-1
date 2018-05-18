package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{


    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<Movie> mMovieData;

    MovieAdapter(Context context, ArrayList<Movie> movieData) {
        this.mMovieData = movieData;
        this.mContext = context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        //Get the current Movie
        Movie currentMovie = mMovieData.get(position);

        //Populate Views with Data
        holder.bindTo(currentMovie);
        Glide.with(mContext).load(currentMovie.getImageResource()).into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mMovieTitle;
        private ImageView mMoviePoster;

        public ViewHolder(View itemView) {
            super(itemView);

            //Initialize
            mMovieTitle = (TextView) itemView.findViewById(R.id.title);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.movieImage);
        }

        void bindTo(Movie currentMovie){
            //Populate Title TextView
            mMovieTitle.setText(currentMovie.getTitle());
        }
    }
}
