package com.neworin.rxretrofitdemo

import android.os.Environment
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

class IdeaApi private constructor() {

    var service: IdeaApiService

    companion object {
        val instance: IdeaApi by lazy { IdeaApi() }
    }

    init {
        //日志拦截器
        val interceptor = HttpLoggingInterceptor { message ->
            try {
                val text = URLDecoder.decode(message, "utf-8")
                Logger.d(text)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheFile = File(Environment.getExternalStorageDirectory(), "/cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 100) //100Mb

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://v3.wufazhuce.com:8000")
                .build()
        service = retrofit.create(IdeaApiService::class.java)
    }

    inner class HttpCacheInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            Logger.d(request.toString())
            return chain.proceed(chain.request())
        }

    }
}