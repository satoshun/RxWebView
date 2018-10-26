package com.github.satoshun.reactivex.webkit

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Message
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.GeolocationPermissions
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebStorage
import android.webkit.WebView
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import com.github.satoshun.reactivex.webkit.data.GetDefaultVideoPoster
import com.github.satoshun.reactivex.webkit.data.GetVideoLoadingProgressView
import com.github.satoshun.reactivex.webkit.data.GetVisitedHistory
import com.github.satoshun.reactivex.webkit.data.OnCloseWindow
import com.github.satoshun.reactivex.webkit.data.OnConsoleMessage
import com.github.satoshun.reactivex.webkit.data.OnConsoleMessageNew
import com.github.satoshun.reactivex.webkit.data.OnCreateWindow
import com.github.satoshun.reactivex.webkit.data.OnExceededDatabaseQuota
import com.github.satoshun.reactivex.webkit.data.OnGeolocationPermissionsHidePrompt
import com.github.satoshun.reactivex.webkit.data.OnGeolocationPermissionsShowPrompt
import com.github.satoshun.reactivex.webkit.data.OnHideCustomView
import com.github.satoshun.reactivex.webkit.data.OnJsAlert
import com.github.satoshun.reactivex.webkit.data.OnJsBeforeUnload
import com.github.satoshun.reactivex.webkit.data.OnJsConfirm
import com.github.satoshun.reactivex.webkit.data.OnJsPrompt
import com.github.satoshun.reactivex.webkit.data.OnJsTimeout
import com.github.satoshun.reactivex.webkit.data.OnPermissionRequest
import com.github.satoshun.reactivex.webkit.data.OnPermissionRequestCanceled
import com.github.satoshun.reactivex.webkit.data.OnProgressChanged
import com.github.satoshun.reactivex.webkit.data.OnReachedMaxAppCacheSize
import com.github.satoshun.reactivex.webkit.data.OnReceivedIcon
import com.github.satoshun.reactivex.webkit.data.OnReceivedTitle
import com.github.satoshun.reactivex.webkit.data.OnReceivedTouchIconUrl
import com.github.satoshun.reactivex.webkit.data.OnRequestFocus
import com.github.satoshun.reactivex.webkit.data.OnShowCustomView
import com.github.satoshun.reactivex.webkit.data.OnShowCustomViewRequestedOrientation
import com.github.satoshun.reactivex.webkit.data.OnShowFileChooser
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * static factory methods for creating [io.reactivex.Observable]
 */
object RxWebChromeClient {
  /**
   * Create an observable of WebView from WebChromeClient.
   */
  @CheckResult
  @JvmOverloads fun events(webView: WebView, delegate: WebChromeClient? = null): Observable<RxWebChromeClientData> {
    return AllChromeOnObservable(webView, delegate)
  }
}

private class AllChromeOnObservable(
  private val webView: WebView,
  private val delegate: WebChromeClient?
) : Observable<RxWebChromeClientData>() {

  override fun subscribeActual(observer: Observer<in RxWebChromeClientData>) {
    webView.webChromeClient = ClientWrapper(observer, delegate)
    observer.onSubscribe(object : MainThreadDisposable() {
      override fun onDispose() {
        webView.webChromeClient = null
      }
    })
  }

  internal class ClientWrapper(
    private val observer: Observer<in RxWebChromeClientData>,
    private val delegate: WebChromeClient?
  ) : WebChromeClient() {

    override fun onProgressChanged(view: WebView, newProgress: Int) {
      if (delegate != null) {
        delegate.onProgressChanged(view, newProgress)
      } else {
        super.onProgressChanged(view, newProgress)
      }
      observer.onNext(OnProgressChanged(newProgress))
    }

    override fun onReceivedTitle(view: WebView, title: String) {
      if (delegate != null) {
        delegate.onReceivedTitle(view, title)
      } else {
        super.onReceivedTitle(view, title)
      }
      observer.onNext(OnReceivedTitle(title))
    }

    override fun onReceivedIcon(view: WebView, icon: Bitmap) {
      if (delegate != null) {
        delegate.onReceivedIcon(view, icon)
      } else {
        super.onReceivedIcon(view, icon)
      }
      observer.onNext(OnReceivedIcon(icon))
    }

    override fun onReceivedTouchIconUrl(view: WebView, url: String, precomposed: Boolean) {
      if (delegate != null) {
        delegate.onReceivedTouchIconUrl(view, url, precomposed)
      } else {
        super.onReceivedTouchIconUrl(view, url, precomposed)
      }
      observer.onNext(OnReceivedTouchIconUrl(url, precomposed))
    }

    override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
      if (delegate != null) {
        delegate.onShowCustomView(view, callback)
      } else {
        super.onShowCustomView(view, callback)
      }
      observer.onNext(OnShowCustomView(callback))
    }

    override fun onShowCustomView(view: View, requestedOrientation: Int, callback: WebChromeClient.CustomViewCallback) {
      if (delegate != null) {
        delegate.onShowCustomView(view, requestedOrientation, callback)
      } else {
        super.onShowCustomView(view, requestedOrientation, callback)
      }
      observer.onNext(OnShowCustomViewRequestedOrientation(requestedOrientation, callback))
    }

    override fun onHideCustomView() {
      if (delegate != null) {
        delegate.onHideCustomView()
      } else {
        super.onHideCustomView()
      }
      observer.onNext(OnHideCustomView)
    }

    override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message): Boolean {
      observer.onNext(OnCreateWindow(isDialog, isUserGesture, resultMsg))
      return delegate?.onCreateWindow(view, isDialog, isUserGesture, resultMsg) ?: super.onCreateWindow(
          view,
          isDialog,
          isUserGesture,
          resultMsg
      )
    }

    override fun onRequestFocus(view: WebView) {
      if (delegate != null) {
        delegate.onRequestFocus(view)
      } else {
        super.onRequestFocus(view)
      }
      observer.onNext(OnRequestFocus)
    }

    override fun onCloseWindow(window: WebView) {
      if (delegate != null) {
        delegate.onCloseWindow(window)
      } else {
        super.onCloseWindow(window)
      }
      observer.onNext(OnCloseWindow)
    }

    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
      observer.onNext(OnJsAlert(url, message, result))
      return delegate?.onJsAlert(view, url, message, result) ?: super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
      observer.onNext(OnJsConfirm(url, message, result))
      return delegate?.onJsConfirm(view, url, message, result) ?: super.onJsConfirm(view, url, message, result)
    }

    override fun onJsPrompt(
      view: WebView,
      url: String,
      message: String,
      defaultValue: String,
      result: JsPromptResult
    ): Boolean {
      observer.onNext(OnJsPrompt(url, message, defaultValue, result))
      return delegate?.onJsPrompt(view, url, message, defaultValue, result) ?: super.onJsPrompt(
          view,
          url,
          message,
          defaultValue,
          result
      )
    }

    override fun onJsBeforeUnload(view: WebView, url: String, message: String, result: JsResult): Boolean {
      observer.onNext(OnJsBeforeUnload(url, message, result))
      return delegate?.onJsBeforeUnload(view, url, message, result) ?: super.onJsBeforeUnload(
          view,
          url,
          message,
          result
      )
    }

    override fun onExceededDatabaseQuota(
      url: String,
      databaseIdentifier: String,
      quota: Long,
      estimatedDatabaseSize: Long,
      totalQuota: Long,
      quotaUpdater: WebStorage.QuotaUpdater
    ) {
      if (delegate != null) {
        delegate.onExceededDatabaseQuota(
            url,
            databaseIdentifier,
            quota,
            estimatedDatabaseSize,
            totalQuota,
            quotaUpdater
        )
      } else {
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater)
      }
      observer.onNext(
          OnExceededDatabaseQuota(
              databaseIdentifier,
              quota,
              estimatedDatabaseSize,
              totalQuota,
              quotaUpdater
          )
      )
    }

    override fun onReachedMaxAppCacheSize(requiredStorage: Long, quota: Long, quotaUpdater: WebStorage.QuotaUpdater) {
      if (delegate != null) {
        delegate.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater)
      } else {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater)
      }
      observer.onNext(OnReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater))
    }

    override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
      if (delegate != null) {
        delegate.onGeolocationPermissionsShowPrompt(origin, callback)
      } else {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
      }
      observer.onNext(OnGeolocationPermissionsShowPrompt(origin, callback))
    }

    override fun onGeolocationPermissionsHidePrompt() {
      if (delegate != null) {
        delegate.onGeolocationPermissionsHidePrompt()
      } else {
        super.onGeolocationPermissionsHidePrompt()
      }
      observer.onNext(OnGeolocationPermissionsHidePrompt)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onPermissionRequest(request: PermissionRequest) {
      if (delegate != null) {
        delegate.onPermissionRequest(request)
      } else {
        super.onPermissionRequest(request)
      }
      observer.onNext(OnPermissionRequest(request))
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onPermissionRequestCanceled(request: PermissionRequest) {
      if (delegate != null) {
        delegate.onPermissionRequestCanceled(request)
      } else {
        super.onPermissionRequestCanceled(request)
      }
      observer.onNext(OnPermissionRequestCanceled(request))
    }

    override fun onJsTimeout(): Boolean {
      observer.onNext(OnJsTimeout)
      return delegate?.onJsTimeout() ?: super.onJsTimeout()
    }

    override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
      if (delegate != null) {
        delegate.onConsoleMessage(message, lineNumber, sourceID)
      } else {
        super.onConsoleMessage(message, lineNumber, sourceID)
      }
      observer.onNext(OnConsoleMessage(message, lineNumber, sourceID))
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
      observer.onNext(OnConsoleMessageNew(consoleMessage))
      return delegate?.onConsoleMessage(consoleMessage) ?: super.onConsoleMessage(consoleMessage)
    }

    override fun getDefaultVideoPoster(): Bitmap? {
      observer.onNext(GetDefaultVideoPoster)
      return if (delegate != null) {
        delegate.defaultVideoPoster
      } else {
        super.getDefaultVideoPoster()
      }
    }

    override fun getVideoLoadingProgressView(): View? {
      observer.onNext(GetVideoLoadingProgressView)
      return if (delegate != null) {
        delegate.videoLoadingProgressView
      } else {
        super.getVideoLoadingProgressView()
      }
    }

    override fun getVisitedHistory(callback: ValueCallback<Array<String>>) {
      if (delegate != null) {
        delegate.getVisitedHistory(callback)
      } else {
        super.getVisitedHistory(callback)
      }
      observer.onNext(GetVisitedHistory(callback))
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onShowFileChooser(
      webView: WebView,
      filePathCallback: ValueCallback<Array<Uri>>,
      fileChooserParams: WebChromeClient.FileChooserParams
    ): Boolean {
      observer.onNext(OnShowFileChooser(filePathCallback, fileChooserParams))
      return delegate?.onShowFileChooser(webView, filePathCallback, fileChooserParams) ?: super.onShowFileChooser(
          webView,
          filePathCallback,
          fileChooserParams
      )
    }
  }
}
