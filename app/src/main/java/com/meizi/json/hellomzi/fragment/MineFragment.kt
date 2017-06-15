package com.meizi.json.hellomzi.fragment

import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.base.BaseFragment

/**
 * Created by xxl on 2017/6/7.
 * MineFragment
 */
class MineFragment private constructor(): BaseFragment(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }
    override fun initData() {
    }
    companion object {
        fun newInstance() : MineFragment {
            return Singleton.mineFragment
        }
    }
    private object Singleton{
        val mineFragment = MineFragment()
    }
}