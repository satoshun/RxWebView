package com.github.satoshun.reactivex.webkit

import android.webkit.WebView
import com.github.satoshun.reactivex.webkit.data.RxWebViewClientData
import io.reactivex.Observable

/**
 * Create an observable which emits from `WebView` based on WebViewClient event.
 * data types are defined into [com.github.satoshun.reactivex.webkit.data].
 * It's corresponding to [android.webkit.WebViewClient] event.
 */
fun WebView.events(): Observable<RxWebViewClientData> = RxWebViewClient.events(this)
