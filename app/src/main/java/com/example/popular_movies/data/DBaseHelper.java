package com.example.popular_movies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 22/03/2018.
 */

public class DBaseHelper extends SQLiteOpenHelper {

    private static final String MOVIES_DB = "MoviesDB";
    private static int VERSION = 1;

    private static class MoviesTable {
        private static final String TABLE = "movies";
        private static final String _ID = "_id";
        private static final String MOVIE_ID = "movie_id";
        private static final String TITLE = "title";
        private static final String POSTER = "poster";
        private static final String DESCRIPTION = "description";
        private static final String VOTE_AVERAGE = "vote_average";
        private static final String RELEASE_DATE = "release_date";
        private static final String BACKDROP = "backdrop";
        private static final String IS_FAVORITE = "is_favorite";

        public static final String[] COLUMNS = new String[]{
                _ID, MOVIE_ID, TITLE, POSTER, DESCRIPTION, VOTE_AVERAGE, RELEASE_DATE, BACKDROP
        };
    }

    public DBaseHelper(Context context) {
        super(context, MOVIES_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + MoviesTable.TABLE +
                "(" + MoviesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MoviesTable.MOVIE_ID + " INTEGER," +
                MoviesTable.TITLE + " TEXT," +
                MoviesTable.POSTER + " TEXT," +
                MoviesTable.DESCRIPTION + " TEXT," +
                MoviesTable.VOTE_AVERAGE + " TEXT," +
                MoviesTable.RELEASE_DATE + " TEXT," +
                MoviesTable.BACKDROP + " TEXT," +
                MoviesTable.IS_FAVORITE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
