package com.github.satoshun.reactivex.webkit

import android.webkit.WebView
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable

fun WebView.chromeEvents(): Observable<RxWebChromeClientData> = RxWebChromeClient.events(this)
