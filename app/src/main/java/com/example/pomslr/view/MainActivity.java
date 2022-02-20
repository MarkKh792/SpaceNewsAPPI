package com.example.pomslr.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.pomslr.R;
import com.example.pomslr.model.api.Article;

public class MainActivity extends AppCompatActivity {

    public static Article selectedArticle;
    private static Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}