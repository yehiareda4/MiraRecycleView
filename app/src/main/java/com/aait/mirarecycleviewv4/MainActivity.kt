package com.aait.mirarecycleviewv4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yehia.reda.mira_recycle_view_tools.lib.MiraRecycleViewV4
import com.yehia.reda.mira_recycle_view_tools.util.CallBack

class MainActivity : AppCompatActivity() {

    private lateinit var selectedAdapter: SelectedAdapter
    var error = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val list = findViewById<RecyclerView>(R.id.subView)
//        list.layoutManager = LinearLayoutManager(this)
//        list.adapter = SelectedAdapter()
////        var miraShimmerCreator =
////            MiraShimmerCreator(this, list, R.layout.item3)
//
//        list.applySkeleton(R.layout.item3, 6).apply { showSkeleton() }
        val mrvList = findViewById<MiraRecycleViewV4>(R.id.mrv_list)
        mrvList.setUp(LinearLayoutManager(this), object : CallBack {
            override fun onLoadMore(current_page: Int) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mrvList.stopLoading(current_page)
                    mrvList.hiddenProgress()
                    addData()
                }, 3000)
            }

            override fun onRefresh() {
                selectedAdapter.list.clear()
                selectedAdapter.toString()
                Handler(Looper.getMainLooper()).postDelayed({
                    mrvList.stopLoading()
                    if (!error) {
                        mrvList.maxPage = 4
                        addData()
                        error = true
                    } else {
                        mrvList.toggleShowError(VISIBLE)
                    }
                }, 3000)
            }

            override fun onReset() {
                selectedAdapter = SelectedAdapter()
                mrvList.setAdapter(selectedAdapter)
                mrvList.maxPage = 0
            }

            override fun onInit() {
                selectedAdapter = SelectedAdapter()
                mrvList.setAdapter(selectedAdapter)
                Handler(Looper.getMainLooper()).postDelayed({
                    mrvList.maxPage = 2
                    addData()
                    mrvList.stopLoading()
                }, 4000)
            }

            override fun onErrorClick() {
                error = false
                mrvList.onRefresh()
            }
        })
    }

    private fun addData() {
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.list.add(1)
        selectedAdapter.notifyDataSetChanged()
    }
}