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

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

final class AllOnSubscribe implements ObservableOnSubscribe<RxWebView.Event> {

  private final WebView webView;
  private final WebViewClient client;

  AllOnSubscribe(WebView webView, WebViewClient client) {
    this.webView = webView;
    this.client = client;
  }

  @Override public void subscribe(final ObservableEmitter<RxWebView.Event> e) throws Exception {
    webView.setWebViewClient(new WebViewClientWrapper(client) {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        e.onNext(RxWebView.Event.SHOULD_OVERRIDE_URL_LOADING);
        return super.shouldOverrideUrlLoading(view, url);
      }

      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        e.onNext(RxWebView.Event.SHOULD_OVERRIDE_URL_LOADING);
        return super.shouldOverrideUrlLoading(view, request);
      }

      @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        e.onNext(RxWebView.Event.ON_PAGE_STARTED);
      }

      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        e.onNext(RxWebView.Event.ON_PAGE_FINISHED);
      }

      @Override public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        e.onNext(RxWebView.Event.ON_LOAD_RESOURCE);
      }

      @RequiresApi(api = Build.VERSION_CODES.M)
      @Override public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
        e.onNext(RxWebView.Event.ON_PAGE_COMMIT_VISIBLE);
      }

      @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        e.onNext(RxWebView.Event.SHOULD_INTERCEPT_REQUEST);
        return super.shouldInterceptRequest(view, url);
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
      public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        e.onNext(RxWebView.Event.SHOULD_INTERCEPT_REQUEST);
        return super.shouldInterceptRequest(view, request);
      }

      @Override
      public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg);
        e.onNext(RxWebView.Event.ON_TOO_MANY_REDIRECTS);
      }

      @Override
      public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        e.onNext(RxWebView.Event.ON_RECEIVED_ERROR);
      }

      @RequiresApi(api = Build.VERSION_CODES.M) @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        e.onNext(RxWebView.Event.ON_RECEIVED_ERROR);
      }

      @RequiresApi(api = Build.VERSION_CODES.M) @Override
      public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        e.onNext(RxWebView.Event.ON_RECEIVED_HTTP_ERROR);
      }

      @Override public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        super.onFormResubmission(view, dontResend, resend);
        e.onNext(RxWebView.Event.ON_FORM_RESUBMISSION);
      }

      @Override public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
        e.onNext(RxWebView.Event.DO_UPDATE_VISITED_HISTORY);
      }

      @Override
      public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        e.onNext(RxWebView.Event.ON_RECEIVED_SSL_ERROR);
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        super.onReceivedClientCertRequest(view, request);
        e.onNext(RxWebView.Event.ON_RECEIVED_CLIENT_CERT_REQUEST);
      }

      @Override
      public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
        e.onNext(RxWebView.Event.ON_RECEIVED_HTTP_AUTH_REQUEST);
      }

      @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        e.onNext(RxWebView.Event.SHOULD_OVERRIDE_KEY_EVENT);
        return super.shouldOverrideKeyEvent(view, event);
      }

      @Override public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        super.onUnhandledKeyEvent(view, event);
        e.onNext(RxWebView.Event.ON_UNHANDLED_KEY_EVENT);
      }

      @Override public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        e.onNext(RxWebView.Event.ON_SCALE_CHANGED);
      }

      @Override
      public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        super.onReceivedLoginRequest(view, realm, account, args);
        e.onNext(RxWebView.Event.ON_RECEIVED_LOGIN_REQUEST);
      }
    });
    e.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        webView.setWebViewClient(null);
      }
    });
  }
}
