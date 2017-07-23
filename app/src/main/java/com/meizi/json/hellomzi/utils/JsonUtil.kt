package com.meizi.json.hellomzi.utils

import com.google.gson.Gson
import com.google.gson.JsonParser


/**
 * Created by xxl on 2017/6/10.
 * Json解析工具类d
 */
class JsonUtil constructor() {
    companion object {
        fun <T> json2List(json: String, clazz: Class<T>): MutableList<T> {
            val mutableList = mutableListOf<T>()
            var gson: Gson = Gson()
            var jsonParser: JsonParser = JsonParser()
            val jsonArray = jsonParser.parse(json).asJsonArray
//            for (jsonElement in jsonArray) {
//               var bean = gson.fromJson(jsonElement, clazz)
//                mutableList.add(bean)
//            }
            jsonArray.mapTo(mutableList) {
                gson.fromJson(it, clazz)
            }
            return mutableList
        }
    }
}