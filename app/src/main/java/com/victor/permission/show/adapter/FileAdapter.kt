package com.victor.permission.show.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.victor.permission.show.R
import com.victor.permission.show.data.CallLogInfo
import com.victor.permission.show.holder.CallLogContentHolder
import com.victor.permission.show.holder.FileContentHolder
import java.io.File

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FileAdapter
 * Author: Victor
 * Date: 2024/09/29 16:12
 * Description: 
 * -----------------------------------------------------------------
 */

class FileAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<File, RecyclerView.ViewHolder>(context, listener) {

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: File?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FileContentHolder(inflate(R.layout.rv_file_cell, parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: File?, position: Int) {
        val contentViewHolder = viewHolder as FileContentHolder
        contentViewHolder.mOnItemClickListener = listener
        contentViewHolder.bindData(data)
    }
}