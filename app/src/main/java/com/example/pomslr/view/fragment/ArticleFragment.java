package com.example.pomslr.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pomslr.view.MainActivity;
import com.example.pomslr.R;
import com.example.pomslr.model.api.Article;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ArticleFragment extends Fragment implements com.example.pomslr.view.View {

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

        showArticle(view);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    public void showArticle(View view) {

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

    }
}
