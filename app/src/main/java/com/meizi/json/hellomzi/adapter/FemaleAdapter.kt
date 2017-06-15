package com.meizi.json.hellomzi.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.meizi.json.hellomzi.R

/**
 * Created by xxl on 2017/6/8.
 */
class FemaleAdapter constructor(var list:ArrayList<String>): RecyclerView.Adapter<FemaleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FemaleAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.femele_item_recyclerview,null,false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.text
    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder : RecyclerView.ViewHolder{
        var name : TextView ?= null

        constructor(itemView: View?) : super(itemView) {
            name = itemView?.findViewById(R.id.tv_text) as TextView
        }
    }

}