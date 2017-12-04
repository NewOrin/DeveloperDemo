package com.neworin.rxretrofitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        main_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == main_btn) {
            IdeaApi.instance.service
                    .getOneArticle()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxResultHelper.handleResult())
                    .subscribe(object : DefaultObserver<OneEssayEntity>(this) {
                        override fun onSuccess(response: OneEssayEntity) {
                            main_tv.text = response.hp_content
                        }

                        override fun onFailed(e: Throwable) {
                            Logger.d(e.message)
                        }

                    })
        }
    }
}
