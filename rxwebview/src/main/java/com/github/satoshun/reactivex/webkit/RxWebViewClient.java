package com.github.satoshun.reactivex.webkit;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webkit.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webkit.internal.ObjectHelper;

import io.reactivex.Observable;

/**
 * static factory methods for creating {@linkplain io.reactivex.Completable} and {@linkplain io.reactivex.Observable}
 */
public class RxWebViewClient {

  /**
   * @deprecated please use events method
   * <p>
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * Some data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull @Deprecated
  public static Observable<RxWebViewClientData> all(
      @NonNull WebView webView, @Nullable WebViewClient client
  ) {
    return events(webView, client);
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> events(@NonNull WebView webView) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    return events(webView, null);
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> events(
      @NonNull WebView webView, @Nullable WebViewClient client
  ) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    return new AllOnObservable(webView, client);
  }
}
