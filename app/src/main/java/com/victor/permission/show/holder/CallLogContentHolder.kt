package com.victor.permission.show.holder

import android.view.View
import com.victor.permission.show.data.CallLogInfo
import kotlinx.android.synthetic.main.rv_call_log_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CallLogContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class CallLogContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: CallLogInfo?) {
        itemView.mTvName.text = data?.name ?: ""
        itemView.mTvPhone.text = data?.phone ?: ""
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}