package com.yehia.reda.mira_recycle_view_tools.lib

import android.content.Context
import android.util.LayoutDirection.LTR
import androidx.core.content.ContextCompat
import androidx.core.text.layoutDirection
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.yehia.reda.mira_recycle_view_tools.R
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.Skeleton
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.applySkeleton
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.createSkeleton
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.mask.SkeletonShimmerDirection
import com.yehia.reda.mira_recycle_view_tools.util.createView
import java.util.*

class empty {

    private lateinit var skeleton: Skeleton
    private var context1: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPager2: ViewPager2
    private var layout: Int

    //Config
    private var shimmerColor: Int
    private var maskColor: Int
    private var durationInMillis: Int
    private var countItem: Int = 0

    constructor(
        context: Context, layout: Int = R.layout.item_duf,
        shimmerColor: Int = R.color.skeleton_shimmer,
        maskColor: Int = R.color.skeleton_mask,
        duration: Int = 1000,
    ) {
        this.context1 = context
        this.layout = layout
        this.shimmerColor = shimmerColor
        this.maskColor = maskColor
        this.durationInMillis = duration
        setUp()
    }

    constructor(
        context: Context, recyclerView: RecyclerView, layout: Int = R.layout.item_duf,
        shimmerColor: Int = R.color.skeleton_shimmer,
        maskColor: Int = R.color.skeleton_mask,
        duration: Int = 1000,
        countItem: Int = 8
    ) {
        this.context1 = context
        this.layout = layout
        this.recyclerView = recyclerView
        this.shimmerColor = shimmerColor
        this.maskColor = maskColor
        this.durationInMillis = duration
        this.countItem = countItem
        setUp()

    }

    constructor(
        context: Context, viewPager2: ViewPager2, layout: Int = R.layout.item_duf,
        shimmerColor: Int = R.color.skeleton_shimmer,
        maskColor: Int = R.color.skeleton_mask,
        duration: Int = 1000,
        countItem: Int = 4
    ) {
        this.context1 = context
        this.layout = layout
        this.viewPager2 = viewPager2
        this.shimmerColor = shimmerColor
        this.maskColor = maskColor
        this.durationInMillis = duration
        this.countItem = countItem
        setUp()
    }

    private fun setUp() {
        setMiraRecycleViewSFlShimmer()
        showShimmer()
    }

    private fun setMiraRecycleViewSFlShimmer() {
        skeleton = when {
            ::viewPager2.isInitialized -> {
                viewPager2.applySkeleton(layout, countItem)
            }
            ::recyclerView.isInitialized -> {
                recyclerView.applySkeleton(layout, countItem)
            }
            else -> {
                val view = context1.createView(layout)
                view.childCount
                view.createSkeleton()
            }
        }

        skeleton.shimmerDirection = when (Locale.getDefault().layoutDirection) {
            LTR -> SkeletonShimmerDirection.LEFT_TO_RIGHT
            else -> SkeletonShimmerDirection.RIGHT_TO_LEFT
        }

        skeleton.shimmerColor = ContextCompat.getColor(context1, shimmerColor)
        skeleton.maskColor = ContextCompat.getColor(context1, maskColor)
        skeleton.shimmerDurationInMillis = durationInMillis.toLong()
    }

    fun showShimmer() {
        skeleton.showSkeleton()
    }

    fun hiddenShimmer() {
        skeleton.showOriginal();
    }
}