package com.example.pomslr.model.api;

import com.example.pomslr.model.api.Article;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface SpaceFlightNewsApi {

    @GET("/v3/articles")
    Observable<ArrayList<Article>> getAllPostsObservable();

}
