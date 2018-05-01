package com.example.popular_movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.example.popular_movies.R;
import com.example.popular_movies.model.Review;
import com.example.popular_movies.holder.MovieReviewViewHolder;

/**
 * Created by Alex on 29/03/2018.
 */
public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewViewHolder>{
    private List<Review> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieReviewsAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMovieList = new ArrayList<>();
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.review_movie,parent,false);
        MovieReviewViewHolder viewHolder = new MovieReviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder holder, int position){
        Review review = mMovieList.get(position);
        holder.textReview.setText(review.getReview());
        holder.textAuthor.setText(review.getAuthor());
    }

    @Override
    public int getItemCount(){
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setmMovieList(List<Review> reviewList){
        mMovieList.clear();
        mMovieList.addAll(reviewList);
        notifyDataSetChanged();
    }
}
