package com.example.popular_movies.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.popular_movies.R;
import com.example.popular_movies.model.Trailer;

/**
 * Created by Alex on 30/04/2018.
 */
public class MovieTrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public Trailer trailerHolder;

    public TextView textView;
    public Button trailer_number;

    private final Context context;


    public MovieTrailerViewHolder(View itemView){
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        textView = itemView.findViewById(R.id.text_trailer);
        trailer_number = itemView.findViewById(R.id.trailer_number);
        trailer_number.setOnClickListener(this);
        trailerHolder = new Trailer();
    }

    @Override
    public void onClick(View view){

        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Trailer.getYouUrl() + textView.getText()));

        Intent appIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Trailer.getYouApp() + textView.getText()));

        try {
            context.startActivity(appIntent);
        } catch (Exception ex) {
            context.startActivity(webIntent);
        }
    }


}