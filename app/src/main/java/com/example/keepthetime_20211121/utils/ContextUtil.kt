package com.example.keepthetime_20211121.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefNeme = "KeepTheTimePref"

        private val TOKEN = "TOKEN"

        fun setToken( context: Context,token : String)  {

            val pref = context.getSharedPreferences(prefNeme,Context.MODE_PRIVATE)

            pref.edit().putString(TOKEN,token).apply()


        }


        fun  getToken(context: Context) : String{

            val pref = context.getSharedPreferences(prefNeme,Context.MODE_PRIVATE)
            return pref.getString(TOKEN,"")!!

        }


    }

}