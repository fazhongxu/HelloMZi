package com.meizi.json.hellomzi.fragment

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.adapter.FemaleAdapter
import com.meizi.json.hellomzi.base.BaseFragment
import com.meizi.json.hellomzi.bean.GirlBean
import com.meizi.json.hellomzi.utils.JsonUtil
import com.meizi.json.hellomzi.view.AutoLoadMoreRecyclerView
import kotlinx.android.synthetic.main.fragment_female.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.logging.Handler

/**
 * Created by xxl on 2017/6/7.
 * FemaleFragment
 */
open class FemaleFragment private constructor() : BaseFragment() {
    //在类名后面加 private constructor() 私有构造方法

    val list: ArrayList<GirlBean> = ArrayList()
    var index: Int = 2000

    override fun getLayoutId(): Int {
        return R.layout.fragment_female
    }

    override fun initData() {
        getData(index)
        //匿名内部类的使用，object : 内部类
        swipeRefreshLayout?.setColorSchemeColors(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple
        )
        swipeRefreshLayout?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                index = 2000
                getData(index)
                swipeRefreshLayout.isRefreshing = false
            }
        })
        recyclerView.setOnLoadMoreListener(object : AutoLoadMoreRecyclerView.AutoLoadMoreListener {
            override fun loadMore() {
//                index += 20
//                Toast.makeText(context, "正在加载更多...", Toast.LENGTH_SHORT).show()
//                getData(index)
            }
        })
        recyclerView?.layoutManager = GridLayoutManager(context, 2)

    }

    private fun getData(index: Int) {
        var okHttp: OkHttpClient = OkHttpClient()
        var request: Request? = Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + index + "" + "/1")
                .get()
                .build()
        val call = okHttp.newCall(request)
        call.enqueue(object : Callback {
            //匿名内部类的使用，用object+参数
            override fun onFailure(call: Call?, e: IOException?) {
            }

            override fun onResponse(call: Call?, response: Response?) {
                var jsonObject: JSONObject = JSONObject(response?.body()?.string())
                if ("false".equals(jsonObject.optString("error"))) {
                    val results = jsonObject.optString("results")
                    val mutList = JsonUtil.json2List(results, GirlBean::class.java)
                    if (index == 20) {
                        list.clear()
                    }
                    list.addAll(mutList)
                    activity.runOnUiThread {
//                        recyclerView.adapter = FemaleAdapter(context = context).listFillToAdapter(list)
                        val femaleAdapter = FemaleAdapter(context)
                        recyclerView?.adapter = femaleAdapter
                        femaleAdapter.listFillToAdapter(list)
//                        recyclerView.adapter.notifyDataSetChanged()
                    }
                }
                AutoLoadMoreRecyclerView.LoadMoreListener.isLoading = false
            }
        })
    }

    /**
     * 用companion object包裹效果相当于Java中的static kotlin中没有static
     */
    companion object {
        fun newInstance(): FemaleFragment {
            return Singleton.femaleFragment//通过内部类调用成员
        }
    }

    /**
     * 用内部类的方式来创建单例模式，让jvm加载内部类的互斥机制来保证线程安全
     */
    private object Singleton {
        val femaleFragment = FemaleFragment()  //直接实例化对象
    }
}