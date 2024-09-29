package com.victor.permission.show.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.victor.permission.show.R
import com.victor.permission.show.data.ContactInfo
import com.victor.permission.show.holder.ContactContentHolder

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ContactAdapter
 * Author: Victor
 * Date: 2024/09/29 16:12
 * Description: 
 * -----------------------------------------------------------------
 */

class ContactAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<ContactInfo, RecyclerView.ViewHolder>(context, listener) {

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: ContactInfo?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactContentHolder(inflate(R.layout.rv_contact_cell, parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: ContactInfo?, position: Int) {
        val contentViewHolder = viewHolder as ContactContentHolder
        contentViewHolder.mOnItemClickListener = listener
        contentViewHolder.bindData(data)
    }
}