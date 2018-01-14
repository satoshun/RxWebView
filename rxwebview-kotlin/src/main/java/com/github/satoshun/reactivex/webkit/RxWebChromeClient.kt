package com.github.satoshun.reactivex.webkit

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable

fun WebView.chromeEvents(client: WebChromeClient? = null): Observable<RxWebChromeClientData>
    = RxWebChromeClient.events(this, client)
