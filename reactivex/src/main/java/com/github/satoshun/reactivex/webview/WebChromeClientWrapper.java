package com.github.satoshun.reactivex.webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

class WebChromeClientWrapper extends WebChromeClient {

  private final WebChromeClient actual;

  WebChromeClientWrapper(WebChromeClient actual) {
    this.actual = actual;
  }

  @Override public void onProgressChanged(WebView view, int newProgress) {
    actual.onProgressChanged(view, newProgress);
  }

  @Override public void onReceivedTitle(WebView view, String title) {
    actual.onReceivedTitle(view, title);
  }

  @Override public void onReceivedIcon(WebView view, Bitmap icon) {
    actual.onReceivedIcon(view, icon);
  }

  @Override public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
    actual.onReceivedTouchIconUrl(view, url, precomposed);
  }

  @Override public void onShowCustomView(View view, CustomViewCallback callback) {
    actual.onShowCustomView(view, callback);
  }

  @Override
  public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
    actual.onShowCustomView(view, requestedOrientation, callback);
  }

  @Override public void onHideCustomView() {
    actual.onHideCustomView();
  }

  @Override
  public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
    return actual.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
  }

  @Override public void onRequestFocus(WebView view) {
    actual.onRequestFocus(view);
  }

  @Override public void onCloseWindow(WebView window) {
    actual.onCloseWindow(window);
  }

  @Override public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
    return actual.onJsAlert(view, url, message, result);
  }

  @Override public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
    return actual.onJsConfirm(view, url, message, result);
  }

  @Override
  public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
    return actual.onJsPrompt(view, url, message, defaultValue, result);
  }

  @Override
  public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
    return actual.onJsBeforeUnload(view, url, message, result);
  }

  @Override
  public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
    actual.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
  }

  @Override
  public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
    actual.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
  }

  @Override
  public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
    actual.onGeolocationPermissionsShowPrompt(origin, callback);
  }

  @Override public void onGeolocationPermissionsHidePrompt() {
    actual.onGeolocationPermissionsHidePrompt();
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override public void onPermissionRequest(PermissionRequest request) {
    actual.onPermissionRequest(request);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override public void onPermissionRequestCanceled(PermissionRequest request) {
    actual.onPermissionRequestCanceled(request);
  }

  @Override public boolean onJsTimeout() {
    return actual.onJsTimeout();
  }

  @Override public void onConsoleMessage(String message, int lineNumber, String sourceID) {
    actual.onConsoleMessage(message, lineNumber, sourceID);
  }

  @Override public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
    return actual.onConsoleMessage(consoleMessage);
  }

  @Override public Bitmap getDefaultVideoPoster() {
    return actual.getDefaultVideoPoster();
  }

  @Override public View getVideoLoadingProgressView() {
    return actual.getVideoLoadingProgressView();
  }

  @Override public void getVisitedHistory(ValueCallback<String[]> callback) {
    actual.getVisitedHistory(callback);
  }

  @Override
  public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
    return actual.onShowFileChooser(webView, filePathCallback, fileChooserParams);
  }
}
