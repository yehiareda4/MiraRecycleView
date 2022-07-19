package com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.mask

import android.view.View
import com.yehia.reda.mira_recycle_view_tools.lib.skeletonlayout.SkeletonConfig

internal object SkeletonMaskFactory {

    fun createMask(
        view: View,
        config: SkeletonConfig
    ): SkeletonMask {
        return when (config.showShimmer) {
            true -> SkeletonMaskShimmer(
                view,
                config.maskColor,
                config.shimmerColor,
                config.shimmerDurationInMillis,
                config.shimmerDirection,
                config.shimmerAngle
            )
            false -> SkeletonMaskSolid(view, config.maskColor)
        }
    }
}