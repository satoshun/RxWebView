package com.github.satoshun.reactivex.webview.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.github.satoshun.reactivex.webview.RxWebChromeClient
import com.github.satoshun.reactivex.webview.data.*
import com.github.satoshun.reactivex.webview.events
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sampleWebViewClient()
    sampleWebChromeClient()
  }

  private fun sampleWebViewClient() {
    startAndFinishedEvents()
    otherEvents()
  }

  private fun startAndFinishedEvents() {
    val view = findViewById<WebView>(R.id.web1)
    val client = WebViewClient()

    // subscribe OnPageStarted and OnPageFinished event
    view.events(client)
        .publish { shared ->
          Observable.merge(
              shared.ofType(OnPageStarted::class.java),
              shared.ofType(OnPageFinished::class.java)
          )
        }
        .subscribe { event ->
          if (event is OnPageStarted) {
            Toast
                .makeText(this@MainActivity, "onPageStarted", Toast.LENGTH_LONG)
                .show()
          }
          if (event is OnPageFinished) {
            Toast
                .makeText(this@MainActivity, "onPageFinished", Toast.LENGTH_LONG)
                .show()
          }
        }
    view.loadUrl("https://www.google.co.jp")
  }

  private fun otherEvents() {
    val view = findViewById<WebView>(R.id.web2)
    val client = WebViewClient()
    val o = view.events(client).share()
    // subscribe ShouldInterceptRequest event
    o.ofType(ShouldInterceptRequest::class.java)
        .subscribe { data -> Log.d("ShouldInterceptRequest", data.toString()) }
    // subscribe OnPageFinished event
    o.ofType(OnPageFinished::class.java)
        .subscribe { data -> Log.d("OnPageFinished", data.toString()) }
    // subscribe OnPageStarted event
    o.ofType(OnPageStarted::class.java)
        .subscribe { data -> Log.d("OnPageStarted", data.toString()) }
    // subscribe all events of WebViewClient
    o.subscribe { d -> Log.d("RxWebViewClient", d.toString()) }

    view.loadUrl("https://www.google.co.jp")
  }

  private fun sampleWebChromeClient() {
    val view = findViewById<View>(R.id.web3) as WebView
    val client = WebChromeClient()
    val o = RxWebChromeClient.all(view, client)
        .subscribeOn(AndroidSchedulers.mainThread()).share()
    // subscribe OnJsBeforeUnload event
    o.ofType(OnJsBeforeUnload::class.java)
        .subscribe { d -> Log.d("OnJsBeforeUnload", d.toString()) }
    // subscribe OnReceivedIcon event
    o.ofType(OnReceivedIcon::class.java)
        .subscribe { d -> Log.d("OnReceivedIcon", d.toString()) }
    // subscribe all events of WebChromeClient
    o.subscribe { d -> Log.d("RxWebChromeClient", d.toString()) }

    view.loadUrl("https://www.google.co.jp/")
  }
}
