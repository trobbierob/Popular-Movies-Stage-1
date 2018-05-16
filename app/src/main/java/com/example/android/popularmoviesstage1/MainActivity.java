package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstage1.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    private TextView movieTitle;
    private TextView moviePoster;

    private TextView mEmptyView;
    public String jsonString;
    public URL movieQueryUrl;
    private ListView listView;
    private ArrayList<HashMap<String, String>> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize RV
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //TODO 1 Get Data

        mRecyclerView.setAdapter(mAdapter);


        //movieTitle = (TextView) findViewById(R.id.movie_title);
        //moviePoster = (TextView) findViewById(R.id.movie_poster);
        //searchMovies();
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
                //new FetchMovie(movieTitle, moviePoster).execute();
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
                        Log.i(TAG,"Title is: " + titleString);
                        String posterPath = jsonFirstResult.optString("poster_path");
                        Log.i(TAG,"Poster Path is: " + posterPath);
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
        }
    }

    public void searchMovies(){
        //new MovieQueryTask().execute();
    }

}
