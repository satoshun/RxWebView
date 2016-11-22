package com.github.satoshun.reactivex.webview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.reactivex.Completable;

public class RxWebView {

  @CheckResult @NonNull
  public static Completable onPageFinished(WebView webView, WebViewClient client) {
    return Completable.create(new PageFinishedOnSubscribe(webView, client));
  }

  @CheckResult @NonNull
  public static Completable onPageStarted(WebView webView, WebViewClient client) {
    return Completable.create(new PageStartedOnSubscribe(webView, client));
  }
}
