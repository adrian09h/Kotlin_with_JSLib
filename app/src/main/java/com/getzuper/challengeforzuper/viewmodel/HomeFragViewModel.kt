package com.getzuper.challengeforzuper.viewmodel

import android.util.Log
import android.webkit.JavascriptInterface
import androidx.lifecycle.MutableLiveData
import com.getzuper.challengeforzuper.data.model.MessageFromJS
import com.getzuper.challengeforzuper.data.remote.api.FetchJSAPIService
import com.getzuper.challengeforzuper.data.repository.JSFileRepository
import com.google.gson.Gson
import javax.inject.Inject

class HomeFragViewModel @Inject constructor(
    fetchJSAPIService: FetchJSAPIService)  : BaseViewModel() {
    private val fetchJSFileRepository = JSFileRepository(fetchJSAPIService)
    private val messageListData: MutableLiveData<ArrayList<MessageFromJS>> = MutableLiveData()
    private val jsContentLiveData: MutableLiveData<String> = MutableLiveData()

    fun loadJSContent() {
        fetchJSFileRepository.fetchJSContent().subscribe { responseBody ->
            var jsContent = responseBody.string()
            Log.d("FirstFrag", "jsContext: $jsContent")
            val iOSJSSyntax = "window.webkit.messageHandlers."
            val androidJSSyntax = "window."
            val correctedJSContent= jsContent.replace(iOSJSSyntax, androidJSSyntax)
            jsContentLiveData.postValue(correctedJSContent)
        }
    }


    @JavascriptInterface
    fun postMessage(result: String?) {
        Log.d("From JS", "message from JS: $result")
        if (result == null) return
        var oldList = messageListData.value
        if (oldList == null) {
            oldList = ArrayList()
        }
        val newMessage = Gson().fromJson(result, MessageFromJS::class.java)
        val indexOfFirst = oldList.indexOfFirst { it.id == newMessage.id }
        if (indexOfFirst > -1) {
            //existing operation
            val oldOne = oldList[indexOfFirst]
            if (newMessage.isCompletedType()) {
                if (newMessage.state!! == "error") {
                    newMessage.progress = oldOne.progress
                } else {
                    newMessage.progress = "100"
                }
            }
            oldList[indexOfFirst] = newMessage
        } else {
            //new operation
            if (newMessage.isCompletedType()) {
                if (newMessage.state!! == "error") {
                    //got error as a first message for the operation
                    newMessage.progress = "0"
                } else {
                    //possible?
                    newMessage.progress = "100"
                }
            }
            oldList.add(newMessage)
        }
        messageListData.postValue(oldList)
    }

    fun getMessageListData() = messageListData

    fun getJsContentLiveData() = jsContentLiveData
}