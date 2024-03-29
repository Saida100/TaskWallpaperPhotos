package com.saida_aliyeva.taskwpphotos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {
    private final static int VISIBLE_THRESHOLD = 2;
    private StaggeredGridLayoutManager linearLayoutManager;
    private boolean loading; // LOAD MORE Progress dialog
    private OnLoadMoreListener listener;
    private boolean pauseListening = false;
    private boolean END_OF_FEED_ADDED = false;
    private int NUM_LOAD_ITEMS = 10;

    public InfiniteScrollListener(StaggeredGridLayoutManager linearLayoutManager, OnLoadMoreListener listener) {
        this.linearLayoutManager = linearLayoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dx == 0 && dy == 0)
            return;
        int totalItemCount = linearLayoutManager.getItemCount();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPositions(null)[0];
        if (!loading && totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD && totalItemCount != 0 && !END_OF_FEED_ADDED && !pauseListening) {
            if (listener != null) {
                listener.onLoadMore();
            }
            loading = true;
        }
    }

    public void setLoaded() {
        loading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void addEndOfRequests() {
        this.END_OF_FEED_ADDED = true;
    }

    public void pauseScrollListener(boolean pauseListening) {
        this.pauseListening = pauseListening;
    }
}
