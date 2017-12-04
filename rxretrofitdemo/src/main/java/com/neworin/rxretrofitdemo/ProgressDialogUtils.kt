package com.neworin.rxretrofitdemo

import android.app.ProgressDialog
import android.content.Context

class ProgressDialogUtils {

    var mProgress: ProgressDialog? = null

    fun showProgress(context: Context) {
        if (mProgress == null) {
            mProgress = ProgressDialog(context)
        }
        mProgress?.show()
    }

    fun dismissDialog() {
        mProgress?.dismiss()
    }
}