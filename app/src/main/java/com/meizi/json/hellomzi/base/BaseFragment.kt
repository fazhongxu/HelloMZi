package com.meizi.json.hellomzi.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by xxl on 2017/6/7.
 * BaseFragment
 */
open abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = getLayoutId()
        val view = LayoutInflater.from(context).inflate(layoutId, null, false)
//        initData()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /**
     * getLayoutId
     */
    protected abstract fun getLayoutId():Int

    protected abstract fun initData()

}