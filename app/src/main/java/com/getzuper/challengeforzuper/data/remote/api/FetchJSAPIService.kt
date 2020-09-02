package com.getzuper.challengeforzuper.data.remote.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface FetchJSAPIService {
    @GET("/test/interview_bundle.js")
    fun fetchJSContent(): Observable<ResponseBody>
}