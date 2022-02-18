package com.example.pomslr.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.pomslr.MainActivity;
import com.example.pomslr.R;
import com.example.pomslr.entity.Article;
import com.example.pomslr.fragment.ArticleFragment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;
import java.util.Collection;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    ArrayList<Article> articleList = new ArrayList<>();
    Activity activity;
    Context context;
    FragmentManager fragmentManager;

    public ArticleListAdapter(Activity activity, Context context, FragmentManager fragmentManager){
        this.activity = activity;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.choose_article_appearance, parent, false);
        return new ArticleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListAdapter.ViewHolder holder, int position) {

        DateTimeFormatter isoFormat = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
        DateTime dateTime = isoFormat.parseDateTime(articleList.get(position).getPublishedAt());
        String readableDateTime = dateTime.toString(ISODateTimeFormat.date()) + ", " +
                dateTime.toString(ISODateTimeFormat.hourMinute());
        holder.titleTextView.setText(articleList.get(position).getTitle());
        holder.sourceTextView.setText(articleList.get(position).getNewsSite());
        holder.dateTextView.setText(readableDateTime);
        Glide.with(activity)
                .load(articleList.get(position).getImageUrl())
                .into(holder.articlePreviewImage);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.selectedArticle = articleList.get(holder.getAbsoluteAdapterPosition());
                        //.getId();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, ArticleFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("newsListFragment")
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setItems (Collection<Article> articles){
        System.out.println("-------------"+ articles);
        articleList.addAll(articles);
        articles.clear();
        notifyDataSetChanged();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView sourceTextView;
        TextView dateTextView;
        ImageView articlePreviewImage;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            articlePreviewImage = itemView.findViewById(R.id.previewImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
