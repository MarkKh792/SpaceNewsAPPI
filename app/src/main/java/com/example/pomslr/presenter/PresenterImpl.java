package com.example.pomslr.presenter;

import com.example.pomslr.model.api.Article;
import com.example.pomslr.view.fragment.NewsListFragment;
import com.example.pomslr.model.Model;


import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class PresenterImpl implements Presenter {

    private Model model = new Model();
    private NewsListFragment newsListFragment;

    public PresenterImpl(NewsListFragment newsListFragment) {
        this.newsListFragment = newsListFragment;
    }
    @Override
    public void searchArticle() {
        model.getObservableArticle().subscribe(new Observer<ArrayList<Article>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<Article> articles) {
                if (articles != null && !articles.isEmpty()) {
                    //model.clearTable();
                    //model.insertIntoDB(articles);
                    model.insertDataIntoDB(articles);
                    newsListFragment.showList(articles);
                } else {
                    newsListFragment.showEmptyList();

                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                newsListFragment.showList(model.getDataObservable().blockingFirst());
                newsListFragment.showError("Нет подключения к сети"); // e.getMessage()
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
