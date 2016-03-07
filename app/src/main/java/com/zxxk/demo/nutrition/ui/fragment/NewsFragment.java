package com.zxxk.demo.nutrition.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zxxk.demo.nutrition.NutritionApp;
import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.respository.NetRespository;
import com.zxxk.demo.nutrition.ui.adapter.NewsAdapter;
import com.zxxk.demo.nutrition.ui.widget.LoadMoreRecyclerView;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 17:32
 * Description:
 */
public class NewsFragment extends BaseFragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView recyclerView;
    private String mTabId;
    private String mLastNewsId;
    private NewsAdapter mAdapter;

    private boolean isDataLoaded;
    public static final String TAG = NewsFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mAdapter = new NewsAdapter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_swipe_refresh_layout);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        recyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.setLoadingMore(true);
                loadMore();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTabId = getThemeId();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (!isDataLoaded) {
                    swipeRefreshLayout.setRefreshing(true);
                    refresh();
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void refresh() {
        isDataLoaded = false;
        NutritionApp.getNetRespository().getTabDataByType(mTabId, new NetRespository.CallBack<Tab>() {
            @Override
            public void success(Tab tab, String url) {
                isDataLoaded = true;
                swipeRefreshLayout.setRefreshing(false);
                if (tab != null && mAdapter != null) {
                    if (tab.getResult().getArticles().size() > 0){
                        mLastNewsId = tab.getResult().getArticles().get(tab.getResult().getArticles()
                                .size() - 1).getId();
                    }
                    mAdapter.setTab(tab);
                }
            }

            @Override
            public void failure(Exception e, String url) {

                isDataLoaded = false;
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void loadMore(){
        NutritionApp.getNetRespository().getMoreTabDataById(mTabId, mLastNewsId, new NetRespository.CallBack<Tab>() {
            @Override
            public void success(Tab tab, String url) {
                recyclerView.setLoadingMore(false);
                if (tab !=null && mAdapter !=null){
                    if (tab.getResult().getArticles().size() > 0){
                        mLastNewsId = tab.getResult().getArticles().get(tab.getResult()
                                .getArticles().size() -1).getId();
                        mAdapter.appendNews(tab.getResult().getArticles());
                    }
                }
            }

            @Override
            public void failure(Exception e, String url) {
                recyclerView.setLoadingMore(false);
                e.printStackTrace();
                Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}