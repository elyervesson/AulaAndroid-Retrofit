package com.example.elyervesson.aulaandroid_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Result>> {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_movies);
        

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.trakt.tv")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        movieService.listTrending().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
        List<Result> results = response.body();
        List<Movie> movies = new ArrayList<>();
        for (Result r : results) {
            movies.add(r.movie);
        }
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies));
    }

    @Override
    public void onFailure(Call<List<Result>> call, Throwable t) {
        Log.d("TAG", t.getMessage());
    }

    // http://docs.trakt.apiary.io/#introduction/dates
    // https://developers.themoviedb.org/3/movies/get-movie-images
}
