package com.github.satoshun.reactivex.webkit

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.widget.FrameLayout

class RxWebViewTestActivity : Activity() {
  private lateinit var parent: FrameLayout
  lateinit var webView: WebView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    parent = FrameLayout(this)
    webView = WebView(this)
    parent.addView(webView)
    setContentView(parent)
  }
}
