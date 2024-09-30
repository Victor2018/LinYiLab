package com.victor.permission.show.holder

import android.view.View
import com.victor.permission.show.data.SmsInfo
import kotlinx.android.synthetic.main.rv_sms_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SmsContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class SmsContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: SmsInfo?) {
        itemView.mTvAddress.text = data?.address ?: ""
        itemView.mTvBody.text = data?.body ?: ""
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}