package com.github.satoshun.reactivex.webview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webview.data.RxWebViewData;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class RxWebView {

  @CheckResult @NonNull
  public static Completable onPageStarted(WebView webView, WebViewClient client) {
    return Completable.create(new PageStartedOnSubscribe(webView, client));
  }

  @CheckResult @NonNull
  public static Completable onPageFinished(WebView webView, WebViewClient client) {
    return Completable.create(new PageFinishedOnSubscribe(webView, client));
  }

  @CheckResult @NonNull
  public static Observable<RxWebViewData> all(WebView webView, WebViewClient client) {
    return Observable.create(new AllOnSubscribe(webView, client));
  }
}
