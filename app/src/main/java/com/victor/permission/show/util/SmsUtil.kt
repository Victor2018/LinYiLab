package com.victor.permission.show.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CallLog
import android.util.Log
import com.victor.permission.show.data.SmsInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SmsUtil
 * Author: Victor
 * Date: 2024/09/29 18:21
 * Description: 
 * -----------------------------------------------------------------
 */

object SmsUtil {
    const val TAG = "SmsUtil"
    fun getAllSms(context: Context, callback: (List<SmsInfo>) -> Unit) {
        try {
            val listOfAllResult = ArrayList<SmsInfo>()
            CoroutineScope(Dispatchers.IO).launch {
                val uri = Uri.parse("content://sms/inbox")
                val projection = arrayOf("_id", "address", "body", "date")
                val sortOrder = "date DESC"

                val cursor = context.contentResolver.query(uri, projection, null, null, sortOrder)

                while (cursor?.moveToNext() == true) {
                    val address = cursor.getString(cursor.getColumnIndex("address") ?: 0)
                    val body = cursor.getString(cursor.getColumnIndex("body") ?: 0)
                    val date = cursor.getLong(cursor.getColumnIndex("date") ?: 0) ?: 0

                    val item = SmsInfo()
                    item.address = address
                    item.body = body
                    item.date = date
                    listOfAllResult.add(item)
                }
                cursor?.close()

                CoroutineScope(Dispatchers.Main).launch {
                    callback.invoke(listOfAllResult)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}