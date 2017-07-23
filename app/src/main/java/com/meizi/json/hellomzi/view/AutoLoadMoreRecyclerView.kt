package com.meizi.json.hellomzi.view

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import java.util.jar.Attributes

/**
 * Created by xxl on 2017/7/22.
 */
class AutoLoadMoreRecyclerView : RecyclerView {

    object LoadMoreListener {
        var autoLoadMoreListener: AutoLoadMoreListener? = null
        var isLoading : Boolean = false
    }

    /**
     * 提供给外部调用的方法
     */
    fun setOnLoadMoreListener(autoLoadMoreListener: AutoLoadMoreListener) {
        LoadMoreListener.autoLoadMoreListener = autoLoadMoreListener
    }
    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        //添加滚动监听
        addOnScrollListener(AutoLoadScroller())
    }

    /**
     * 提供给外部调用的接口
     */
    interface AutoLoadMoreListener {
        fun loadMore(): Unit
    }

    /**
     * 重写RecycelerView的OnScrollListener
     */
    private class AutoLoadScroller : OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            //判断是否是linearLayout
            if (recyclerView?.layoutManager is GridLayoutManager) {
                val lastVisibleItemPosition =
                        (recyclerView?.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                if (LoadMoreListener.autoLoadMoreListener != null && !LoadMoreListener.isLoading &&
                        lastVisibleItemPosition == recyclerView.adapter.itemCount - 1 && dy > 0) {
                    LoadMoreListener.autoLoadMoreListener?.loadMore()
                    LoadMoreListener.isLoading = true

                }
            }
        }
    }
}
