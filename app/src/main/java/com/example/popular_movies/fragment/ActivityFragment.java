package com.example.popular_movies.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popular_movies.MovieListActivity;
import com.example.popular_movies.R;
import com.example.popular_movies.adapter.MoviesAdapter;
import com.example.popular_movies.service.MoviesRetrofit;

/**
 * Created by Alex on 12/03/2018.
 */
public class ActivityFragment extends Fragment{

    private RecyclerView mRecyclerView;
    public static MoviesAdapter mAdapter;
    private Parcelable mListState;
    private String query;
    private String movieLang;
    private static final String LIST_STATE_KEY = "ListRestore";
    private static final String MOVIE_QUERY = "MovieQuery";
    private static final String LANG_MOVIE = "movieLang";
    private static final String RESTORE_PAGE = "PageRestore";
    private MoviesRetrofit mMoviesRetro;

    private int pageNumber = 1;
    private FloatingActionButton btn_go_back, btn_go_next;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


        View view = inflater.inflate(R.layout.activity_movie_list_fragment, container, false);

        btn_go_back = (FloatingActionButton) view.findViewById(R.id.goback);
        btn_go_next = (FloatingActionButton) view.findViewById(R.id.gonext);

        if(getPageNumber() == 1){
            btn_go_back.setVisibility(View.GONE);
        }

        mRecyclerView = view.findViewById(R.id.recyclerView);

        if(MovieListActivity.getDual()) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }

        if(mAdapter == null) {
            mAdapter = new MoviesAdapter(getActivity());
        }

        mRecyclerView.setAdapter(mAdapter);
        mMoviesRetro = new MoviesRetrofit(getActivity());

        btn_go_back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (getPageNumber() > 1) {
                        changePageCount(false);
                    }

                    mMoviesRetro.retroMovies(getPageNumber(), getQuery(), movieLang);
                }
            });


        btn_go_next.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    changePageCount(true);

                    mMoviesRetro.retroMovies(getPageNumber(),getQuery(),movieLang);
                }
            });


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        mListState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        savedInstanceState.putParcelable(LIST_STATE_KEY,mListState);
        savedInstanceState.putString(MOVIE_QUERY,getQuery());
        savedInstanceState.putString(LANG_MOVIE, movieLang);
        savedInstanceState.putInt(RESTORE_PAGE, getPageNumber());
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String prefLang = sharedPrefs.getString(getString(R.string.pref_language_key), getString(R.string.pref_value_lang_en));

        if (!movieLang.equals(prefLang)) {

            movieLang = prefLang;
            setPageNumber(1);
            mMoviesRetro.retroMovies(getPageNumber(), getQuery(), movieLang);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (savedInstanceState == null) {

            setQuery(getResources().getString(R.string.pref_value_popular));
            movieLang = sharedPrefs.getString(getString(R.string.pref_language_key), getString(R.string.pref_value_lang_en));
            mMoviesRetro.retroMovies(getPageNumber(), getQuery(), movieLang);
        } else {

            String savedQuery = savedInstanceState.getString(MOVIE_QUERY);
            String savedLang = savedInstanceState.getString(LANG_MOVIE);

            setQuery(savedQuery);
                movieLang = savedLang;
                mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
                setPageNumber(savedInstanceState.getInt(RESTORE_PAGE));
                mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);
                mMoviesRetro.retroMovies(getPageNumber(), getQuery(), movieLang);
            }
        }

    private void setQuery(String query){
        this.query = query;
    }

    private String getQuery(){
        return query;
    }

    public void refreshMenuMovies(String q){
        setQuery(q);
        mMoviesRetro.retroMovies(getPageNumber(), q, movieLang);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public int getPageNumber(){
        return this.pageNumber;
    }

    public void setPageNumber(int p){
        this.pageNumber = p;

        if(pageNumber > 1){
            btn_go_back.setVisibility(View.VISIBLE);
        }else{
            btn_go_back.setVisibility(View.GONE);
        }
    }

    public void changePageCount(boolean operation){

        if(operation){
            pageNumber++;
        }else{
            pageNumber--;
        }

        if(pageNumber > 1){
            btn_go_back.setVisibility(View.VISIBLE);
        }else{
            btn_go_back.setVisibility(View.GONE);
        }

    }

}
