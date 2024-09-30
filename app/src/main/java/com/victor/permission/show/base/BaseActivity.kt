package com.victor.permission.show.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BaseActivity
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class BaseActivity: AppCompatActivity() {
    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false
    protected var TAG = javaClass.simpleName
    var statusBarTextColorBlack: Boolean = true

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(getLayoutResource())
        } else {
            initDataBind()
        }
    }

    /**
     * 供子类BaseDbActivity 初始化DataBinding操作
     */
    open fun initDataBind() {}

    internal fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }

}