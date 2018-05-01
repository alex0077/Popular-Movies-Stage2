package com.example.popular_movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {

    private String author;

    @SerializedName("content")
    private String review;


    public String getAuthor(){
        return author;
    }


    public Review(){}

    public void setAuthor(String author){
        this.author = author;
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){
        this.review = review;
    }


    public static class ReviewResult{
        private List<Review> results;

        public List<Review> getResults(){
            return results;
        }
    }
}
