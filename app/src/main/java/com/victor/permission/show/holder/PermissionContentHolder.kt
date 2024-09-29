package com.victor.permission.show.holder

import android.view.View
import kotlinx.android.synthetic.main.rv_permission_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: PermissionContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class PermissionContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: String?) {
        itemView.mTvPermission.text = data
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}