package com.neworin.rxretrofitdemo

import android.app.ProgressDialog
import android.content.Context

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
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