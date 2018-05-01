package com.example.popular_movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popular_movies.R;
import com.example.popular_movies.holder.MovieTrailerViewHolder;
import com.example.popular_movies.model.Trailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 28/03/2018.
 */
public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailerViewHolder>{
    private List<Trailer> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieTrailersAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMovieList = new ArrayList<>();
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.trailer_movie,parent,false);
        MovieTrailerViewHolder viewHolder = new MovieTrailerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position){
        Trailer trailer = mMovieList.get(position);
        holder.textView.setText(trailer.getKey());
        holder.trailer_number.setText("Trailer: " + String.valueOf(position+1));
    }

    @Override
    public int getItemCount(){
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<Trailer> trailerList){
        mMovieList.clear();
        mMovieList.addAll(trailerList);
        notifyDataSetChanged();
    }
}
