package com.github.satoshun.reactivex.webkit;

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

class WebViewClientWrapper extends WebViewClient {

  private final WebViewClient actual;

  WebViewClientWrapper(WebViewClient actual) {
    this.actual = actual;
  }

  @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
    return actual.shouldOverrideUrlLoading(view, url);
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
    return actual.shouldOverrideUrlLoading(view, request);
  }

  @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
    actual.onPageStarted(view, url, favicon);
  }

  @Override public void onPageFinished(WebView view, String url) {
    actual.onPageFinished(view, url);
  }

  @Override public void onLoadResource(WebView view, String url) {
    actual.onLoadResource(view, url);
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override public void onPageCommitVisible(WebView view, String url) {
    actual.onPageCommitVisible(view, url);
  }

  @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
    return actual.shouldInterceptRequest(view, url);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
  public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
    return actual.shouldInterceptRequest(view, request);
  }

  @Override public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
    actual.onTooManyRedirects(view, cancelMsg, continueMsg);
  }

  @Override
  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
    actual.onReceivedError(view, errorCode, description, failingUrl);
  }

  @RequiresApi(api = Build.VERSION_CODES.M) @Override
  public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
    actual.onReceivedError(view, request, error);
  }

  @RequiresApi(api = Build.VERSION_CODES.M) @Override
  public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
    actual.onReceivedHttpError(view, request, errorResponse);
  }

  @Override public void onFormResubmission(WebView view, Message dontResend, Message resend) {
    actual.onFormResubmission(view, dontResend, resend);
  }

  @Override public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
    actual.doUpdateVisitedHistory(view, url, isReload);
  }

  @Override public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    actual.onReceivedSslError(view, handler, error);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
    actual.onReceivedClientCertRequest(view, request);
  }

  @Override
  public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
    actual.onReceivedHttpAuthRequest(view, handler, host, realm);
  }

  @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
    return actual.shouldOverrideKeyEvent(view, event);
  }

  @Override public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
    actual.onUnhandledKeyEvent(view, event);
  }

  @Override public void onScaleChanged(WebView view, float oldScale, float newScale) {
    actual.onScaleChanged(view, oldScale, newScale);
  }

  @Override
  public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
    actual.onReceivedLoginRequest(view, realm, account, args);
  }
}
