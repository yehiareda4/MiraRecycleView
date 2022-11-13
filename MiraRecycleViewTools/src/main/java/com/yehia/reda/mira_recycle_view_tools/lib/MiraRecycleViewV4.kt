package com.yehia.reda.mira_recycle_view_tools.lib

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yehia.reda.mira_recycle_view_tools.R
import com.yehia.reda.mira_recycle_view_tools.databinding.LayoutMiraProgressBinding
import com.yehia.reda.mira_recycle_view_tools.databinding.LayoutMiraRecycleViewV4Binding
import com.yehia.reda.mira_recycle_view_tools.util.*
import com.yehia.reda.mira_recycle_view_tools.util.Constant.HIDDEN_PROGRESS
import com.yehia.reda.mira_recycle_view_tools.util.Constant.LOADING_MORE_DATA
import com.yehia.reda.mira_recycle_view_tools.util.Constant.NOT_REVERSED
import com.yehia.reda.mira_recycle_view_tools.util.Constant.NO_MORE_DATA
import com.yehia.reda.mira_recycle_view_tools.util.Constant.REVERSED

class MiraRecycleViewV4 : RelativeLayout {

    private var context1: Context

    private var _binding: LayoutMiraRecycleViewV4Binding? = null
    val binding get() = _binding!!

    private var refreshing = true
    lateinit var onEndLess: OnEndLessx
    lateinit var callBack: CallBack

    private lateinit var manger: RecyclerView.LayoutManager
    private lateinit var miraShimmerCreator: MiraShimmerCreator
    private lateinit var miraErrorView: MiraErrorViewCreator
    private lateinit var progressLayout: LayoutMiraProgressBinding
    private var shimmerLayout: Int = 0
    private var shimmerColor: Int = R.color.skeleton_shimmer
    private var maskColor: Int = R.color.skeleton_mask
    private var duration: Int = 1000
    private var countItem: Int = 8
    private var progressTxtColor: Int = R.color.txt_title
    private var font: Int = 0
    private var reversedLayout: String = NOT_REVERSED

    var currantPage = 1
    var maxPage = 0

    constructor(context: Context) : super(context) {
        this.context1 = context
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context1 = context
        initView(
            attrs,
            0
        )
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.context1 = context
        initView(
            attrs,
            defStyleAttr
        )
    }

    private fun initView() {
        if (_binding == null) {
            _binding = LayoutMiraRecycleViewV4Binding.inflate(
                LayoutInflater.from(context),
                parent as ViewGroup?,
                false
            )
        }
        addView(binding.root)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun initView(attrs: AttributeSet, defStyleAttr: Int) {
        val inflater = LayoutInflater.from(context1)
        if (_binding == null) {
            _binding = LayoutMiraRecycleViewV4Binding.inflate(
                inflater, parent as ViewGroup?, false
            )
        }
        setAttributes(attrs, defStyleAttr)

        addView(_binding!!.root)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun setAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.MiraRecycleViewV4, defStyleAttr, 0
        )

        val attrsEnabled =
            typedArray.getBoolean(R.styleable.MiraRecycleViewV4_mira_attrs_enabled, false)

        if (attrsEnabled) {
            shimmerLayout =
                typedArray.getResourceId(R.styleable.MiraRecycleViewV4_mira_shimmer_layout, 0)
            shimmerColor = typedArray.getResourceId(
                R.styleable.MiraRecycleViewV4_mira_shimmerColor,
                R.color.skeleton_shimmer
            )
            maskColor = typedArray.getResourceId(
                R.styleable.MiraRecycleViewV4_mira_maskColor,
                R.color.skeleton_mask
            )
            duration = typedArray.getResourceId(R.styleable.MiraRecycleViewV4_mira_duration, 1000)
            countItem = typedArray.getResourceId(R.styleable.MiraRecycleViewV4_mira_countItem, 8)

            val visibility =
                typedArray.getInt(
                    R.styleable.MiraRecycleViewV4_mira_toggle_show_shimmer,
                    View.VISIBLE
                )
            this.visibility = when (visibility) {
                0 -> {
                    View.VISIBLE
                }

                else -> {
                    View.GONE
                }
            }
            refreshing = typedArray.getBoolean(R.styleable.MiraRecycleViewV4_mira_refreshing, true)

            miraErrorView =
                MiraErrorViewCreator(_binding!!.root.context!!, attrs, binding.root as ViewGroup)

            progressTxtColor = typedArray.getResourceId(
                R.styleable.MiraRecycleViewV4_mira_progress_txt_color,
                R.color.txt_title
            )
            font = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_font, R.font.urw_din_bold
            )
            val reversedType = typedArray.getInteger(
                R.styleable.MiraRecycleViewV4_mira_reversed_layout, 1
            )
            reversedLayout = when (reversedType) {
                0 -> {
                    progressLayout = binding.lyProgTop
                    REVERSED
                }
                else -> {
                    progressLayout = binding.lyProgBottom
                    NOT_REVERSED
                }
            }

            binding.srlRefresh.isEnabled = refreshing

            val mangerKey =
                typedArray.getInt(R.styleable.MiraRecycleViewV4_mira_layout_manger, 3)
            val spanCount =
                typedArray.getInt(R.styleable.MiraRecycleViewV4_mira_span_count, 2)

            binding.srlRefresh.isEnabled = this.refreshing
            if (mangerKey != 3) {
                manger = when (mangerKey) {
                    0 -> LinearLayoutManager(context)
                    1 -> GridLayoutManager(context, spanCount)
                    else -> StaggeredGridLayoutManager(spanCount, LinearLayout.VERTICAL)
                }
                setUpMiraRecycleView(manger)

                if (shimmerLayout != 0) {
                    setsFlShimmer(
                        shimmerLayout
                    )
                    toggleShimmerLoading(visibility)
                }
            }
        }
    }

    private fun setsFlShimmer(shimmerLayout: Int) {
        miraShimmerCreator = MiraShimmerCreator(
            context1, binding.rvList, shimmerLayout,
            shimmerColor, maskColor, duration, countItem,
        )
    }

    fun setUp(
        manger: RecyclerView.LayoutManager,
        shimmerLayout: Int,
        callBack: CallBack,
        refreshing: Boolean = true
    ) {
        this.refreshing = refreshing
        binding.srlRefresh.isEnabled = this.refreshing
        this.callBack = callBack
        this.manger = manger
        setUpMiraRecycleView(manger)

        miraErrorView.onErrorBtnClick { callBack.onErrorClick() }
        this.callBack.onInit()
        if (shimmerLayout != 0) {
            setsFlShimmer(
                shimmerLayout
            )
            toggleShimmerLoading(visibility)
        }
    }

    fun setUp(
        manger: RecyclerView.LayoutManager,
        callBack: CallBack
    ) {
        binding.srlRefresh.isEnabled = this.refreshing
        this.manger = manger
        this.callBack = callBack
        setUpMiraRecycleView(manger)
        miraErrorView.onErrorBtnClick { callBack.onErrorClick() }
        this.callBack.onInit()

        if (shimmerLayout != 0) {
            setsFlShimmer(
                shimmerLayout
            )
            toggleShimmerLoading(visibility)
        }
    }

    fun setUp(callBack: CallBack) {
        binding.srlRefresh.isEnabled = this.refreshing
        this.callBack = callBack
        setUpMiraRecycleView(manger)
        miraErrorView.onErrorBtnClick { callBack.onErrorClick() }
        this.callBack.onInit()

        if (shimmerLayout != 0) {
            setsFlShimmer(
                shimmerLayout
            )
            toggleShimmerLoading(visibility)
        }
    }

    fun toggleShimmerLoading(visibility: Int) {
        if (::miraShimmerCreator.isInitialized) {
            if (visibility == VISIBLE) {
                miraShimmerCreator.showShimmer()
            } else {
                miraShimmerCreator.hiddenShimmer()
            }
        }
    }

    private fun setUpMiraRecycleView(manger: RecyclerView.LayoutManager) {
        binding.rvList.layoutManager = manger
        when (manger) {
            is GridLayoutManager -> setPagination(manger as GridLayoutManager)
            is StaggeredGridLayoutManager -> setPagination(manger as StaggeredGridLayoutManager)
            else -> setPagination(manger as LinearLayoutManager)
        }
        binding.srlRefresh.setOnRefreshListener {
            onRefresh()
        }
    }

    fun onRefresh() {
        callBack.onReset()
        reset()
        stopLoading()
        progressLayout.setProgress(HIDDEN_PROGRESS)
        binding.srlRefresh.isRefreshing = true
        toggleShimmerLoading(VISIBLE)
        callBack.onRefresh()
    }

    private fun reset() {
        maxPage = 0
        when (manger) {
            is GridLayoutManager -> setPagination(manger as GridLayoutManager)
            is StaggeredGridLayoutManager -> setPagination(manger as StaggeredGridLayoutManager)
            else -> setPagination(manger as LinearLayoutManager)
        }
    }

    private fun setPagination(manger: LinearLayoutManager) {
        onEndLess = object : OnEndLessx(manger) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNewPage(page)
            }
        }
        binding.rvList.addOnScrollListener(onEndLess)
    }

    private fun setPagination(manger: GridLayoutManager) {
        onEndLess = object : OnEndLessx(manger) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNewPage(page)
            }
        }
        binding.rvList.addOnScrollListener(onEndLess)
    }

    private fun setPagination(manger: StaggeredGridLayoutManager) {
        onEndLess = object : OnEndLessx(manger) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNewPage(page)
            }
        }
        binding.rvList.addOnScrollListener(onEndLess)
    }

    private fun loadNewPage(current_page: Int) {
        if (current_page <= maxPage) {
            if (maxPage != 0 && current_page != 1) {
                currantPage = current_page
                stopLoading(currantPage)
                progressLayout.setProgress(LOADING_MORE_DATA, font, progressTxtColor)
                callBack.onLoadMore(currantPage)
            } else {
                toggleShimmerLoading(GONE)
            }
        } else {
            currantPage = current_page
            if (maxPage != 0) {
                progressLayout.setProgress(NO_MORE_DATA, font, progressTxtColor)
            }
        }
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        binding.rvList.adapter = adapter
    }

    fun stopLoading(page: Int = 0) {
        if (page > maxPage) {
            progressLayout.setProgress(HIDDEN_PROGRESS)
        } else {
            if (page != 0) {
                progressLayout.setProgress(NO_MORE_DATA, font, progressTxtColor)
            } else {
                binding.srlRefresh.isRefreshing = false
                progressLayout.setProgress(HIDDEN_PROGRESS)
            }
        }
        resetPresses()
    }

    private fun resetPresses() {
        toggleShimmerLoading(GONE)
        progressLayout.setProgress(HIDDEN_PROGRESS)
        toggleShowError(GONE)
        binding.srlRefresh.isRefreshing = false
    }

    fun toggleShowError(visibility: Int) {
        miraErrorView.toggleShowError(visibility)
    }

    fun hiddenProgress() {
        progressLayout.setProgress(HIDDEN_PROGRESS)
    }

    fun changeData(
        errorImage: Int = R.drawable.ic_no_connection_vector,
        errorImageTypeTxt: String = Constant.OTHER,
        errorTitle: String = "",
        errorMessage: String = "",
        actionText: String = "",
    ) {
        miraErrorView.changeData(
            errorImage,
            errorImageTypeTxt,
            errorTitle,
            errorMessage,
            actionText
        )
    }

    fun returnToStartData() {
        miraErrorView.returnToStartData()
    }

}