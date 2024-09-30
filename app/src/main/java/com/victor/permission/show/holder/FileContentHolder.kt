package com.victor.permission.show.holder

import android.view.View
import com.victor.permission.show.R
import com.victor.permission.show.data.CallLogInfo
import com.victor.permission.show.util.ImageUtils
import kotlinx.android.synthetic.main.rv_file_cell.view.*
import java.io.File

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FileContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class FileContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: File?) {
        val isDirectory = data?.isDirectory ?: false
        if (isDirectory) {
            ImageUtils.instance.loadImage(itemView.context,itemView.mIvFileType, R.mipmap.ic_zfile_folder)
        } else {
            ImageUtils.instance.loadImage(itemView.context,itemView.mIvFileType, R.mipmap.ic_zfile_other)
        }
        itemView.mTvName.text = data?.name ?: ""
        itemView.mIvFilePath.text = data?.absolutePath ?: ""
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}