package com.meizi.json.hellomzi.fragment

import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.base.BaseFragment

/**
 * Created by xxl on 2017/6/7.
 * NewsPaperFragment
 */
class NewsPaperFragment private constructor(): BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_newspaper
    }

    override fun initData() {
    }
    companion object {
        fun newInstance() : NewsPaperFragment {
            return Singleton.newsPaperFragment
        }
    }
    private object Singleton{
        val newsPaperFragment = NewsPaperFragment()
    }
}