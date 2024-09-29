package com.victor.permission.show

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import com.cherry.permissions.lib.EasyPermissions
import com.cherry.permissions.lib.EasyPermissions.hasStoragePermission
import com.cherry.permissions.lib.annotations.AfterPermissionGranted
import com.cherry.permissions.lib.dialogs.DEFAULT_SETTINGS_REQ_CODE
import com.cherry.permissions.lib.dialogs.SettingsDialog
import com.google.android.material.snackbar.Snackbar
import com.victor.permission.show.util.PermissionRequestCode.REQUEST_CODE_CAMERA_PERMISSION
import com.victor.permission.show.util.PermissionRequestCode.REQUEST_CODE_CONTACTS_PERMISSION
import com.victor.permission.show.util.PermissionRequestCode.REQUEST_CODE_LOCATION_AND_CONTACTS_PERMISSION
import com.victor.permission.show.util.PermissionRequestCode.REQUEST_CODE_STORAGE_PERMISSION
import com.victor.permission.show.adapter.PermissionAdapter
import com.victor.permission.show.base.BaseActivity
import com.victor.permission.show.util.DeviceUtils
import com.victor.permission.show.util.PictureSelectorUtil
import com.victor.permission.show.util.ResUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(),OnClickListener, EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks,OnItemClickListener {

    var mPermissionAdapter: PermissionAdapter? = null


    override fun getLayoutResource() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    fun initView() {
        mPermissionAdapter = PermissionAdapter(this,this)
        mRvPermission.adapter = mPermissionAdapter

        mTvBack.setOnClickListener(this)
//        mBtnStorage.setOnClickListener(this)
//        mBtnLocationAndContacts.setOnClickListener(this)
//        mBtnCamera.setOnClickListener(this)
    }

    fun initData() {
        var playSpeeds = ResUtils.getStringArrayRes(R.array.permission_titles)
        playSpeeds?.forEach {
            mPermissionAdapter?.add(it)
        }
        mPermissionAdapter?.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        when (v?.id) {
            R.id.mTvBack -> {
                finish()
            }
//            R.id.mBtnStorage -> {
//
//            }
//            R.id.mBtnLocationAndContacts -> {
//                requestPermissionLocationAndContacts()
//            }
//            R.id.mBtnCamera -> {
//                requestPermissionCamera()
//            }
        }
    }

    @AfterPermissionGranted(REQUEST_CODE_CAMERA_PERMISSION)
    private fun requestPermissionCamera() {
        if (hasCameraPermission()) {
            // Have permission, do things!
            showMessage(mRvPermission,"AfterPermissionGranted you have Camera permission,you can take photo")
            openCameraAction()
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_camera_rationale_message),
                REQUEST_CODE_CAMERA_PERMISSION,
                Manifest.permission.CAMERA
            )
        }
    }

    @AfterPermissionGranted(REQUEST_CODE_CONTACTS_PERMISSION)
    private fun requestContactsPermission() {
        if (hasContactsPermissions()) {
            // Have permissions, do things!
//            showMessage(mRvPermission,"AfterPermissionGranted you have Contacts permissions,you can get Contacts")
            ContactResultActivity.intentStart(this)
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_contacts_rationale_message),
                REQUEST_CODE_CONTACTS_PERMISSION,
                Manifest.permission.READ_CONTACTS
            )
        }
    }

    @AfterPermissionGranted(REQUEST_CODE_STORAGE_PERMISSION)
    private fun requestStoragePermission() {
        if (hasStoragePermission(this)) {
            // Have permission, do things!
//            showMessage(mRvPermission,"AfterPermissionGranted you have Storage permission,you can storage things")
            AlbumResultActivity.intentStart(this)
        } else {
            // Ask for one permission
            EasyPermissions.requestStoragePermission(
                this,
                getString(R.string.permission_storage_rationale_message),
                REQUEST_CODE_STORAGE_PERMISSION
            )
        }
    }

    private fun hasCameraPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)
    }

    private fun hasLocationAndContactsPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
        )
    }
    private fun hasContactsPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this,
            Manifest.permission.READ_CONTACTS
        )
    }

    private fun hasSmsPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.READ_SMS)
    }

    fun showMessage(view: View,message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DEFAULT_SETTINGS_REQ_CODE) {
            val yes = getString(R.string.yes)
            val no = getString(R.string.no)

            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(
                this,
                getString(
                    R.string.returned_from_app_settings_to_activity,
                    if (hasCameraPermission()) yes else no,
                    if (hasLocationAndContactsPermissions()) yes else no,
                    if (hasSmsPermission()) yes else no,
                    if (hasStoragePermission(this)) yes else no
                ),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    // ============================================================================================
    //  Implementation Permission Callbacks
    // ============================================================================================

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.d(TAG, getString(R.string.log_permissions_granted, requestCode, perms.size))
        //会回调 AfterPermissionGranted注解对应方法
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Log.d(TAG, getString(R.string.log_permissions_denied, requestCode, perms.size))

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {

            val settingsDialogBuilder = SettingsDialog.Builder(this)

            when(requestCode) {
                REQUEST_CODE_STORAGE_PERMISSION -> {
                    settingsDialogBuilder.title = getString(
                        com.cherry.permissions.lib.R.string.title_settings_dialog,
                        "Storage Permission")
                    settingsDialogBuilder.rationale = getString(
                        com.cherry.permissions.lib.R.string.rationale_ask_again,
                        "Storage Permission")
                }
                REQUEST_CODE_LOCATION_AND_CONTACTS_PERMISSION -> {
                    settingsDialogBuilder.title = getString(
                        com.cherry.permissions.lib.R.string.title_settings_dialog,
                        "Location and Contacts Permissions")
                    settingsDialogBuilder.rationale = getString(
                        com.cherry.permissions.lib.R.string.rationale_ask_again,
                        "Location and Contacts Permissions")
                }
                REQUEST_CODE_CONTACTS_PERMISSION -> {
                    settingsDialogBuilder.title = getString(
                        com.cherry.permissions.lib.R.string.title_settings_dialog,
                        "Contacts Permissions")
                    settingsDialogBuilder.rationale = getString(
                        com.cherry.permissions.lib.R.string.rationale_ask_again,
                        "Contacts Permissions")
                }
                REQUEST_CODE_CAMERA_PERMISSION -> {
                    /*settingsDialogBuilder.title = getString(
                        com.cherry.permissions.lib.R.string.title_settings_dialog,
                        "Camera Permission")
                    settingsDialogBuilder.rationale = getString(
                        com.cherry.permissions.lib.R.string.rationale_ask_again,
                        "Camera Permission")*/

                    openCameraAction()
                }
            }

            settingsDialogBuilder.build().show()
        }

    }

    // ============================================================================================
    //  Implementation Rationale Callbacks
    // ============================================================================================

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d(TAG, getString(R.string.log_permission_rationale_accepted, requestCode))
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d(TAG, getString(R.string.log_permission_rationale_denied, requestCode))
    }

    fun openCameraAction() {
//        startActivity(Intent(this,CameraActivity::class.java))
        PictureSelectorUtil.openCamera(this,false,false)
    }

    override fun onItemClick(p0: AdapterView<*>?, v: View?, position: Int, id: Long) {
        when(v?.id) {
            R.id.mClPermissionCell -> {
                itemClickAction(position)
            }
        }
    }

    fun itemClickAction(position: Int) {
        when (position) {
            0 -> {//相册
                requestStoragePermission()
            }
            1 -> {//联系人
                requestContactsPermission()
            }
            2 -> {//文件权限
                requestStoragePermission()
            }
            3 -> {//文件权限
                requestPermissionCamera()
            }
            4 -> {//手机信息
                DeviceResultActivity.intentStart(this)
            }
        }
    }
}