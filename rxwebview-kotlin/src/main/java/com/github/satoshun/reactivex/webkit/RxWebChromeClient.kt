@file:Suppress("NOTHING_TO_INLINE")

package com.github.satoshun.reactivex.webkit

import android.webkit.WebView
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable

inline fun WebView.chromeEvents(): Observable<RxWebChromeClientData> = RxWebChromeClient.events(this)
