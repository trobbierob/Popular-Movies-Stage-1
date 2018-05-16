package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{


    private Context mContext;


    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }


        void bindTo(Movie currentMovie){
            //Populate the TextViews With Data Here

        }

    }

}
