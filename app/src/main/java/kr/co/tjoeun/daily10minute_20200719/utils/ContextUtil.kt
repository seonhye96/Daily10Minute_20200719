package kr.co.tjoeun.daily10minute_20200719.utils

import android.content.Context

class ContextUtil { // new > kotlin file/class

    companion object{

        private val prefName = "daily10minutePref"  // 이 파일로 저장할거야!!

        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        fun setLoginUserToken(context: Context, token:String){ //Context로 메모장 열고 token로 저장해라
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE) // prefName 메모장 좀 열여줘
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getLoginUserToken(context: Context) : String {  //Context로 저장한 메모장 열어줘라
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

    }

}