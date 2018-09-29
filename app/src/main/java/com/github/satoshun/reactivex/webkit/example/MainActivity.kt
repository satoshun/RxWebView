package com.github.satoshun.reactivex.webkit.example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.reactivex.webkit.chromeEvents
import com.github.satoshun.reactivex.webkit.data.OnJsBeforeUnload
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.data.OnPageStarted
import com.github.satoshun.reactivex.webkit.data.OnReceivedIcon
import com.github.satoshun.reactivex.webkit.data.ShouldInterceptRequest
import com.github.satoshun.reactivex.webkit.events
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sampleWebViewClient()
    sampleWebChromeClient()
  }

  private fun sampleWebViewClient() {
    detectStartAndFinishEvent()
    otherEvents()
  }

  private fun detectStartAndFinishEvent() {
    val view = findViewById<WebView>(R.id.web1)

    // subscribe OnPageStarted and OnPageFinished event
    view.events(delegate = WebViewClient())
        .publish { shared ->
          Observable.merge(
              shared.ofType(OnPageStarted::class.java),
              shared.ofType(OnPageFinished::class.java)
          )
        }
        .subscribe { event ->
          when (event) {
            is OnPageStarted -> Toast
                .makeText(this, "onPageStarted", Toast.LENGTH_LONG)
                .show()
            is OnPageFinished -> Toast
                .makeText(this, "onPageFinished", Toast.LENGTH_LONG)
                .show()
          }
        }
    view.loadUrl("https://www.google.co.jp")
  }

  private fun otherEvents() {
    val view = findViewById<WebView>(R.id.web2)
    val o = view.events().share()
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
    val o = view.chromeEvents(delegate = WebChromeClient()).share()
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
