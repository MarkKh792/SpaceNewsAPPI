package com.example.pomslr.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArticlesDBHelper extends SQLiteOpenHelper implements DBQueries{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ArticlesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES());
        onCreate(db);
    }

    @Override
    public String SQL_CREATE_ENTRIES() {
        return "CREATE TABLE " + ArticleContract.ArticleEntry.TABLE_NAME + " (" +
                ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_ID + " INTEGER PRIMARY KEY," +
                ArticleContract.ArticleEntry.COLUMN_NAME_TITLE + " TEXT," +
                ArticleContract.ArticleEntry.COLUMN_NAME_SITE_URL + " TEXT," +
                ArticleContract.ArticleEntry.COLUMN_NAME_IMAGE_URL + " TEXT," +
                ArticleContract.ArticleEntry.COLUMN_NAME_SUMMARY + " TEXT," +
                ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT + " TEXT)";
    }

    @Override
    public String SQL_DELETE_ENTRIES() {
        return "DROP TABLE IF EXISTS " + ArticleContract.ArticleEntry.TABLE_NAME;
    }
}
