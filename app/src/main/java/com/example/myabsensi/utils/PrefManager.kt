package com.example.myabsensi.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(var context: Context) {
    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "SharedPreferences"

    private val IS_LOGIN = "is_login"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setLoggin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setLogin(type:String,token: String?, id:Int) {
        editor?.putString("type", type)
        editor?.putString("token", token)
        editor?.putInt("id", id)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun setName(name: String?, email: String) {
        editor?.putString("name", name)
        editor?.putString("email", email)
        editor?.commit()
    }

    fun getType():String? {
        return pref?.getString("type", "")
    }

    fun getName():String? {
        return pref?.getString("name", "")
    }

    fun getEmail():String? {
        return pref?.getString("email", "")
    }

    fun setDashboard(user: String?, positions: String?, Holiday: String?) {
        editor?.putString("user", user)
        editor?.putString("positions", positions)
        editor?.putString("Holiday", Holiday)
        editor?.commit()
    }


    fun clear() {
        editor?.clear()
            ?.apply()
    }


//    fun clear (){
//        editor?.clear()
//        editor?.commit()
//    }

//    fun getToken(): String? {
//        return pref?.getString("token", "")
//    }
//
//    fun getId(): Int {
//        return pref?.getInt("id", 0)!!
//    }
//    fun removeData() {
//        editor?.clear()
//        editor?.commit()
//    }

}