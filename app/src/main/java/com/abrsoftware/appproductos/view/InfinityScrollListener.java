package com.abrsoftware.appproductos.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.abrsoftware.appproductos.products.DataLoading;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by AbrWin on 18/10/16.
 */

public abstract class InfinityScrollListener extends RecyclerView.OnScrollListener {
    private static final int VISIBLE_THRESHOLD = 5;
    private final LinearLayoutManager mLinearLayoutManager;
    private final DataLoading mDataLoading;

    public InfinityScrollListener(DataLoading dataLoading, LinearLayoutManager linearLayoutManager){
        mDataLoading = checkNotNull(dataLoading);
        mLinearLayoutManager = checkNotNull(linearLayoutManager);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if(dy < 0 || mDataLoading.isLoadingData() || !mDataLoading.isThereMoreData())
            return;

        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = mLinearLayoutManager.getItemCount();
        final int firstVisibleItem = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();

        if((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)){
            onLoadMore();
        }
    }

    public abstract void onLoadMore();
}
