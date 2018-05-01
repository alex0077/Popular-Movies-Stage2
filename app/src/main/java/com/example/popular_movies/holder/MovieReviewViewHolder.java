package com.example.popular_movies.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.popular_movies.R;
import com.example.popular_movies.model.Review;

/**
 * Created by Alex on 30/03/2018.
 */
public class MovieReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final Review reviewHolder;

    public final TextView textReview;
    public final TextView textAuthor;

    private final Context context;


    public MovieReviewViewHolder(View itemView){
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        textReview = itemView.findViewById(R.id.text_review);
        textAuthor = itemView.findViewById(R.id.text_author);
        reviewHolder = new Review();
    }

    @Override
    public void onClick(View view){

    }


}