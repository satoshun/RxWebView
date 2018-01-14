package com.github.satoshun.reactivex.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.github.satoshun.reactivex.webview.data.RxWebChromeClientData
import io.reactivex.Observable

fun WebView.chromeEvents(client: WebChromeClient? = null): Observable<RxWebChromeClientData>
    = RxWebChromeClient.events(this, client)
