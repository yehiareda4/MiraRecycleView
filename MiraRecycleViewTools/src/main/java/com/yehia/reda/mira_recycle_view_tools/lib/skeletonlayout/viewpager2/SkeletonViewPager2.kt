package com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.viewpager2

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.viewpager2.widget.ViewPager2
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.Skeleton
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.SkeletonConfig
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.SkeletonStyle
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.recyclerview.SkeletonRecyclerViewAdapter

@SuppressLint("NotifyDataSetChanged")
internal class SkeletonViewPager2(
    private val viewPager: ViewPager2,
    @LayoutRes layoutResId: Int,
    itemCount: Int,
    config: SkeletonConfig
) : Skeleton, SkeletonStyle by config {

    private val originalAdapter = viewPager.adapter
    private var skeletonAdapter = SkeletonRecyclerViewAdapter(layoutResId, itemCount, config)

    init {
        config.addValueObserver { skeletonAdapter.notifyDataSetChanged() }
    }

    override fun showOriginal() {
        viewPager.adapter = originalAdapter
    }

    override fun showSkeleton() {
        viewPager.adapter = skeletonAdapter
    }

    override fun isSkeleton(): Boolean {
        return viewPager.adapter == skeletonAdapter
    }
}