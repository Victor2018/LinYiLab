package com.victor.permission.show.util

import android.content.Context
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import com.victor.permission.show.data.ContactInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ContactUtil
 * Author: Victor
 * Date: 2024/09/29 18:21
 * Description: 
 * -----------------------------------------------------------------
 */

object ContactUtil {
    const val TAG = "AlbumUtil"
    fun getAllContacts(context: Context, callback: (List<ContactInfo>) -> Unit) {
        val listOfAllContacts = ArrayList<ContactInfo>()
        CoroutineScope(Dispatchers.IO).launch {
            val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
            val cursor = context.contentResolver.query(uri, projection, null, null, null)

            var phoneIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) ?: 0
            val nameIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME) ?: 0
            while (cursor?.moveToNext() == true) {
                val name = cursor.getString(nameIndex)
                var phone = cursor.getString(phoneIndex)
                Log.e(TAG,"getAllContacts-name = $name")
                Log.e(TAG,"getAllContacts-phone = $phone")

                val item = ContactInfo()
                item.name = name
                item.phone = phone
                listOfAllContacts.add(item)
            }
            cursor?.close()

            CoroutineScope(Dispatchers.Main).launch {
                callback.invoke(listOfAllContacts)
                Log.e(TAG,"getAllContacts-count = ${listOfAllContacts.size}")
            }
        }
    }
}