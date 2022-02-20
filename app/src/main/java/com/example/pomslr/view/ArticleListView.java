package com.example.pomslr.view;

import com.example.pomslr.model.api.Article;

import java.util.ArrayList;

public interface ArticleListView extends View {

    void showList(ArrayList<Article> articleList);

    void showEmptyList();
}
