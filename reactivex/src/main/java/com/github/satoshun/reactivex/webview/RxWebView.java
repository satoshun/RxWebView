package com.github.satoshun.reactivex.webview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
  public static Observable<Event> all(WebView webView, WebViewClient client) {
    return Observable.create(new AllOnSubscribe(webView, client));
  }

  public enum Event {
    SHOULD_OVERRIDE_URL_LOADING, ON_PAGE_STARTED, ON_PAGE_FINISHED,
    ON_LOAD_RESOURCE, ON_PAGE_COMMIT_VISIBLE, SHOULD_INTERCEPT_REQUEST,
    ON_TOO_MANY_REDIRECTS, ON_RECEIVED_ERROR, ON_RECEIVED_HTTP_ERROR,
    ON_FORM_RESUBMISSION, DO_UPDATE_VISITED_HISTORY, ON_RECEIVED_SSL_ERROR,
    ON_RECEIVED_CLIENT_CERT_REQUEST, ON_RECEIVED_HTTP_AUTH_REQUEST,
    SHOULD_OVERRIDE_KEY_EVENT, ON_UNHANDLED_KEY_EVENT, ON_SCALE_CHANGED,
    ON_RECEIVED_LOGIN_REQUEST
  }
}
