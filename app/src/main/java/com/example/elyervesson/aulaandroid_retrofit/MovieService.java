package com.example.elyervesson.aulaandroid_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by elyervesson on 17/04/17.
 */

public interface MovieService {
    @Headers({
            "trakt-api-key: bb010eab6d293013e9a901a64adeda1e783aeac71d744262f68cc2aeb762ab2e",
            "trakt-api-version: 2"
    })
    @GET("movies/trending")
    Call<List<Result>> listTrending();
}
