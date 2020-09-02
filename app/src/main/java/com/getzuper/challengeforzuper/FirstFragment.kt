//package com.getzuper.challengeforzuper
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.Button
//import androidx.fragment.app.Fragment
//import okhttp3.*
//import java.io.IOException
//
///**
// * A simple [Fragment] subclass as the default destination in the navigation.
// */
//class FirstFragment : Fragment() {
//    private lateinit var webView: WebView
//    private var clicks = 0;
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        webView = view.findViewById(R.id.webview)
//
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
////            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            clicks++
//            executeOperation("$clicks")
//
//        }
//        loadJS()
//    }
//
//    private fun loadJS() {
//        val urlString = "https://nistruct.com/test/interview_bundle.js"
//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder().url(urlString).build()
//        okHttpClient.newCall(request).enqueue(object: Callback{
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful) {
//                    var jsContent = response.body?.string()
//                    Log.d("FirstFrag", "jsContext: $jsContent")
//
//                    jsContent?.let {
//                        val iOSJSSyntax = "window.webkit.messageHandlers."
//                        val androidJSSyntax = "window."
//                        val correctedJSContent= it.replace(iOSJSSyntax, androidJSSyntax)
//                        requireActivity().runOnUiThread {
//                            configWebView(correctedJSContent)
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//        })
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun configWebView(jsContent: String) {
//        webView.settings.javaScriptEnabled = true
//        val htmlContentFirst = "<html>\n <body>\n <script type=\"text/javascript\">\n"
//        val htmlContentLast = "</script>\n </body>\n</html>"
//        var htmlContent = htmlContentFirst + jsContent + htmlContentLast
//        val handleJSCallback = HandleJSCallback()
//        webView.addJavascriptInterface(handleJSCallback, "jumbo")
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
////                executeOperation("1")
//            }
//        }
//        webView.loadData(htmlContent, "text/html","UTF-8")
//    }
//
//    private fun executeOperation(id: String) {
//        val urlString = "javascript: startOperation('$id');"
//        webView.loadUrl(urlString)
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun testJS() {
//        val htmlContent = "<html>\n" +
//                "<body>\n" +
//                "    <p>Show me</p>\n" +
//                "    <script type=\"text/javascript\">\n" +
//                "        'use strict';\n" +
//                "        function startOperation(id) { \n" +
//                "            window.javaCallback.postMeesage(id);\n" +
//                "        }\n" +
//                "    </script>\n" +
//                "</body>\n" +
//                "</html>"
//
//        val handleJSCallback = HandleJSCallback()
//        webView.settings.javaScriptEnabled = true
//        webView.addJavascriptInterface(handleJSCallback, "javaCallback")
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
//                val urlString = "javascript: startOperation('4');"
//                webView.loadUrl(urlString)
//            }
//        }
//        webView.loadData(htmlContent, "text/html","UTF-8")
//
//
//
//
////        webView.loadData("", "text/html", null)
////        webView.loadUrl("javascript:alert(JavaCallback.getString())")
//
//
//    }
//}