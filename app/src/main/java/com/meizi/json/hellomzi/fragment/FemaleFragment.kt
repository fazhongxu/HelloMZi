package com.meizi.json.hellomzi.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.base.BaseFragment
import com.meizi.json.hellomzi.bean.GirlBean
import kotlinx.android.synthetic.main.fragment_female.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Created by xxl on 2017/6/7.
 * FemaleFragment
 */
open class FemaleFragment private constructor() : BaseFragment() {
    //在类名后面加 private constructor() 私有构造方法
    override fun getLayoutId(): Int {
        return R.layout.fragment_female
    }

    override fun initData() {
        val view = LayoutInflater.from(context).inflate(R.layout.female_recyclerview, null, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView //as为强制类型转换
        var list: ArrayList<String> = ArrayList()
//        for (item in list) {
//        }
        var okHttp: OkHttpClient = OkHttpClient()
        var request: Request? = Request.Builder().url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1").get().build()

        val call = okHttp.newCall(request)
        call.enqueue(object : Callback {
            //匿名内部类的使用，用object+参数
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                var jsonObject: JSONObject = JSONObject(response?.body()?.string())
                if ("false".equals(jsonObject.optString("error"))) {
                    val results = jsonObject.optString("results")

//                    Gson().fromJson(results.toString(), GirlBean::class.java)
                    var list: ArrayList<GirlBean> = ArrayList()
                    var jsonParser: JsonParser = JsonParser()
                    val parse = jsonParser.parse(results.toString())
                    val asJsonArray = parse.asJsonArray
                    val gson = Gson()
                    for (a in asJsonArray) {
                        val asJsonObject = a.asJsonObject
                        val fromJson = gson.fromJson(asJsonObject, GirlBean::class.java)
                        list.add(fromJson)
                    }
                    println(list.size)
                }
            }

        })

//        recyclerView.adapter = FemaleAdapter()
        fl_female_container.addView(view)

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