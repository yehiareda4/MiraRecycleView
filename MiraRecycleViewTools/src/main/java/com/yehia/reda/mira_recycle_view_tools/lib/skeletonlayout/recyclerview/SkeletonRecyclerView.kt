package com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.recyclerview

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.Skeleton
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.SkeletonConfig
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.SkeletonStyle

@SuppressLint("NotifyDataSetChanged")
internal class SkeletonRecyclerView(
    private val recyclerView: RecyclerView,
    @LayoutRes layoutResId: Int,
    itemCount: Int,
    config: SkeletonConfig
) : Skeleton, SkeletonStyle by config {

    private val originalAdapter = recyclerView.adapter
    private var skeletonAdapter = SkeletonRecyclerViewAdapter(layoutResId, itemCount, config)

    init {
        config.addValueObserver { skeletonAdapter.notifyDataSetChanged() }
    }

    override fun showOriginal() {
        recyclerView.adapter = originalAdapter
    }

    override fun showSkeleton() {
        recyclerView.adapter = skeletonAdapter
    }

    override fun isSkeleton(): Boolean {
        return recyclerView.adapter == skeletonAdapter
    }
}