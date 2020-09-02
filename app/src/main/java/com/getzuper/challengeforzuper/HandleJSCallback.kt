package com.getzuper.challengeforzuper

import android.util.Log
import android.webkit.JavascriptInterface

class HandleJSCallback {
    @JavascriptInterface
    fun postMessage(result: String?) {
        Log.d("From JS", "Okay, good to go $result")
    }

}