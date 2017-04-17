package com.example.elyervesson.aulaandroid_retrofit;

import java.util.Map;

/**
 * Created by elyervesson on 17/04/17.
 */

class Movie {
    public String title;
    public int year;
    public Map<String, String> ids;

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }

}
