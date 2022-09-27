package com.yehia.reda.mira_recycle_view_tools.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class OnEndLess(layoutManager: Any, visibleThreshold: Int) :
    RecyclerView.OnScrollListener() {

    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold // The minimum amount of items to have below your current scroll position before loading more.
            : Int
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var previousTotal = 0 // The total number of items in the dataset after the last load
    var current_page = 1
    var previous_page = 1
    private val mLinearLayoutManager: LinearLayoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount
            <= firstVisibleItem + visibleThreshold
        ) {
            // End has been reached
            // Do something
            current_page++
            onLoadMore(current_page)
            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

    init {
        mLinearLayoutManager = layoutManager as LinearLayoutManager
        this.visibleThreshold = visibleThreshold
    }
}