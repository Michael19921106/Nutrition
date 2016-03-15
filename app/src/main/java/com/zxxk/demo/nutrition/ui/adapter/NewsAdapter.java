package com.zxxk.demo.nutrition.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.entity.ArticlesEntity;
import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.ui.adapter.holder.NewsHolder;
import com.zxxk.demo.nutrition.utils.UIUtils;

import java.util.List;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-07
 * Time: 09:56
 * Description:
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Tab tab;

    public void setTab(Tab tab) {
        this.tab = tab;
        notifyDataSetChanged();
    }
    private CollectionStarListner starListner;
    public NewsAdapter() {

    }

    public NewsAdapter(CollectionStarListner starListner) {
        this.starListner = starListner;
    }

    public void appendNews(List<ArticlesEntity> mList) {
        tab.getResult().getArticles().addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        viewHolder = new NewsHolder(UIUtils.inflate(R.layout.card_view_news_item, parent));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsHolder newsHolder = (NewsHolder) holder;
        newsHolder.bindNewsView(tab.getResult().getArticles().get(position));
        ((NewsHolder) holder).starView.setStarred(false);
        ((NewsHolder) holder).starView.clearAnimation();
        ((NewsHolder) holder).starView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starListner.onStarClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (tab != null) {
            if (tab.getResult() != null) {
                if (tab.getResult().getArticles() != null) {
                    count = count + tab.getResult().getArticles().size();
                }
            }
        }
        return count;
    }

    public interface CollectionStarListner {
         void onStarClicked(int position);
    }
}