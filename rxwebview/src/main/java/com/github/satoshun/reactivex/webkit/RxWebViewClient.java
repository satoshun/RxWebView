package com.github.satoshun.reactivex.webkit;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.github.satoshun.reactivex.webkit.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webkit.internal.ObjectHelper;
import io.reactivex.Observable;

/**
 * static factory methods for creating {@linkplain io.reactivex.Completable} and {@linkplain io.reactivex.Observable}
 */
public class RxWebViewClient {

  /**
   * @deprecated please use {@link #events} method
   * <p>
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * Some data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull @Deprecated
  public static Observable<RxWebViewClientData> all(@NonNull WebView webView) {
    return events(webView);
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> events(@NonNull WebView webView) {
    return events(webView, null);
  }

  /**
   * Create an observable which emits on {@code WebView} WebViewClient event.
   * data types are defined into {@link com.github.satoshun.reactivex.webkit.data}.
   * It's corresponding to WebViewClient event.
   */
  @CheckResult @NonNull
  public static Observable<RxWebViewClientData> events(@NonNull WebView webView, @Nullable WebViewClient delegate) {
    ObjectHelper.requireNonNull(webView, "webView is null");
    return new AllOnObservable(webView, delegate);
  }
}
