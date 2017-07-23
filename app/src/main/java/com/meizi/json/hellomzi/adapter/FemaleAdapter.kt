package com.meizi.json.hellomzi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.meizi.json.hellomzi.R
import com.meizi.json.hellomzi.bean.GirlBean
import com.squareup.picasso.Picasso
import javax.security.auth.login.LoginException

/**
 * Created by xxl on 2017/6/8.
 */
class FemaleAdapter constructor(var context: Context) : RecyclerView.Adapter<FemaleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FemaleAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.femele_item_recyclerview, null, false))
    }
    var list : ArrayList<GirlBean> = ArrayList()

     fun listFillToAdapter(list : ArrayList<GirlBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val girlBean = list.get(position)
        Picasso.with(context)
                .load(girlBean.url)
                .fit()//充满整个imageView控件，imageView必须要有确定的宽和高，不能是wrap_content
                .into(holder?.pic)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var pic: ImageView? = null

        constructor(itemView: View?) : super(itemView) {
            pic = itemView?.findViewById(R.id.iv_picture) as ImageView
        }
    }

}