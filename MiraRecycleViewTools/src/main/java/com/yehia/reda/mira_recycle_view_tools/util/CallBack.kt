package com.yehia.reda.mira_recycle_view_tools.util

/*
 * Yehia Reda
 * */
interface CallBack {
    fun onLoadMore(current_page: Int) {}
    fun onRefresh() {}
    abstract fun onReset()
    abstract fun onInit()
    fun onErrorClick() {}
}