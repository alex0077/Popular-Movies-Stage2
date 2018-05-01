package com.example.popular_movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.example.popular_movies.fragment.ActivityFragment;
import com.example.popular_movies.fragment.DetailFragment;
import com.example.popular_movies.fragment.FavoritesFragment;

public class FavoritesActivity extends AppCompatActivity {
    private static boolean dual;
    private static DetailFragment f;
    private static FavoritesFragment a;
    private static final String fTag = "FavoritesFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ScrollView s = findViewById(R.id.scroll2);

        dual = s != null;

        if(dual){
            f = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frag2);
        }

        if(savedInstanceState == null){
            FavoritesFragment f = new FavoritesFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.favorites_fragment,f,fTag);
            ft.commit();
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        a = (FavoritesFragment) getSupportFragmentManager().findFragmentByTag(fTag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.show_pop_movies){
            ActivityFragment a = MovieListActivity.getActivityFragment();
            a.refreshMenuMovies("popular");
            Intent intent = new Intent(this, MovieListActivity.class);
            startActivity(intent);
        }

        if(id == R.id.show_top_movies){
            ActivityFragment a = MovieListActivity.getActivityFragment();
            a.refreshMenuMovies("top");
            Intent intent = new Intent(this, MovieListActivity.class);
            startActivity(intent);
        }

        if(id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }

        if(id == R.id.about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        if(id == R.id.show_fav_movies){
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean getDual(){
        return dual;
    }

    public static DetailFragment getDetailFragment(){
        return f;
    }

    public static FavoritesFragment getActivityFragment(){
        return a;
    }
}
