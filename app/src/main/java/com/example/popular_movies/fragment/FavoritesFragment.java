package com.example.popular_movies.fragment;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popular_movies.FavoritesActivity;
import com.example.popular_movies.adapter.MoviesAdapter;
import com.example.popular_movies.data.DBaseMovies;
import com.example.popular_movies.model.Movie;

import com.example.popular_movies.R;
import java.util.List;

public class FavoritesFragment extends Fragment {
    // the fragment initialization parameters
    public static RecyclerView mRecyclerView;
    public static MoviesAdapter mAdapter;
    private ContentResolver resolver;
    private DBaseMovies dBaseMovies;
    private List<Movie> favorite;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        if(FavoritesActivity.getDual()) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }


        mAdapter = new MoviesAdapter(getActivity());

        resolver = getActivity().getContentResolver();
        dBaseMovies = new DBaseMovies(resolver);
        favorite = dBaseMovies.getFavoriteMovies();

        mAdapter.setMyMovieList(favorite);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        favorite.clear();
        favorite = dBaseMovies.getFavoriteMovies();
        mAdapter.setMyMovieList(favorite);
        mRecyclerView.setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void onRefresh(){
        favorite.clear();
        favorite = dBaseMovies.getFavoriteMovies();
        mAdapter.setMyMovieList(favorite);
        mRecyclerView.setAdapter(mAdapter);
    }
}
