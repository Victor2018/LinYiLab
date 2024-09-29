package com.victor.permission.show.holder

import android.view.View
import com.victor.permission.show.util.ImageUtils
import kotlinx.android.synthetic.main.rv_album_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AlbumContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class AlbumContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: String?) {
        ImageUtils.instance.loadImage(itemView.context,itemView.mIvAlbum,data)
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}