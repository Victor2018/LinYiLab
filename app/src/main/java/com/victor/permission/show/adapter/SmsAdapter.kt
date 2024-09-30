package com.victor.permission.show.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.victor.permission.show.R
import com.victor.permission.show.data.SmsInfo
import com.victor.permission.show.holder.SmsContentHolder

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SmsAdapter
 * Author: Victor
 * Date: 2024/09/29 16:12
 * Description: 
 * -----------------------------------------------------------------
 */

class SmsAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<SmsInfo, RecyclerView.ViewHolder>(context, listener) {

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: SmsInfo?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SmsContentHolder(inflate(R.layout.rv_sms_cell, parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: SmsInfo?, position: Int) {
        val contentViewHolder = viewHolder as SmsContentHolder
        contentViewHolder.mOnItemClickListener = listener
        contentViewHolder.bindData(data)
    }
}