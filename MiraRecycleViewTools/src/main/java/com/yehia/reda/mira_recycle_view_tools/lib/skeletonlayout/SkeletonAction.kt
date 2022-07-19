package com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout

interface SkeletonAction {

    /**
     * Displays the original layout and hides the skeleton
     */
    fun showOriginal()

    /**
     * Displays the skeleton and hides the original layout
     */
    fun showSkeleton()

    /**
     * @return True if the original layout is hidden by the skeleton
     */
    fun isSkeleton(): Boolean
}