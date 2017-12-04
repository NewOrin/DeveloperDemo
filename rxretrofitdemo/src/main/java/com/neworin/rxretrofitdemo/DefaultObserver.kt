package com.neworin.rxretrofitdemo

import android.content.Context
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
abstract class DefaultObserver<T>(context: Context) : Observer<T> {

    private var mProDialog: ProgressDialogUtils? = null

    init {
        mProDialog = ProgressDialogUtils()
        mProDialog?.showProgress(context)
    }

    override fun onError(e: Throwable) {
        mProDialog?.dismissDialog()
        onFailed(e)
    }

    override fun onNext(t: T) {
        mProDialog?.dismissDialog()
        onSuccess(t)
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    /**
     * 请求成功
     */
    abstract fun onSuccess(response: T)

    abstract fun onFailed(e: Throwable)
}