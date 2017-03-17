package com.example.lmotisi.myapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Post> news;
    private OnListItemClickListener listener;

    public MainAdapter(ArrayList<Post> news, OnListItemClickListener listener) {
        this.news = news;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_simple, parent, false), listener, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bindValue(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView title;
        private TextView content;
        private ImageView image;
        private OnListItemClickListener listener;

        MyViewHolder(View itemView, OnListItemClickListener listItemClickListener, Context context) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            this.listener = listItemClickListener;
            this.context = context;
            itemView.setOnClickListener(this);
            content = (TextView) itemView.findViewById(R.id.content);
            image = (ImageView) itemView.findViewById(R.id.newsImage);
        }

        void bindValue(Post news) {
            itemView.setTag(news);
            title.setText(news.title);
            content.setText(news.excerpt);
            if(!news.attachments.isEmpty()) {
                Picasso.with(context)
                        .load(news.attachments.get(0).url)
                        .error(R.drawable.article1)
                        .placeholder(R.drawable.article1)
                        .into(image);
            }
        }

        @Override
        public void onClick(View v) {
            listener.onItemClicked((Post) itemView.getTag());
        }
    }

}