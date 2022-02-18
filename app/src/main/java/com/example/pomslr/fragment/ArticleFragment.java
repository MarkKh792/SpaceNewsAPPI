package com.example.pomslr.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pomslr.MainActivity;
import com.example.pomslr.NetworkService;
import com.example.pomslr.R;
import com.example.pomslr.entity.Article;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleFragment extends Fragment {

    Article article;
    TextView articleHeaderTextView;
    TextView publisherTextView;
    TextView postDateTextView;
    TextView articleTextView;
    TextView linkTextView;
    ImageView articleImageView;

    public ArticleFragment() {
        super(R.layout.article_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        article = MainActivity.selectedArticle;
        articleHeaderTextView = view.findViewById(R.id.articleHeaderTextView);
        articleTextView = view.findViewById(R.id.articleTextView);
        publisherTextView = view.findViewById(R.id.publisherTextView);
        postDateTextView = view.findViewById(R.id.postDateTextView);
        articleImageView = view.findViewById(R.id.articleImageView);
        linkTextView = view.findViewById(R.id.linkTextView);

        DateTimeFormatter isoFormat = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
        DateTime dateTime = isoFormat.parseDateTime(article.getPublishedAt());
        String readableDateTime = dateTime.toString(ISODateTimeFormat.date()) + ", " +
                dateTime.toString(ISODateTimeFormat.hourMinute());
        articleHeaderTextView.setText(article.getTitle());
        publisherTextView.setText(article.getNewsSite());
        postDateTextView.setText(readableDateTime);
        articleTextView.setText(article.getSummary());
        linkTextView.setText(article.getUrl());
        Glide.with(getActivity())
                .load(article.getImageUrl())
                .into(articleImageView);

        /*NetworkService.getInstance() // показать нажатую новость
                .getJSONApi()
                .getArticleById(MainActivity.selectedArticleId)
                .enqueue(new Callback<Article>() {
                    @Override
                    public void onResponse(@NonNull Call<Article> call, @NonNull Response<Article> response) {
                        article = response.body();
                        DateTimeFormatter isoFormat = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
                        DateTime dateTime = isoFormat.parseDateTime(article.getPublishedAt());
                        String readableDateTime = dateTime.toString(ISODateTimeFormat.date()) + ", " +
                                dateTime.toString(ISODateTimeFormat.hourMinute());
                        articleHeaderTextView.setText(article.getTitle());
                        publisherTextView.setText(article.getNewsSite());
                        postDateTextView.setText(readableDateTime);
                        articleTextView.setText(article.getSummary());
                        linkTextView.setText(article.getUrl());
                        Glide.with(getActivity())
                                .load(article.getImageUrl())
                                .into(articleImageView);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {
                        System.out.println("Ошибка обработки запроса!");
                        t.printStackTrace();
                    }
                });*/
    }



}
