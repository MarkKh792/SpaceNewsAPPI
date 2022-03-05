package com.example.pomslr.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pomslr.model.api.NetworkService;
import com.example.pomslr.model.api.SpaceFlightNewsApi;
import com.example.pomslr.model.api.Article;
import com.example.pomslr.model.db.ArticleContract;
import com.example.pomslr.model.db.ArticlesDBHelper;
import com.example.pomslr.view.MainActivity;
import com.example.pomslr.view.fragment.ArticleFragment;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Model {

    SpaceFlightNewsApi spaceFlightNewsApi = NetworkService.getInstance().getJSONApi();
    ArticlesDBHelper dbHelper;// = new ArticlesDBHelper(MainActivity.getContext());

    public Observable<ArrayList<Article>> getObservableArticle() {

        return spaceFlightNewsApi.getAllPostsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void insertIntoDB(ArrayList<Article> articleList){

        dbHelper = new ArticlesDBHelper(MainActivity.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < articleList.size(); i++){
            System.out.println(articleList.get(i).getId());
            ContentValues values = new ContentValues();
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_ID, articleList.get(i).getId());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE, articleList.get(i).getTitle());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SITE_URL, articleList.get(i).getNewsSite());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_IMAGE_URL, articleList.get(i).getImageUrl());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SUMMARY, articleList.get(i).getSummary());
            values.put(ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT, articleList.get(i).getPublishedAt());
            db.insert(ArticleContract.ArticleEntry.TABLE_NAME, null, values);
        }

    }

    public ArrayList<Article> readFromDB () {

        dbHelper = new ArticlesDBHelper(MainActivity.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Article> articleListFromDB = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + ArticleContract.ArticleEntry.TABLE_NAME, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            articleListFromDB.add(
                    new Article(c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5)
                    )
            );
            System.out.println("DATA: " + articleListFromDB.get(c.getPosition()).getId());
        }
        return articleListFromDB;
    }

    public Callable<ArrayList<Article>> readObservableFromDB() {

        return new Callable<ArrayList<Article>>() {
            @Override
            public ArrayList<Article> call() throws Exception {
                return readFromDB();
            }
        };
    }

    private static Observable<ArrayList<Article>> makeObservable (final Callable<ArrayList<Article>> func){

        return Observable.create(new ObservableOnSubscribe<ArrayList<Article>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<Article>> emitter) throws Throwable {
                ArrayList<Article> observed = func.call();
                if(observed != null) {
                    emitter.onNext(observed);
                }
                emitter.onComplete();
            }
        });
    }

    public  Observable<ArrayList<Article>> getDataObservable(){

        return makeObservable(readObservableFromDB());
    }

    public void insertDataIntoDB(ArrayList<Article> incomingArticleList){

       Callable<Void> clb = new Callable<Void>() {
           @Override
           public Void call() throws Exception {
               clearTable();
               insertIntoDB(incomingArticleList);
               return null;
           }
       };
       Completable.fromCallable(clb).subscribe();
    }

    public void clearTable(){

        dbHelper = new ArticlesDBHelper(MainActivity.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + ArticleContract.ArticleEntry.TABLE_NAME);

    }

}
