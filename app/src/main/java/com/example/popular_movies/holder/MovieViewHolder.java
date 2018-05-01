package com.example.popular_movies.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.popular_movies.FavoritesActivity;
import com.example.popular_movies.MovieDetailActivity;
import com.example.popular_movies.MovieListActivity;
import com.example.popular_movies.R;
import com.example.popular_movies.fragment.DetailFragment;
import com.example.popular_movies.model.Movie;

/**
 * Created by Alex on 12/03/2018.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final Movie movieHolder;

    public final ImageView mImageViewMovie;
   // public TextView rankPosition;

    private final Context context;

    public MovieViewHolder(View itemView){
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        mImageViewMovie = itemView.findViewById(R.id.mImageViewMovie);
        //rankPosition = (TextView) itemView.findViewById(R.id.rankPosition);
        movieHolder = new Movie();
    }

    @Override
    public void onClick(View view){

        String s = context.getClass().getName();
        s = s.substring(s.lastIndexOf("."));

        if(s.equals(".MovieListActivity")){
            if (MovieListActivity.getDual()){

                DetailFragment d = MovieListActivity.getDetailFragment();
                d.setFragmentData(movieHolder);

            }else {

                Intent intent = new Intent(context,MovieDetailActivity.class);
                intent.putExtra("title", movieHolder.getTitle());
                intent.putExtra("description",movieHolder.getDescription());
                intent.putExtra("vote_average",movieHolder.getVote_average());
                intent.putExtra("release_date",movieHolder.getRelease_date());
                intent.putExtra("backdrop",movieHolder.getBackdrop());
                intent.putExtra("id",movieHolder.getId());
                intent.putExtra("poster",movieHolder.getPoster());
                context.startActivity(intent);

            }
        }
        if(s.equals(".FavoritesActivity")){
            if (FavoritesActivity.getDual()){

                DetailFragment d = FavoritesActivity.getDetailFragment();
                d.setFragmentData(movieHolder);

            }else {

                Intent intent = new Intent(context,MovieDetailActivity.class);
                intent.putExtra("title", movieHolder.getTitle());
                intent.putExtra("description",movieHolder.getDescription());
                intent.putExtra("vote_average",movieHolder.getVote_average());
                intent.putExtra("release_date",movieHolder.getRelease_date());
                intent.putExtra("backdrop",movieHolder.getBackdrop());
                intent.putExtra("id",movieHolder.getId());
                intent.putExtra("poster",movieHolder.getPoster());
                context.startActivity(intent);

            }
        }
    }
}
