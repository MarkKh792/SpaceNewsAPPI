package com.example.pomslr.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pomslr.MainActivity;
import com.example.pomslr.NetworkService;
import com.example.pomslr.R;
import com.example.pomslr.adapter.ArticleListAdapter;
import com.example.pomslr.entity.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListFragment extends Fragment {

    public NewsListFragment() {
        super(R.layout.news_list_fragment);
    }

    FragmentManager fragmentManager;

    RecyclerView recyclerView;
    ArticleListAdapter articleListAdapter;
    ArrayList<Article> articleList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TextView textView2 = view.findViewById(R.id.textView2);
        fragmentManager = getParentFragmentManager();
        NetworkService.getInstance() // отобразить последние 10 новостй
                .getJSONApi()
                //.getArticleById(13756)
                .getAllPosts()
                .enqueue(new Callback<ArrayList<Article>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Article>> call, @NonNull Response<ArrayList<Article>> response) {
                        ArrayList<Article> articles = response.body();
                        //textView.setText(articles.get(100).toString());
                        articleList.addAll(articles);
                        initRecyclerView(view);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Article>> call, @NonNull Throwable t) {
                        //textView2.append("Ошибка обработки запроса!");
                        System.out.println("Ошибка обработки запроса!");
                        t.printStackTrace();
                    }
                });

    }

    private void initRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        articleListAdapter = new ArticleListAdapter(NewsListFragment.this.getActivity(), this.getContext(), this.fragmentManager);
        recyclerView.setAdapter(articleListAdapter);
        articleListAdapter.setItems(articleList);

    }
}
