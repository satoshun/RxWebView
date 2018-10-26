package com.github.satoshun.reactivex.webkit

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Message
import android.view.KeyEvent
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import com.github.satoshun.reactivex.webkit.data.DoUpdateVisitedHistory
import com.github.satoshun.reactivex.webkit.data.OnFormResubmission
import com.github.satoshun.reactivex.webkit.data.OnLoadResource
import com.github.satoshun.reactivex.webkit.data.OnPageCommitVisible
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.data.OnPageStarted
import com.github.satoshun.reactivex.webkit.data.OnReceivedClientCertRequest
import com.github.satoshun.reactivex.webkit.data.OnReceivedError
import com.github.satoshun.reactivex.webkit.data.OnReceivedHttpAuthRequest
import com.github.satoshun.reactivex.webkit.data.OnReceivedHttpError
import com.github.satoshun.reactivex.webkit.data.OnReceivedLoginRequest
import com.github.satoshun.reactivex.webkit.data.OnReceivedSslError
import com.github.satoshun.reactivex.webkit.data.OnScaleChanged
import com.github.satoshun.reactivex.webkit.data.OnTooManyRedirect
import com.github.satoshun.reactivex.webkit.data.OnUnhandledKeyEvent
import com.github.satoshun.reactivex.webkit.data.RxWebViewClientData
import com.github.satoshun.reactivex.webkit.data.ShouldInterceptRequest
import com.github.satoshun.reactivex.webkit.data.ShouldInterceptWebResourceRequest
import com.github.satoshun.reactivex.webkit.data.ShouldOverrideKeyEvent
import com.github.satoshun.reactivex.webkit.data.ShouldOverrideUrlLoading
import com.github.satoshun.reactivex.webkit.data.ShouldOverrideUrlLoadingWebResourceRequest
import com.github.satoshun.reactivex.webkit.data.WebResourceOnReceivedError
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * static factory methods for creating [io.reactivex.Completable] and [io.reactivex.Observable]
 */
object RxWebViewClient {
  /**
   * Create an observable which emits on `WebView` WebViewClient event.
   * data types are defined into [com.github.satoshun.reactivex.webkit.data].
   * It's corresponding to WebViewClient event.
   */
  @CheckResult
  @JvmOverloads fun events(webView: WebView, delegate: WebViewClient? = null): Observable<RxWebViewClientData> {
    return AllOnObservable(webView, delegate)
  }
}

/**
 * Create an observable which emits from `WebView` based on WebViewClient event.
 * data types are defined into [com.github.satoshun.reactivex.webkit.data].
 * It's corresponding to [android.webkit.WebViewClient] event.
 */
@CheckResult
fun WebView.events(delegate: WebViewClient? = null): Observable<RxWebViewClientData> =
    RxWebViewClient.events(this, delegate)

private class AllOnObservable(
  private val webView: WebView,
  private val delegate: WebViewClient?
) : Observable<RxWebViewClientData>() {
  override fun subscribeActual(observer: Observer<in RxWebViewClientData>) {
    MainThreadDisposable.verifyMainThread()

    webView.webViewClient = ClientWrapper(observer, delegate)
    observer.onSubscribe(object : MainThreadDisposable() {
      override fun onDispose() {
        webView.webViewClient = null
      }
    })
  }

  internal class ClientWrapper(
    private val observer: Observer<in RxWebViewClientData>,
    private val delegate: WebViewClient?
  ) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
      observer.onNext(ShouldOverrideUrlLoading(url))
      return delegate?.shouldOverrideUrlLoading(view, url) ?: super.shouldOverrideUrlLoading(view, url)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
      observer.onNext(ShouldOverrideUrlLoadingWebResourceRequest(request))
      return delegate?.shouldOverrideUrlLoading(view, request) ?: super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
      if (delegate != null) {
        delegate.onPageStarted(view, url, favicon)
      } else {
        super.onPageStarted(view, url, favicon)
      }
      observer.onNext(OnPageStarted(url, favicon))
    }

    override fun onPageFinished(view: WebView, url: String) {
      if (delegate != null) {
        delegate.onPageFinished(view, url)
      } else {
        super.onPageFinished(view, url)
      }
      observer.onNext(OnPageFinished(url))
    }

    override fun onLoadResource(view: WebView, url: String) {
      if (delegate != null) {
        delegate.onLoadResource(view, url)
      } else {
        super.onLoadResource(view, url)
      }
      observer.onNext(OnLoadResource(url))
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onPageCommitVisible(view: WebView, url: String) {
      if (delegate != null) {
        delegate.onPageCommitVisible(view, url)
      } else {
        super.onPageCommitVisible(view, url)
      }
      observer.onNext(OnPageCommitVisible(url))
    }

    override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
      observer.onNext(ShouldInterceptRequest(url))
      return if (delegate != null) {
        delegate.shouldInterceptRequest(view, url)
      } else super.shouldInterceptRequest(view, url)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
      observer.onNext(ShouldInterceptWebResourceRequest(request))
      return if (delegate != null) {
        delegate.shouldInterceptRequest(view, request)
      } else super.shouldInterceptRequest(view, request)
    }

    override fun onTooManyRedirects(view: WebView, cancelMsg: Message, continueMsg: Message) {
      if (delegate != null) {
        delegate.onTooManyRedirects(view, cancelMsg, continueMsg)
      } else {
        super.onTooManyRedirects(view, cancelMsg, continueMsg)
      }
      observer.onNext(OnTooManyRedirect(cancelMsg, continueMsg))
    }

    override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
      if (delegate != null) {
        delegate.onReceivedError(view, errorCode, description, failingUrl)
      } else {
        super.onReceivedError(view, errorCode, description, failingUrl)
      }
      observer.onNext(OnReceivedError(errorCode, description, failingUrl))
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
      if (delegate != null) {
        delegate.onReceivedError(view, request, error)
      } else {
        super.onReceivedError(view, request, error)
      }
      observer.onNext(WebResourceOnReceivedError(request, error))
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
      if (delegate != null) {
        delegate.onReceivedHttpError(view, request, errorResponse)
      } else {
        super.onReceivedHttpError(view, request, errorResponse)
      }
      observer.onNext(OnReceivedHttpError(request, errorResponse))
    }

    override fun onFormResubmission(view: WebView, dontResend: Message, resend: Message) {
      if (delegate != null) {
        delegate.onFormResubmission(view, dontResend, resend)
      } else {
        super.onFormResubmission(view, dontResend, resend)
      }
      observer.onNext(OnFormResubmission(dontResend, resend))
    }

    override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
      if (delegate != null) {
        delegate.doUpdateVisitedHistory(view, url, isReload)
      } else {
        super.doUpdateVisitedHistory(view, url, isReload)
      }
      observer.onNext(DoUpdateVisitedHistory(url, isReload))
    }

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
      if (delegate != null) {
        delegate.onReceivedSslError(view, handler, error)
      } else {
        super.onReceivedSslError(view, handler, error)
      }
      observer.onNext(OnReceivedSslError(handler, error))
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onReceivedClientCertRequest(view: WebView, request: ClientCertRequest) {
      if (delegate != null) {
        delegate.onReceivedClientCertRequest(view, request)
      } else {
        super.onReceivedClientCertRequest(view, request)
      }
      observer.onNext(OnReceivedClientCertRequest(request))
    }

    override fun onReceivedHttpAuthRequest(view: WebView, handler: HttpAuthHandler, host: String, realm: String) {
      if (delegate != null) {
        delegate.onReceivedHttpAuthRequest(view, handler, host, realm)
      } else {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
      }
      observer.onNext(OnReceivedHttpAuthRequest(handler, host, realm))
    }

    override fun shouldOverrideKeyEvent(view: WebView, event: KeyEvent): Boolean {
      observer.onNext(ShouldOverrideKeyEvent(event))
      return delegate?.shouldOverrideKeyEvent(view, event) ?: super.shouldOverrideKeyEvent(view, event)
    }

    override fun onUnhandledKeyEvent(view: WebView, event: KeyEvent) {
      if (delegate != null) {
        delegate.onUnhandledKeyEvent(view, event)
      } else {
        super.onUnhandledKeyEvent(view, event)
      }
      observer.onNext(OnUnhandledKeyEvent(event))
    }

    override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
      if (delegate != null) {
        delegate.onScaleChanged(view, oldScale, newScale)
      } else {
        super.onScaleChanged(view, oldScale, newScale)
      }
      observer.onNext(OnScaleChanged(oldScale, newScale))
    }

    override fun onReceivedLoginRequest(view: WebView, realm: String, account: String?, args: String) {
      if (delegate != null) {
        delegate.onReceivedLoginRequest(view, realm, account, args)
      } else {
        super.onReceivedLoginRequest(view, realm, account, args)
      }
      observer.onNext(OnReceivedLoginRequest(realm, account!!, args))
    }
  }
}
