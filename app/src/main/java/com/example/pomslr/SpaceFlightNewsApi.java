package com.example.pomslr;

import com.example.pomslr.entity.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceFlightNewsApi {

    @GET("/v3/articles")
    public Call<ArrayList<Article>> getAllPosts();

    @GET("/v3/articles/{id}")
    public Call<Article> getArticleById(@Path("id") int id);

}
