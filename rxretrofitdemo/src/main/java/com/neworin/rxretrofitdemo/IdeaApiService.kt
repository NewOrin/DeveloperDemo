package com.neworin.rxretrofitdemo

import io.reactivex.Observable
import retrofit2.http.GET

interface IdeaApiService {

    @GET("/api/essay/1634?version=3.5.0&platform=android")
    fun getOneArticle(): Observable<BasicResponse<OneEssayEntity>>
}