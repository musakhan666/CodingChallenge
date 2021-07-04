package com.android.designpattern.util;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
private int previousTotal = 0;
private boolean loading = true;
private int visibleThreshold;
private int firstVisibleItem, visibleItemCount, totalItemCount;
private RecyclerView.LayoutManager layoutManager;

public EndlessRecyclerOnScrollListener(RecyclerView.LayoutManager layoutManager, int visibleThreshold) {
this.layoutManager = layoutManager; this.visibleThreshold = visibleThreshold;
}
@Override
public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
super.onScrolled(recyclerView, dx, dy);

visibleItemCount = recyclerView.getChildCount();
totalItemCount = layoutManager.getItemCount();
firstVisibleItem = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();

if (loading) {
    if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
    }
}
if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
    onLoadMore();
    loading = true;
}
  }

public abstract void onLoadMore();}