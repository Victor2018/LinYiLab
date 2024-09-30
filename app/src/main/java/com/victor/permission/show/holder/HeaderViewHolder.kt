package com.victor.permission.show.holder

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HeaderViewHolder.java
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

open class HeaderViewHolder: RecyclerView.ViewHolder,View.OnClickListener {
    var mOnItemClickListener: AdapterView.OnItemClickListener? = null

    constructor(itemView: View) : super(itemView) {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        mOnItemClickListener?.onItemClick(null, v, bindingAdapterPosition, 0)
    }
}