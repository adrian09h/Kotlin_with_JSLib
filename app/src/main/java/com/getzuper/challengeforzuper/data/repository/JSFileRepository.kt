package com.getzuper.challengeforzuper.data.repository

import com.getzuper.challengeforzuper.data.remote.api.FetchJSAPIService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Singleton

@Singleton
class JSFileRepository(
    private val fetchJSAPIService: FetchJSAPIService
) {
    fun fetchJSContent(): Observable<ResponseBody> {
        return fetchJSAPIService
            .fetchJSContent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}