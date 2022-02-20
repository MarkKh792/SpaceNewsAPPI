package com.example.pomslr.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.pomslr.model.api.NetworkService;
import com.example.pomslr.model.api.SpaceFlightNewsApi;
import com.example.pomslr.model.api.Article;
import com.example.pomslr.model.db.ArticleContract;
import com.example.pomslr.model.db.ArticlesDBHelper;
import com.example.pomslr.view.MainActivity;
import com.example.pomslr.view.fragment.ArticleFragment;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Model {

    SpaceFlightNewsApi spaceFlightNewsApi = NetworkService.getInstance().getJSONApi();
    ArticlesDBHelper dbHelper = new ArticlesDBHelper(MainActivity.getContext());
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    public Observable<ArrayList<Article>> getObservableArticle() {
        return spaceFlightNewsApi.getAllPostsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*public void insertIntoDB(ArrayList<Article> articlelist){

        for (int i = 0; i < articlelist.size(); i++){
            ContentValues values = new ContentValues();
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_ID, articlelist.get(i).getId());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE, articlelist.get(i).getTitle());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SITE_URL, articlelist.get(i).getNewsSite());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_IMAGE_URL, articlelist.get(i).getImageUrl());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SUMMARY, articlelist.get(i).getSummary());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT, articlelist.get(i).getPublishedAt());
            db.insert(ArticleContract.ArticleEntry.TABLE_NAME, null, values);
        }

    }*/

}
