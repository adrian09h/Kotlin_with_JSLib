package com.getzuper.challengeforzuper.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getzuper.challengeforzuper.R
import com.getzuper.challengeforzuper.ui.adapter.OperationsAdapter
import com.getzuper.challengeforzuper.utils.ContextUtils
import com.getzuper.challengeforzuper.viewmodel.HomeFragViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var webView: WebView
    private lateinit var viewModel: HomeFragViewModel
    private lateinit var adapter: OperationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeFragViewModel::class.java)
        initWithViewModel()
        showProgress()
        viewModel.loadJSContent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        webView = rootView.findViewById(R.id.web_view)
        adapter = OperationsAdapter()
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        btn_execute.setOnClickListener {
            ContextUtils.hideKeyboard(requireActivity())
            if (!operation_input.text?.toString().isNullOrEmpty()) {
                val id = operation_input.text?.toString()
                executeOperation(id!!)
            } else {
                operation_layout.error = getString(R.string.err_no_operation_id)
            }
        }

        operation_input.addTextChangedListener {
            if (!it?.toString().isNullOrEmpty()) {
                operation_layout.error = null
            }
        }
    }

    private fun initWithViewModel() {
        viewModel.getJsContentLiveData().observe(viewLifecycleOwner, Observer {
            hideProgress()
            configWebView(it)
        })
        viewModel.getMessageListData().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configWebView(jsContent: String) {
        webView.settings.javaScriptEnabled = true
        val htmlContentFirst = "<html>\n <body>\n <script type=\"text/javascript\">\n"
        val htmlContentLast = "</script>\n </body>\n</html>"
        var htmlContent = htmlContentFirst + jsContent + htmlContentLast

        webView.addJavascriptInterface(viewModel, "jumbo")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
//                executeOperation("1")
            }
        }
        webView.loadData(htmlContent, "text/html","UTF-8")
    }

    private fun executeOperation(id: String) {
        val urlString = "javascript: startOperation('$id');"
        webView.loadUrl(urlString)
    }

    private fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress_bar.visibility = View.INVISIBLE
    }
}