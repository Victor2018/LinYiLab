package com.victor.permission.show.holder

import android.view.View
import com.victor.permission.show.data.ContactInfo
import kotlinx.android.synthetic.main.rv_contact_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ContactContentHolder
 * Author: Victor
 * Date: 2024/09/29 16:13
 * Description: 
 * -----------------------------------------------------------------
 */

class ContactContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData(data: ContactInfo?) {
        itemView.mTvName.text = data?.name ?: ""
        itemView.mTvPhone.text = data?.phone ?: ""
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}