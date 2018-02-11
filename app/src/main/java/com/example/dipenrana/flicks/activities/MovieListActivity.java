package com.example.dipenrana.flicks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dipenrana.flicks.R;
import com.example.dipenrana.flicks.adapters.MovieAdapter;
import com.example.dipenrana.flicks.models.Movie;
import com.example.dipenrana.flicks.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {

    private ArrayList<Movie> movieList = new ArrayList<Movie>();
    MovieAdapter movieAdapter;
    RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieAdapter = new MovieAdapter(this, movieList);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovieList);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        
        ConnectHttpClient();

    }

    private void ConnectHttpClient() {

        String url = NetworkUtil.API_URL + NetworkUtil.API_KEY;

        OkHttpClient netClient = new OkHttpClient();
        Request request =  new Request.Builder()
                                        .url(url)
                                        .build();
        netClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonResponse= null;
                try {
                    jsonResponse = new JSONObject(response.body().string());
                    JSONArray responseArray = jsonResponse.getJSONArray("results");
                    movieList.addAll(Movie.parseJsonArray(responseArray.toString()));
                    MovieListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieAdapter.notifyDataSetChanged();

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
