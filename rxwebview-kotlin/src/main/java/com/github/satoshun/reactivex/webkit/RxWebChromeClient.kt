@file:Suppress("NOTHING_TO_INLINE")

package com.github.satoshun.reactivex.webkit

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.annotation.CheckResult
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable

/**
 * Create an observable of WebView from WebChromeClient.
 */
@CheckResult
inline fun WebView.chromeEvents(delegate: WebChromeClient? = null): Observable<RxWebChromeClientData> =
    RxWebChromeClient.events(this, delegate)
