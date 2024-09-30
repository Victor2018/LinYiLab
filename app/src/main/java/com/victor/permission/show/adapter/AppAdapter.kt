package com.victor.permission.show.adapter

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.victor.permission.show.R
import com.victor.permission.show.data.AppInfo
import com.victor.permission.show.data.ContactInfo
import com.victor.permission.show.holder.AppContentHolder
import com.victor.permission.show.holder.ContactContentHolder

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AppAdapter
 * Author: Victor
 * Date: 2024/09/29 16:12
 * Description: 
 * -----------------------------------------------------------------
 */

class AppAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<AppInfo, RecyclerView.ViewHolder>(context, listener) {

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: AppInfo?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppContentHolder(inflate(R.layout.rv_app_cell, parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: AppInfo?, position: Int) {
        val contentViewHolder = viewHolder as AppContentHolder
        contentViewHolder.mOnItemClickListener = listener
        contentViewHolder.bindData(data)
    }
}