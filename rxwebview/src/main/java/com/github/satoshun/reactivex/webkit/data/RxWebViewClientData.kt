package com.github.satoshun.reactivex.webkit.data

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse

/** represents WebViewClient event  */
sealed class  RxWebViewClientData

class DoUpdateVisitedHistory(
  val url: String,
  val isReload: Boolean
) : RxWebViewClientData()

class OnFormResubmission(
  val dontResend: Message,
  val resend: Message
) : RxWebViewClientData()

class WebResourceOnReceivedError(
  val request: WebResourceRequest,
  val error: WebResourceError
) : RxWebViewClientData()

class OnTooManyRedirect(
  val cancelMsg: Message,
  val continueMsg: Message
) : RxWebViewClientData()

class ShouldOverrideKeyEvent(val event: KeyEvent) : RxWebViewClientData()

class ShouldOverrideUrlLoading(val url: String) : RxWebViewClientData()

class ShouldInterceptWebResourceRequest(
  val resourceRequest: WebResourceRequest
) : RxWebViewClientData()

class ShouldInterceptRequest(val url: String) : RxWebViewClientData()

class ShouldOverrideUrlLoadingWebResourceRequest(
  val resourceRequest: WebResourceRequest
) : RxWebViewClientData()

class OnLoadResource(val url: String) : RxWebViewClientData()

class OnPageCommitVisible(val url: String) : RxWebViewClientData()

class OnPageStarted(val url: String, val favicon: Bitmap?) : RxWebViewClientData()

class OnReceivedClientCertRequest(val request: ClientCertRequest) : RxWebViewClientData()

class OnPageFinished(val url: String) : RxWebViewClientData()

class OnReceivedLoginRequest(
  val realm: String,
  val account: String,
  val args: String
) : RxWebViewClientData()

class OnReceivedHttpAuthRequest(
  val handler: HttpAuthHandler,
  val host: String,
  val realm: String
) : RxWebViewClientData()

class OnReceivedHttpError(
  val request: WebResourceRequest,
  val errorResponse: WebResourceResponse
) : RxWebViewClientData()

class OnReceivedError(
  val errorCode: Int,
  val description: String,
  val failingUrl: String
) : RxWebViewClientData()

class OnReceivedSslError(val handler: SslErrorHandler, val error: SslError) : RxWebViewClientData()

class OnUnhandledKeyEvent(val event: KeyEvent) : RxWebViewClientData()

class OnScaleChanged(val oldScale: Float, val newScale: Float) : RxWebViewClientData()
