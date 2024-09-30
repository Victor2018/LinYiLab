package com.victor.permission.show.holder

import android.content.pm.ApplicationInfo
import android.view.View
import com.victor.permission.show.data.AppInfo
import com.victor.permission.show.util.ImageUtils
import kotlinx.android.synthetic.main.rv_app_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AppContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class AppContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: AppInfo?) {
        itemView.mIvIcon.setImageDrawable(data?.icon)
        itemView.mTvName.text = data?.name ?: ""
        itemView.mTvPackageName.text = data?.packageName ?: ""
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}