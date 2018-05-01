package com.example.popular_movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popular_movies.R;
import com.example.popular_movies.holder.MovieViewHolder;
import com.example.popular_movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12/03/2018.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>{
        private final List<Movie> mMovieList;
        private final LayoutInflater mInflater;
        private final Context mContext;

        public MoviesAdapter(Context context){
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mMovieList = new ArrayList<>();
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = mInflater.inflate(R.layout.row_movie,parent,false);
            MovieViewHolder viewHolder = new MovieViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position){

                Movie movie = mMovieList.get(position);

            holder.movieHolder.setTitle(movie.getTitle());
            holder.movieHolder.setDescription(movie.getDescription());
            holder.movieHolder.setVote_average(movie.getVote_average());
            holder.movieHolder.setRelease_date(movie.getRelease_date());
            holder.movieHolder.setBackdrop(movie.getBackdrop());
            holder.movieHolder.setId(movie.getId());
            holder.movieHolder.setPoster(movie.getPoster());

            Picasso.with(mContext)
                    .load(Movie.getTmdbImagePath() + movie.getPoster())
                    .into(holder.mImageViewMovie);
           // holder.rankPosition.setText(mContext.getResources().getString(R.string.movie_position, (position + 1)));
            //holder.rankPosition.setText("#" + movie.getTitle());
        }

        @Override
        public int getItemCount(){
            return (mMovieList == null) ? 0 : mMovieList.size();
        }

        public void setMyMovieList(List<Movie> movieList){
            mMovieList.clear();
            mMovieList.removeAll(movieList);
            mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
}
