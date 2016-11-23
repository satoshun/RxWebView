package com.github.satoshun.reactivex.webview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * static factory methods for creating {@linkplain io.reactivex.Completable} and {@linkplain io.reactivex.Observable}
 */
public class RxWebView {

  /**
   * Create an completable which emits on {@code WebView} WebViewClient#onPageStarted event.
   * <p>
   * <em>Warning:</em> The method don't combine other method. if you want to subscribe a onPageStarted and
   * onPageFinished and other events, please use {@link #all} method.
   * </p>
   */
  @CheckResult @NonNull
  public static Completable onPageStarted(WebView webView, WebViewClient client) {
    return Completable.create(new PageStartedOnSubscribe(webView, client));
  }

  /**
   * Create an completable which emits on {@code WebView} WebViewClient#onPageFinished event.
   * <p>
   * <em>Warning:</em> The method don't combine other method. if you want to subscribe a onPageStarted and
   * onPageFinished and other events, please use {@link #all} method.
   * </p>
   */
  @CheckResult @NonNull
  public static Completable onPageFinished(WebView webView, WebViewClient client) {
    return Completable.create(new PageFinishedOnSubscribe(webView, client));
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * Some data types are defined into {@link com.github.satoshun.reactivex.webview.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> all(WebView webView, WebViewClient client) {
    return Observable.create(new AllOnSubscribe(webView, client));
  }
}
