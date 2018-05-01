package com.example.popular_movies.service;


import android.content.Context;

import com.example.popular_movies.fragment.ActivityFragment;
import com.example.popular_movies.fragment.DetailFragment;
import com.example.popular_movies.model.Movie;
import com.example.popular_movies.model.Review;
import com.example.popular_movies.model.Trailer;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import java.io.IOException;

/**
 * Created by Alex on 14/03/2018.
 */

public class MoviesRetrofit {

    private Context mContext;

    public MoviesRetrofit(Context c){
        mContext = c;
    }


    private String get_TMDB_ApiKey(){
        return Constants.API_KEY;
    }

        public void retroMovies(final int nPages, String query, final String movieLang){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Movie.getTmdbEndpoint())
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key",String.valueOf(get_TMDB_ApiKey()));
                        request.addEncodedQueryParam("page",String.valueOf(nPages));
                        request.addEncodedQueryParam("language",String.valueOf(movieLang));
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        MoviesDataSource service = restAdapter.create(MoviesDataSource.class);

        if(query.equals("popular")) {
            service.getPopularMOvies(new Callback<Movie.MovieData>() {
                @Override
                public void success(Movie.MovieData movieData, Response response) {
                    ActivityFragment.mAdapter.setMyMovieList(movieData.getData());
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }else if(query.equals("top")){
            service.getTopMovies(new Callback<Movie.MovieData>() {
                @Override
                public void success(Movie.MovieData movieResult, Response response) {
                    ActivityFragment.mAdapter.setMyMovieList(movieResult.getData());
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }

    }

    public void retroTrailers(final int movieId){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Movie.getTmdbEndpoint() + "/movie/" + movieId)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key",String.valueOf(get_TMDB_ApiKey()));
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesDataSource service = restAdapter.create(MoviesDataSource.class);
        service.getTrailers(new Callback<Trailer.TrailerResult>() {
            @Override
            public void success(Trailer.TrailerResult trailerResult, Response response) {
                DetailFragment.mAdapter.setMovieList(trailerResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public void retroReviews(final int movieId){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Movie.getTmdbEndpoint() + "/movie/" + movieId)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key",String.valueOf(get_TMDB_ApiKey()));
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesDataSource service = restAdapter.create(MoviesDataSource.class);
        service.getReviews(new Callback<Review.ReviewResult>() {
            @Override
            public void success(Review.ReviewResult reviewResult, Response response) {
                DetailFragment.movieReviewsAdapter.setmMovieList(reviewResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}

