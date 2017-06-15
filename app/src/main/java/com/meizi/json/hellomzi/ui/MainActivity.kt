package com.meizi.json.hellomzi.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.base.BaseActivity
import com.meizi.json.hellomzi.fragment.FemaleFragment
import com.meizi.json.hellomzi.fragment.MineFragment
import com.meizi.json.hellomzi.fragment.NewsPaperFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),BottomNavigationBar.OnTabSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationBar()
    }

    /**
     * initNavigationBar
     */
    private fun initNavigationBar() {
                navigation_bar.addItem(BottomNavigationItem(R.drawable.ic_guest_female_white_24dp,resources.getString(R.string.navigation_female)))
                .addItem(BottomNavigationItem(R.drawable.ic_newspaper_white_24dp,resources.getString(R.string.navigation_newsPaper)))
                .addItem(BottomNavigationItem(R.drawable.ic_person_outline_white_24dp,getString(R.string.navigation_person)))
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.colorGray)
                .initialise()

        setDefaultFragment()//setting defultFragment

        navigation_bar.setTabSelectedListener(this)
    }
    private fun getFragments() : ArrayList<Fragment> {
       var fragments : ArrayList<Fragment> = ArrayList()
        fragments.add(FemaleFragment.newInstance())
        fragments.add(NewsPaperFragment.newInstance())
        fragments.add(MineFragment.newInstance())
        return fragments
    }
    private fun setDefaultFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container,FemaleFragment.newInstance()).commit()
    }

    override fun onTabSelected(position: Int) {
        val fragments = getFragments()
            if(position < fragments.size) {
                val transaction = supportFragmentManager.beginTransaction()//注意如果fragment是V4,此处就应该为support
                val fragment: Fragment = fragments[position]
                if (fragment.isAdded) {
                    transaction.replace(R.id.fl_container, fragment)
                } else {
                    transaction.add(R.id.fl_container, fragment)
                }
                transaction.commit()
        }
    }

    override fun onTabReselected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragments = getFragments()
        if (position < fragments.size) {
            transaction.remove(fragments[position])
        }
        transaction.commit()
    }

}
