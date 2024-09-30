package com.victor.permission.show

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.victor.permission.show.adapter.AlbumAdapter
import com.victor.permission.show.base.BaseActivity
import com.victor.permission.show.util.AlbumUtil
import com.victor.permission.show.util.ResUtils
import com.victor.permission.show.util.SpannableUtil
import kotlinx.android.synthetic.main.activity_album_result.*

class AlbumResultActivity : BaseActivity(),OnItemClickListener,OnClickListener {

    companion object {
        fun intentStart (activity: Activity) {
            var intent = Intent(activity, AlbumResultActivity::class.java)
            activity.startActivity(intent)
        }
    }

    var mAlbumAdapter: AlbumAdapter? = null

    override fun getLayoutResource() = R.layout.activity_album_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    fun initView() {
        mAlbumAdapter = AlbumAdapter(this,this)
        mRvAlbum.adapter = mAlbumAdapter

        mIvBack.setOnClickListener(this)
    }

    fun initData() {
        AlbumUtil.getAllAlbumImages(this){
            var dotTextSize = ResUtils.getDimenPixRes(com.victor.screen.match.library.R.dimen.dp_28)
            val text = "获取相册\n${it.size}张"
            val spanText = "张"
            mTvTip.text = SpannableUtil.getSpannableTextSize(dotTextSize,text, spanText)

            mAlbumAdapter?.showData(it)
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