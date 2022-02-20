package com.example.pomslr.model.db;

import android.provider.BaseColumns;

public class ArticleContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ArticleContract(){}

    public static class ArticleEntry implements BaseColumns {

        public static final String TABLE_NAME = "article";

        public static final String COLUMN_NAME_ARTICLE_ID = "article_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SITE_URL = "news_site";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_SUMMARY = "summary";
        public static final String COLUMN_NAME_PUBLISHED_AT = "published_at";
    }
}
