package com.victor.permission.show

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.victor.permission.show.base.BaseActivity
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : BaseActivity(),OnClickListener {
    lateinit var integrator: IntentIntegrator
    override fun getLayoutResource() = R.layout.activity_camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initScan()
        initView()
    }

    fun initView() {
        mIvBack.setOnClickListener(this)
        mTvSan.setOnClickListener(this)
    }

    fun initScan() {
        integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 设置只扫描QR码
//        integrator.setPrompt("请扫描二维码") // 设置扫描提示
        integrator.setPrompt("   ")// 设置扫描提示
        integrator.setCameraId(0) // 使用设备的特定相机（这里是第一个相机）
        integrator.setBeepEnabled(false)// 禁用扫描成功时的蜂鸣声
        integrator.setOrientationLocked(false)// 锁定扫描器的方向
        integrator.setBarcodeImageEnabled(false)// 禁用条形码图像的显示
        integrator.captureActivity = CaptureActivity::class.java
        integrator.initiateScan() // 发起扫描
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //获取扫码结果
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        mTvScanResult.text = result.contents
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mIvBack -> {
                finish()
            }
            R.id.mTvSan -> {
                integrator.initiateScan() // 发起扫描
            }
        }
    }
}