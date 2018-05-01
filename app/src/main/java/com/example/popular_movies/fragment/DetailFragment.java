package com.example.popular_movies.fragment;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popular_movies.R;
import com.example.popular_movies.adapter.MovieReviewsAdapter;
import com.example.popular_movies.adapter.MovieTrailersAdapter;
import com.example.popular_movies.data.DBaseMovies;
import com.example.popular_movies.model.Movie;
import com.example.popular_movies.provider.MoviesContract;
import com.example.popular_movies.service.MoviesRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 12/03/2018.
 */

public class DetailFragment extends Fragment{

    public static MovieReviewsAdapter movieReviewsAdapter;
    public static MovieTrailersAdapter mAdapter;
    private RecyclerView mRecyclerView_trailer;
    private RecyclerView getmRecyclerView_review;
    private MoviesRetrofit mMoviesDetails;
    private int mId;
    private String mTitle,mPoster,mDescription,mVote,mRelease,mBack;
    @BindView(R.id.movie_title) TextView mMovieTitle;
    @BindView(R.id.movie_description) TextView mMovieDescription;
    @BindView(R.id.vote_average) TextView mVoteAverage;
    @BindView(R.id.release_date) TextView mReleaseDate;
    //private TextView mMovieTitle, mMovieDescription,mVoteAverage,mReleaseDate;
    @BindView(R.id.back_detail) ImageView mBackPoster;
   // private ImageView mBackPoster;
    private ContentResolver resolver;
    private ContentValues values;
    private DBaseMovies dbMovies;
    @BindView(R.id.fav_check) CheckBox mfavCheck;
    //private CheckBox mfavCheck;
    private static Movie mSavedMovies;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView_trailer = (RecyclerView) view.findViewById(R.id.trailer_recycler);
        mRecyclerView_trailer.setLayoutManager(new LinearLayoutManager(getActivity()));

        getmRecyclerView_review = (RecyclerView) view.findViewById(R.id.review_recycler);
        getmRecyclerView_review.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMoviesDetails = new MoviesRetrofit(getActivity());

        if(mAdapter == null) {
            mAdapter = new MovieTrailersAdapter(getActivity());
        }

        mRecyclerView_trailer.setAdapter(mAdapter);

        if(movieReviewsAdapter == null) {
            movieReviewsAdapter = new MovieReviewsAdapter(getActivity());
        }

        getmRecyclerView_review.setAdapter(movieReviewsAdapter);

       // mMovieTitle = view.findViewById(R.id.movie_title);
       // mMovieDescription = view.findViewById(R.id.movie_description);
       // mVoteAverage = view.findViewById(R.id.vote_average);
       // mReleaseDate = view.findViewById(R.id.release_date);
        //mBackPoster = view.findViewById(R.id.back_detail);
        //mfavCheck = view.findViewById(R.id.fav_check);

        resolver = getActivity().getContentResolver();
        values = new ContentValues();
        dbMovies = new DBaseMovies(resolver);


        if(getArguments() != null) {

            mId = getArguments().getInt("id");
            mTitle = getArguments().getString("title");
            mDescription = getArguments().getString("description");
            mVote = getArguments().getString("vote_average");
            mRelease = getArguments().getString("release_date");
            mBack = getArguments().getString("backdrop");
            mPoster = getArguments().getString("poster");

            values.put(MoviesContract.Movie.MOVIE_ID, mId);
            values.put(MoviesContract.Movie.TITLE, mTitle);
            values.put(MoviesContract.Movie.POSTER, mPoster);
            values.put(MoviesContract.Movie.DESCRIPTION, mDescription);
            values.put(MoviesContract.Movie.VOTE_AVERAGE, mVote);
            values.put(MoviesContract.Movie.RELEASE_DATE, mRelease);
            values.put(MoviesContract.Movie.BACKDROP, mBack);

            mMoviesDetails.retroTrailers(mId);
            mMoviesDetails.retroReviews(mId);

            int mCursorCount = dbMovies.getFavoriteMovieForCheck(mId);
            mSavedMovies = new Movie(mId,mTitle,mPoster,mDescription,mVote,mRelease,mBack,mCursorCount);

            mfavCheck.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    if (mfavCheck.isChecked()) {

                        values.put(MoviesContract.Movie.IS_FAVORITE, 1);

                        Uri insert_result = dbMovies.setFavoriteMovies(values);

                        if (insert_result != null) {
                            Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        dbMovies.deleteMovies(mId);
                        Toast.makeText(getActivity(), getString(R.string.removed), Toast.LENGTH_LONG).show();

                    }
                }
            });




            mMovieTitle.setText(mTitle);
            mMovieDescription.setText(mDescription);
            mVoteAverage.setText(mVote);
            mReleaseDate.setText(mRelease);

            Picasso.with(getActivity())
                    .load(Movie.getTmdbBackDropPath() + mBack)
                    .into(mBackPoster);
        }else {

            if(mSavedMovies != null)
                setFragmentData(mSavedMovies);

        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mSavedMovies != null) {
            mfavCheck = (CheckBox) view.findViewById(R.id.fav_check);

            if (dbMovies.getFavoriteMovieForCheck(mSavedMovies.getId()) > 0) {
                mfavCheck.setChecked(true);
            } else {
                mfavCheck.setChecked(false);
            }
        }
    }

    public void setFragmentData(Movie movie){

            mId = movie.getId();
            mTitle = movie.getTitle();
            mDescription = movie.getDescription();
            mVote = movie.getVote_average();
            mRelease = movie.getRelease_date();
            mBack = movie.getBackdrop();
            mPoster = movie.getPoster();

        int mCursorCount = dbMovies.getFavoriteMovieForCheck(mId);
        mSavedMovies = new Movie(mId,mTitle,mPoster,mDescription,mVote,mRelease,mBack,mCursorCount);

        mMovieTitle.setText(mTitle);
        mMovieDescription.setText(mDescription);
        mVoteAverage.setText(mVote);
        mReleaseDate.setText(mRelease);

        Picasso.with(getActivity())
                .load(Movie.getTmdbBackDropPath() + mBack)
                .into(mBackPoster);

        values.clear();
        values.put(MoviesContract.Movie.MOVIE_ID, mId);
        values.put(MoviesContract.Movie.TITLE, mTitle);
        values.put(MoviesContract.Movie.POSTER, mPoster);
        values.put(MoviesContract.Movie.DESCRIPTION, mDescription);
        values.put(MoviesContract.Movie.VOTE_AVERAGE, mVote);
        values.put(MoviesContract.Movie.RELEASE_DATE, mRelease);
        values.put(MoviesContract.Movie.BACKDROP, mBack);

        mAdapter = new MovieTrailersAdapter(getActivity());
        movieReviewsAdapter = new MovieReviewsAdapter(getActivity());
        mRecyclerView_trailer.setAdapter(mAdapter);
        getmRecyclerView_review.setAdapter(movieReviewsAdapter);

        mMoviesDetails.retroTrailers(mId);
        mMoviesDetails.retroReviews(mId);

        mfavCheck = (CheckBox) view.findViewById(R.id.fav_check);

        if(mCursorCount > 0){
        mfavCheck.setChecked(true);
    }else{
        mfavCheck.setChecked(false);
    }

        mfavCheck.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {


            if (mfavCheck.isChecked()) {

                values.put(MoviesContract.Movie.IS_FAVORITE, 1);

                Uri insert_result = dbMovies.setFavoriteMovies(values);

                if (insert_result != null) {
                    Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_LONG).show();

                    refreshFavorite();

                } else {
                    Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            } else {
                dbMovies.deleteMovies(mId);
                Toast.makeText(getActivity(), getString(R.string.removed), Toast.LENGTH_LONG).show();

                refreshFavorite();
            }
        }
    });
}

    public void refreshFavorite(){
       if(FavoritesFragment.mAdapter != null) {
            List<Movie> fav;
            fav = dbMovies.getFavoriteMovies();
            FavoritesFragment.mAdapter.setMyMovieList(fav);
            FavoritesFragment.mRecyclerView.setAdapter(FavoritesFragment.mAdapter);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
