@file:Suppress("NOTHING_TO_INLINE")

package com.github.satoshun.reactivex.webkit

import android.support.annotation.CheckResult
import android.webkit.WebView
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable

/**
 * Create an observable of WebView from WebChromeClient.
 */
@CheckResult
inline fun WebView.chromeEvents(): Observable<RxWebChromeClientData> = RxWebChromeClient.events(this)
