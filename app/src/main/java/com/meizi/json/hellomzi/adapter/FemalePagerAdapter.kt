package com.meizi.json.hellomzi.adapter

import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.View

/**
 * Created by xxl on 2017/6/8.
 */
class FemalePagerAdapter constructor(var list: ArrayList<Fragment>): PagerAdapter(){

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return true
    }

    override fun getCount(): Int {
       return list.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return super.getPageTitle(position)
    }
}