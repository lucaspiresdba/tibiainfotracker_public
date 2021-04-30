package br.com.lucaspires.tibiainfotracker.presentation.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import br.com.lucaspires.tibiainfotracker.databinding.ActivityWebviewBinding

internal class WebView : AppCompatActivity() {

    companion object {
        private const val URL_BUNDLE = "URL_BUNDLE"
        private const val TIBIA_URL = "https://www.tibia.com"
        fun callActivity(context: Context, url: String) =
            Intent(context, WebView::class.java).apply {
                putExtra(URL_BUNDLE, url)
            }
    }

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupView() {
        binding.webViewTibia.let { web ->
            web.settings.let {
                it.javaScriptEnabled = true
                it.useWideViewPort = true
                it.loadWithOverviewMode = true
                it.builtInZoomControls = true
                it.displayZoomControls = false
            }
            web.webViewClient = MyWebViewClient()
            web.loadUrl(intent.getStringExtra(URL_BUNDLE) ?: TIBIA_URL)
        }
        binding.imageViewCloseWebView.setOnClickListener {
            onBackPressed()
        }
    }
}

internal class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: android.webkit.WebView?,
        request: WebResourceRequest?
    ): Boolean {
        return false
    }
}
