package com.meizi.json.hellomzi

import android.app.Activity
import android.app.Application

/**
 * Created by xxl on 2017/6/8.
 */
class MApplication : Application() {
    object list {
        //用object修饰，相当于Java中的static
        var list: ArrayList<Activity>? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
    companion object {
        /**
         * 添加Activity到全局集合中，方便一键退出
         */
        fun registerActivity(activity: Activity) {
            list.list?.add(activity)
        }

        /**
         * 退出activity
         */
        fun exitActivity() {
            list.list?.clear()//清空集合
            System.exit(0)//退出虚拟机
        }
    }
}

