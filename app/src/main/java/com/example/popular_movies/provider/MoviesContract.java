package com.example.popular_movies.provider;

import android.net.Uri;

/**
 * Created by Alex on 12/03/2018.
 */
public final class MoviesContract {

    public static final String AUTHORITY = "com.example.popular_movies.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    public static final String MOVIE_PATH = "movies";

    public static final class Movie{

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" +
                "vnd.com.example.popular_movies.provider/movie";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" +
                "vnd.com.example.popular_movies.provider/movie";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, MOVIE_PATH);
        public static final String _ID = "_id";
        public static final String MOVIE_ID = "movie_id";
        public static final String TITLE = "title";
        public static final String POSTER = "poster";
        public static final String DESCRIPTION = "description";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String RELEASE_DATE = "release_date";
        public static final String BACKDROP = "backdrop";
        public static final String IS_FAVORITE = "is_favorite";
    }
}
