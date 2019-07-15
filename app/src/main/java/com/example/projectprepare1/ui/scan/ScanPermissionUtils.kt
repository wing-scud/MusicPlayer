package com.example.projectprepare1.ui.scan

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.View.inflate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat



class ScanPermissionUtils(private val context: Activity) {

    private var mHasPermissionRunnable: Runnable? = null
    private var mNoPermissionRunnable: Runnable? = null
    private var REQUEST_CODE_PERMISSION = 1000

    fun checkStoragePermission(hasPermissionDo: Runnable) {
        var permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        checkPermission(permission, hasPermissionDo, Runnable {

        })
    }
    fun checkPermission(permissions: Array<out String>, hasPermissionDo: Runnable, noPermissionDo: Runnable) {
        mHasPermissionRunnable = null
        mNoPermissionRunnable = null
        if (isPermissionsGranted(permissions)) hasPermissionDo.run()
        else if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions.get(0))) {
            noPermissionDo.run()
        } else {
            mHasPermissionRunnable = hasPermissionDo
            mNoPermissionRunnable = noPermissionDo
            ActivityCompat.requestPermissions(context, permissions, REQUEST_CODE_PERMISSION)
        }
    }
    fun isPermissionsGranted(permissions: Array<out String>): Boolean {
        for (it in permissions) {
            if (ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

}