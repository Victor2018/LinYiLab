package com.victor.permission.show.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatDelegate
import java.lang.ref.WeakReference

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: App
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

class App : Application() {
    val TAG = "App"

    companion object {
        private lateinit var instance: App
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //关闭黑夜模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}