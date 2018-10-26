package com.github.satoshun.reactivex.webkit.data

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.webkit.ConsoleMessage
import android.webkit.GeolocationPermissions
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebStorage

/** represents WebChromeClient event  */
sealed class RxWebChromeClientData

class GetVisitedHistory(
  val callback: ValueCallback<Array<String>>
) : RxWebChromeClientData()

class OnConsoleMessageNew(
  val consoleMessage: ConsoleMessage
) : RxWebChromeClientData()

class OnShowCustomViewRequestedOrientation(
  val requestedOrientation: Int,
  val callback: WebChromeClient.CustomViewCallback
) : RxWebChromeClientData()

class OnExceededDatabaseQuota(
  val databaseIdentifier: String,
  val quota: Long,
  val estimatedDatabaseSize: Long,
  val totalQuota: Long,
  val quotaUpdater: WebStorage.QuotaUpdater
) : RxWebChromeClientData()

class OnConsoleMessage(
  val message: String,
  val lineNumber: Int,
  val sourceID: String
) : RxWebChromeClientData()

class OnCreateWindow(
  val isDialog: Boolean,
  val isUserGesture: Boolean,
  val resultMsg: Message
) : RxWebChromeClientData()

class OnGeolocationPermissionsShowPrompt(
  val origin: String,
  val callback: GeolocationPermissions.Callback
) : RxWebChromeClientData()

class OnReceivedTouchIconUrl(
  val url: String,
  val isPrecomposed: Boolean
) : RxWebChromeClientData()

class OnShowFileChooser(
  val filePathCallback: ValueCallback<Array<Uri>>,
  val fileChooserParams: WebChromeClient.FileChooserParams
) : RxWebChromeClientData()

class OnShowCustomView(val callback: WebChromeClient.CustomViewCallback) : RxWebChromeClientData()

class OnJsPrompt(
  val url: String,
  val message: String,
  val defaultValue: String,
  val result: JsPromptResult
) : RxWebChromeClientData()

class OnJsConfirm(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData()

class OnJsBeforeUnload(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData()

class OnJsAlert(
  val url: String,
  val message: String,
  val result: JsResult
) : RxWebChromeClientData()

class OnProgressChanged(val newProgress: Int) : RxWebChromeClientData()

class OnPermissionRequestCanceled(val request: PermissionRequest) : RxWebChromeClientData()

class OnReachedMaxAppCacheSize(
  val requiredStorage: Long,
  val quota: Long,
  val quotaUpdater: WebStorage.QuotaUpdater
) : RxWebChromeClientData()

class OnPermissionRequest(val request: PermissionRequest) : RxWebChromeClientData()

class OnReceivedIcon(val icon: Bitmap) : RxWebChromeClientData()

class OnReceivedTitle(val title: String) : RxWebChromeClientData()

object OnGeolocationPermissionsHidePrompt : RxWebChromeClientData()
object OnJsTimeout : RxWebChromeClientData()
object OnRequestFocus : RxWebChromeClientData()
object OnHideCustomView : RxWebChromeClientData()
object GetDefaultVideoPoster : RxWebChromeClientData()
object GetVideoLoadingProgressView : RxWebChromeClientData()
object OnCloseWindow : RxWebChromeClientData()
