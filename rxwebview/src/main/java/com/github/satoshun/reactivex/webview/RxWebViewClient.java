package com.github.satoshun.reactivex.webview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webview.internal.ObjectHelper;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * static factory methods for creating {@linkplain io.reactivex.Completable} and {@linkplain io.reactivex.Observable}
 */
public class RxWebViewClient {

  /**
   * @deprecated please use events method
   * <p>
   * Create an completable which emits on {@code WebView} WebViewClient#onPageStarted event.
   * <p>
   * <em>Warning:</em> The method don't combine other method. if you want to subscribe a onPageStarted and
   * onPageFinished and other events, please use {@link #events(WebView, WebViewClient)} method.
   * </p>
   */
  @CheckResult @NonNull @Deprecated
  public static Completable onPageStarted(WebView webView, WebViewClient client) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    ObjectHelper.requireNonNull(client, "client is null");
    return Completable.create(new PageStartedOnSubscribe(webView, client));
  }

  /**
   * @deprecated please use events method
   * <p>
   * Create an completable which emits on {@code WebView} WebViewClient#onPageFinished event.
   * <p>
   * <em>Warning:</em> The method don't combine other method. if you want to subscribe a onPageStarted and
   * onPageFinished and other events, please use {@link #events(WebView, WebViewClient)} method.
   * </p>
   */
  @CheckResult @NonNull @Deprecated
  public static Completable onPageFinished(WebView webView, WebViewClient client) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    ObjectHelper.requireNonNull(client, "client is null");
    return Completable.create(new PageFinishedOnSubscribe(webView, client));
  }

  /**
   * @deprecated please use events method
   * <p>
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * Some data types are defined into {@link com.github.satoshun.reactivex.webview.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull @Deprecated
  public static Observable<RxWebViewClientData> all(WebView webView, WebViewClient client) {
    return events(webView, client);
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * Some data types are defined into {@link com.github.satoshun.reactivex.webview.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> events(WebView webView, WebViewClient client) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    ObjectHelper.requireNonNull(client, "client is null");
    return Observable.create(new AllOnSubscribe(webView, client));
  }
}
