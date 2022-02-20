package com.example.pomslr.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pomslr.presenter.PresenterImpl;
import com.example.pomslr.R;
import com.example.pomslr.view.ArticleListView;
import com.example.pomslr.view.adapter.ArticleListAdapter;
import com.example.pomslr.model.api.Article;

import java.util.ArrayList;

public class NewsListFragment extends Fragment implements ArticleListView {

    public NewsListFragment() {
        super(R.layout.news_list_fragment);
    }

    FragmentManager fragmentManager;

    RecyclerView recyclerView;
    ArticleListAdapter articleListAdapter;
    public PresenterImpl presenterImpl = new PresenterImpl(this);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getParentFragmentManager();
        presenterImpl.searchArticle();
    }

    private void initRecyclerView(View view, ArrayList<Article> articleList) {

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        articleListAdapter = new ArticleListAdapter(NewsListFragment.this.getActivity(), this.getContext(), this.fragmentManager);
        recyclerView.setAdapter(articleListAdapter);
        articleListAdapter.setItems(articleList);

    }

    @Override
    public void showList(ArrayList<Article> articleList) {
        initRecyclerView(requireView(), articleList);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(getContext(), "Новостей нет", Toast.LENGTH_LONG).show();
    }
}
