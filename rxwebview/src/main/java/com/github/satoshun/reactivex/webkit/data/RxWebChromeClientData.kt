package com.github.satoshun.reactivex.webkit.data

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.ClientCertRequest
import android.webkit.ConsoleMessage
import android.webkit.GeolocationPermissions
import android.webkit.HttpAuthHandler
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebStorage

/** represents WebChromeClient event  */
interface RxWebChromeClientData

class GetDefaultVideoPoster : RxWebChromeClientData
class GetVideoLoadingProgressView : RxWebChromeClientData

class GetVisitedHistory(
  val callback: ValueCallback<Array<String>>
) : RxWebChromeClientData

class OnCloseWindow : RxWebChromeClientData
class OnConsoleMessageNew(
  val consoleMessage: ConsoleMessage
) : RxWebChromeClientData

class OnShowCustomViewRequestedOrientation(
  val requestedOrientation: Int,
  val callback: WebChromeClient.CustomViewCallback
) : RxWebChromeClientData

class OnExceededDatabaseQuota(
  val databaseIdentifier: String,
  val quota: Long,
  val estimatedDatabaseSize: Long,
  val totalQuota: Long,
  val quotaUpdater: WebStorage.QuotaUpdater
) : RxWebChromeClientData

class OnConsoleMessage(
  val message: String,
  val lineNumber: Int,
  val sourceID: String
) : RxWebChromeClientData

class OnCreateWindow(
  val isDialog: Boolean,
  val isUserGesture: Boolean,
  val resultMsg: Message
) : RxWebChromeClientData

class OnGeolocationPermissionsShowPrompt(
  val origin: String,
  val callback: GeolocationPermissions.Callback
) : RxWebChromeClientData

class OnReceivedTouchIconUrl(
  val url: String,
  val isPrecomposed: Boolean
) : RxWebChromeClientData

class OnShowFileChooser(
  val filePathCallback: ValueCallback<Array<Uri>>,
  val fileChooserParams: WebChromeClient.FileChooserParams
) : RxWebChromeClientData

class OnShowCustomView(val callback: WebChromeClient.CustomViewCallback) : RxWebChromeClientData

class OnJsPrompt(
  val url: String,
  val message: String,
  val defaultValue: String,
  val result: JsPromptResult
) : RxWebChromeClientData

class OnTooManyRedirect(
  val cancelMsg: Message,
  val continueMsg: Message
) : RxWebViewClientData

class ShouldOverrideKeyEvent(val event: KeyEvent) : RxWebViewClientData

class ShouldOverrideUrlLoading(val url: String) : RxWebViewClientData

class ShouldInterceptWebResourceRequest(
  val resourceRequest: WebResourceRequest
) : RxWebViewClientData

class ShouldInterceptRequest(val url: String) : RxWebViewClientData

class ShouldOverrideUrlLoadingWebResourceRequest(
  val resourceRequest: WebResourceRequest
) : RxWebViewClientData

class OnJsConfirm(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData

class OnJsBeforeUnload(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData

class OnJsAlert(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData

class OnLoadResource(val url: String) : RxWebViewClientData

class OnPageCommitVisible(val url: String) : RxWebViewClientData

class OnGeolocationPermissionsHidePrompt : RxWebChromeClientData

class OnProgressChanged(val newProgress: Int) : RxWebChromeClientData

class OnPageStarted(val url: String, val favicon: Bitmap) : RxWebViewClientData

class OnJsTimeout : RxWebChromeClientData

class OnPermissionRequestCanceled(val request: PermissionRequest) : RxWebChromeClientData

class OnReachedMaxAppCacheSize(
  val requiredStorage: Long,
  val quota: Long,
  val quotaUpdater: WebStorage.QuotaUpdater
) : RxWebChromeClientData

class OnPermissionRequest(val request: PermissionRequest) : RxWebChromeClientData

class OnReceivedClientCertRequest(val request: ClientCertRequest) : RxWebViewClientData

class OnPageFinished(val url: String) : RxWebViewClientData

class OnReceivedLoginRequest(
  val realm: String,
  val account: String,
  val args: String
) : RxWebViewClientData

class OnReceivedHttpAuthRequest(
  val handler: HttpAuthHandler,
  val host: String,
  val realm: String
) : RxWebViewClientData

class OnReceivedHttpError(
  val request: WebResourceRequest,
  val errorResponse: WebResourceResponse
) : RxWebViewClientData

class OnReceivedError(
  val errorCode: Int,
  val description: String,
  val failingUrl: String
) : RxWebViewClientData

class OnReceivedSslError(val handler: SslErrorHandler, val error: SslError) : RxWebViewClientData

class OnReceivedIcon(val icon: Bitmap) : RxWebChromeClientData

class OnRequestFocus : RxWebChromeClientData

class OnReceivedTitle(val title: String) : RxWebChromeClientData

class OnUnhandledKeyEvent(val event: KeyEvent) : RxWebViewClientData

class OnScaleChanged(val oldScale: Float, val newScale: Float) : RxWebViewClientData

class OnHideCustomView : RxWebChromeClientData
