package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstage1.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private ArrayList<Movie> mMovieData;

    private TextView mEmptyView;

    private String jsonString;
    private URL movieQueryUrl;
    private List<String> movieTitleArray = new ArrayList<String>();
    private ArrayList<String> moviePosterArray = new ArrayList<String>();
    private ArrayList<String> moviePopularityArray = new ArrayList<String>();
    private ArrayList<String> movieOverviewArray = new ArrayList<String>();
    private ArrayList<String> movieReleaseDateArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize RV
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set GridLayoutManager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        //Initialize ArrayList
        mMovieData = new ArrayList<>();

        //Initialize Adapter and set it to RecyclerView
        mAdapter = new MovieAdapter(this,mMovieData);
        mRecyclerView.setAdapter(mAdapter);

        //Load movies at startup
        searchMovies();

        //Swipe, Drag & Drop
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMovieData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if (menuItemSelected == R.id.action_bar_search) {
            ConnectivityManager cm = (ConnectivityManager)
                    MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                Log.i(TAG, "I'm Pickle Rick!");
                Toast.makeText(this,"Connected",
                        Toast.LENGTH_SHORT).show();
                new MovieQueryTask().execute();
            } else { // not connected to the internet
                Log.i(TAG, "wubba lubba dub dub");
                Toast.makeText(this,"Check Internet Connection",
                        Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class MovieQueryTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            // This is an empty string on purpose
            // This will be used to pass the different sort
            // options available
            movieQueryUrl = NetworkUtils.buildUrl("");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (movieQueryUrl != null) {
                try {
                    jsonString = NetworkUtils.getResponseFromHttpUrl(movieQueryUrl);
                    Log.i(TAG, "JSON String is: " + jsonString);

                    JSONObject jsonRootObject = new JSONObject(jsonString);
                    Log.i(TAG, "JSON Root is: " + jsonRootObject);

                    JSONArray resultsArray = jsonRootObject.optJSONArray("results");
                    Log.i(TAG, "Results JSON String is: " + resultsArray);

                    for (int i = 0; i < 10; i++) {

                        JSONObject jsonFirstResult = resultsArray.getJSONObject(i);
                        String titleString = jsonFirstResult.optString("title");
                        movieTitleArray.add(titleString);
                        Log.i(TAG,"Title is: " + titleString);

                        String posterPath = jsonFirstResult.optString("poster_path");
                        moviePosterArray.add(posterPath);
                        Log.i(TAG,"Poster Path is: " + posterPath);

                        String popularityPath = jsonFirstResult.optString("popularity");
                        moviePopularityArray.add(popularityPath);
                        Log.i(TAG,"Popularity Path is: " + popularityPath);

                        String overview = jsonFirstResult.optString("overview");
                        movieOverviewArray.add(overview);
                        Log.i(TAG,"Overview is: " + overview);

                        String releaseDate = jsonFirstResult.optString("release_date");
                        movieReleaseDateArray.add(releaseDate);
                        Log.i(TAG,"Release Date is: " + releaseDate);
                    }

                } catch (IOException e) {

                } catch (JSONException e) {

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.i(TAG,"movieTitleArray is: " + movieTitleArray);
            Log.i(TAG,"moviePosterArray is: " + moviePosterArray);

            //This will convert the ArrayList to String[]s
            String [] movieTitleArrayConvert = movieTitleArray.toArray(new String[movieTitleArray.size()]);
            String [] moviePosterArrayConvert = moviePosterArray.toArray(new String[moviePosterArray.size()]);

            String [] moviePopularityArrayConvert = moviePopularityArray.toArray(new String[moviePopularityArray.size()]);
            String [] movieOverviewArrayConvert = movieOverviewArray.toArray(new String[movieOverviewArray.size()]);
            String [] movieReleaseDateArrayConvert = movieReleaseDateArray.toArray(new String[movieReleaseDateArray.size()]);

            mMovieData.clear();

            for (int i=0; i < movieTitleArrayConvert.length; i++){
                mMovieData.add(new Movie(movieTitleArrayConvert[i], moviePosterArrayConvert[i],
                        moviePopularityArrayConvert[i], movieOverviewArrayConvert[i], movieReleaseDateArrayConvert[i]));
            }

            mAdapter.notifyDataSetChanged();
        }
    }

    public void searchMovies(){
        new MovieQueryTask().execute();
    }
}
