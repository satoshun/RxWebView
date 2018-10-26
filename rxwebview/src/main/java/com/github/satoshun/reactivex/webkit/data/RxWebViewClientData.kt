package com.github.satoshun.reactivex.webkit.data

import android.os.Message
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest

/** represents WebViewClient event  */
interface RxWebViewClientData

class DoUpdateVisitedHistory(
  val url: String,
  val isReload: Boolean
) : RxWebViewClientData

class OnFormResubmission(
  val dontResend: Message,
  val resend: Message
) : RxWebViewClientData

class WebResourceOnReceivedError(
  val request: WebResourceRequest,
  val error: WebResourceError
) : RxWebViewClientData
