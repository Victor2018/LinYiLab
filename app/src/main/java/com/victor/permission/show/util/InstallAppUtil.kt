package com.victor.permission.show.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import com.victor.permission.show.data.AppInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: InstallAppUtil
 * Author: Victor
 * Date: 2024/09/29 18:21
 * Description: 
 * -----------------------------------------------------------------
 */

object InstallAppUtil {
    const val TAG = "InstallAppUtil"
    fun getAllInstallApps(context: Context, callback: (List<AppInfo>) -> Unit) {
        val listOfAllResult = ArrayList<AppInfo>()

        CoroutineScope(Dispatchers.IO).launch {
            // 获取PackageManager对象
            val packageManager: PackageManager = context.packageManager
            // 获取设备上安装的应用列表
            val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            // 遍历应用列表，打印应用名称和包名
            for (appInfo in apps) {
                val item = AppInfo()
                item.name = packageManager.getApplicationLabel(appInfo).toString()
                item.icon = packageManager.getApplicationIcon(appInfo)
                item.packageName = appInfo.packageName

                listOfAllResult.add(item)
            }
            CoroutineScope(Dispatchers.Main).launch {
                callback.invoke(listOfAllResult)
                Log.e(TAG,"getAllInstallApps-count = ${listOfAllResult.size}")
            }
        }
    }
}