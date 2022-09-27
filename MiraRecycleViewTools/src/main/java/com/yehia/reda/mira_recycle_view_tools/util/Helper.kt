package com.yehia.reda.mira_recycle_view_tools.util

import android.content.Context
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.yehia.reda.mira_recycle_view_tools.R
import com.yehia.reda.mira_recycle_view_tools.databinding.LayoutMiraProgressBinding
import com.yehia.reda.mira_recycle_view_tools.util.Constant.LOADING_MORE_DATA
import com.yehia.reda.mira_recycle_view_tools.util.Constant.NO_MORE_DATA

fun Context.createView(shimmerLayout: Int): ViewGroup {
    val view = RelativeLayout.inflate(this, shimmerLayout, null)
    view.layoutParams = LinearLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT,
        1f
    )
    return view as ViewGroup
}

fun TextView.setTxtColor(colorId: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, colorId))
}

fun Button.setTxtColor(colorId: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, colorId))
}

fun LayoutMiraProgressBinding.setProgress(
    type: String = LOADING_MORE_DATA,
    font: Int = R.font.urw_din_bold,
    txtColor: Int = R.color.txt_title
) {
    val typeface: Typeface = ResourcesCompat.getFont(this.root.context!!, font)!!

    this.tvLoadingMorData.typeface = typeface
    this.tvNoMoreData.typeface = typeface
    this.tvLoadingMorData.setTxtColor(txtColor)
    this.tvNoMoreData.setTxtColor(txtColor)

    this.llLoadingMorData.isVisible = false
    this.llNoMoreData.isVisible = false

    when (type) {
        LOADING_MORE_DATA -> {
            this.llLoadingMorData.isVisible = true
        }
        NO_MORE_DATA -> {
            Handler(Looper.getMainLooper()).postDelayed({
                this.llNoMoreData.isGone = true
            }, 3000)
            this.llNoMoreData.isVisible = true
        }
    }
}


