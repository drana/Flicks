package com.example.dipenrana.flicks.utils;

/**
 * Created by dipenrana on 9/12/17.
 */

public class NetworkUtil {

    public static final String API_KEY = "bf60f19c76b0c89c52708146fab54ba2";
    public static final String API_URL = "http://api.themoviedb.org/3/movie/now_playing?api_key=";

    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w1280/";
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w780";

    public static  final String GENRE_PATH = "https://api.themoviedb.org/3/genre/movie/list?api_key=bf60f19c76b0c89c52708146fab54ba2&language=en-US";


    public static final String NETWORK_ERROR_MSG = "Unable to connect to server.\nTry again later.";

}
