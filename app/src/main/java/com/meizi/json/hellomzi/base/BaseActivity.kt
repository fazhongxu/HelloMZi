package com.meizi.json.hellomzi.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.meizi.json.hellomzi.MApplication

/**
 * Created by xxl on 2017/6/7.
 * BaseActivity
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MApplication.registerActivity(this)
    }
}
