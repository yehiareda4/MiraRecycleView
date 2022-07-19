package com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.mask

internal interface SkeletonMaskable {
    fun invalidate() = Unit
    fun start() = Unit
    fun stop() = Unit
}