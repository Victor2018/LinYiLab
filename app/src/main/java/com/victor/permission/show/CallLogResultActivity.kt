package com.victor.permission.show

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.victor.permission.show.adapter.CallLogAdapter
import com.victor.permission.show.base.BaseActivity
import com.victor.permission.show.util.CallLogUtil
import com.victor.permission.show.util.ContactUtil
import com.victor.permission.show.util.ResUtils
import com.victor.permission.show.util.SpannableUtil
import kotlinx.android.synthetic.main.activity_call_log_result.*

class CallLogResultActivity : BaseActivity(),OnItemClickListener,OnClickListener {

    companion object {
        fun intentStart (activity: Activity) {
            var intent = Intent(activity, CallLogResultActivity::class.java)
            activity.startActivity(intent)
        }
    }

    var mCallLogAdapter: CallLogAdapter? = null

    override fun getLayoutResource() = R.layout.activity_call_log_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    fun initView() {
        mCallLogAdapter = CallLogAdapter(this,this)
        mRvCallLog.adapter = mCallLogAdapter

        mIvBack.setOnClickListener(this)
    }

    fun initData() {
        CallLogUtil.getAllCallLog(this){
            var dotTextSize = ResUtils.getDimenPixRes(com.victor.screen.match.library.R.dimen.dp_28)
            val text = "获取通话记录\n${it.size}条"
            val spanText = "条"
            mTvTip.text = SpannableUtil.getSpannableTextSize(dotTextSize,text, spanText)

            mCallLogAdapter?.showData(it)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, v: View?, position: Int, p3: Long) {
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mIvBack -> {
                finish()
            }
        }
    }

}