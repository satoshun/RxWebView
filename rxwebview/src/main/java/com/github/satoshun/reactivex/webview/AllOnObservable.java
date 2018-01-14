package com.github.satoshun.reactivex.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webview.data.DoUpdateVisitedHistory;
import com.github.satoshun.reactivex.webview.data.OnFormResubmission;
import com.github.satoshun.reactivex.webview.data.OnLoadResource;
import com.github.satoshun.reactivex.webview.data.OnPageCommitVisible;
import com.github.satoshun.reactivex.webview.data.OnPageFinished;
import com.github.satoshun.reactivex.webview.data.OnPageStarted;
import com.github.satoshun.reactivex.webview.data.OnReceivedClientCertRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedError;
import com.github.satoshun.reactivex.webview.data.OnReceivedHttpAuthRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedHttpError;
import com.github.satoshun.reactivex.webview.data.OnReceivedLoginRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedSslError;
import com.github.satoshun.reactivex.webview.data.OnScaleChanged;
import com.github.satoshun.reactivex.webview.data.OnTooManyRedirect;
import com.github.satoshun.reactivex.webview.data.OnUnhandledKeyEvent;
import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptRequest;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptWebResourceRequest;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideKeyEvent;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideUrlLoading;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideUrlLoadingWebResourceRequest;
import com.github.satoshun.reactivex.webview.data.WebResourceOnReceivedError;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class AllOnObservable extends Observable<RxWebViewClientData> {

  private final WebView webView;
  private final WebViewClient original;

  AllOnObservable(WebView webView, WebViewClient original) {
    this.webView = webView;
    this.original = original;
  }

  @Override protected void subscribeActual(final Observer<? super RxWebViewClientData> observer) {
    MainThreadDisposable.verifyMainThread();

    webView.setWebViewClient(new ClientWrapper(original, observer));
    observer.onSubscribe(new MainThreadDisposable() {
      @Override protected void onDispose() {
        webView.setWebViewClient(original);
      }
    });
  }

  static class ClientWrapper extends WebViewClientWrapper {
    private final Observer<? super RxWebViewClientData> observer;

    ClientWrapper(WebViewClient actual, Observer<? super RxWebViewClientData> observer) {
      super(actual);
      this.observer = observer;
    }

    @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
      observer.onNext(new ShouldOverrideUrlLoading(url));
      return super.shouldOverrideUrlLoading(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      observer.onNext(new ShouldOverrideUrlLoadingWebResourceRequest(request));
      return super.shouldOverrideUrlLoading(view, request);
    }

    @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
      observer.onNext(new OnPageStarted(url, favicon));
    }

    @Override public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
      observer.onNext(new OnPageFinished(url));
    }

    @Override public void onLoadResource(WebView view, String url) {
      super.onLoadResource(view, url);
      observer.onNext(new OnLoadResource(url));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override public void onPageCommitVisible(WebView view, String url) {
      super.onPageCommitVisible(view, url);
      observer.onNext(new OnPageCommitVisible(url));
    }

    @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
      observer.onNext(new ShouldInterceptRequest(url));
      return super.shouldInterceptRequest(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
      observer.onNext(new ShouldInterceptWebResourceRequest(request));
      return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
      super.onTooManyRedirects(view, cancelMsg, continueMsg);
      observer.onNext(new OnTooManyRedirect(cancelMsg, continueMsg));
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
      super.onReceivedError(view, errorCode, description, failingUrl);
      observer.onNext(new OnReceivedError(errorCode, description, failingUrl));
    }

    @RequiresApi(api = Build.VERSION_CODES.M) @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
      super.onReceivedError(view, request, error);
      observer.onNext(new WebResourceOnReceivedError(request, error));
    }

    @RequiresApi(api = Build.VERSION_CODES.M) @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
      super.onReceivedHttpError(view, request, errorResponse);
      observer.onNext(new OnReceivedHttpError(request, errorResponse));
    }

    @Override public void onFormResubmission(WebView view, Message dontResend, Message resend) {
      super.onFormResubmission(view, dontResend, resend);
      observer.onNext(new OnFormResubmission(dontResend, resend));
    }

    @Override public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
      super.doUpdateVisitedHistory(view, url, isReload);
      observer.onNext(new DoUpdateVisitedHistory(url, isReload));
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      super.onReceivedSslError(view, handler, error);
      observer.onNext(new OnReceivedSslError(handler, error));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
      super.onReceivedClientCertRequest(view, request);
      observer.onNext(new OnReceivedClientCertRequest(request));
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
      super.onReceivedHttpAuthRequest(view, handler, host, realm);
      observer.onNext(new OnReceivedHttpAuthRequest(handler, host, realm));
    }

    @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
      observer.onNext(new ShouldOverrideKeyEvent(event));
      return super.shouldOverrideKeyEvent(view, event);
    }

    @Override public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
      super.onUnhandledKeyEvent(view, event);
      observer.onNext(new OnUnhandledKeyEvent(event));
    }

    @Override public void onScaleChanged(WebView view, float oldScale, float newScale) {
      super.onScaleChanged(view, oldScale, newScale);
      observer.onNext(new OnScaleChanged(oldScale, newScale));
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
      super.onReceivedLoginRequest(view, realm, account, args);
      observer.onNext(new OnReceivedLoginRequest(realm, account, args));
    }
  }
}
