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

import com.github.satoshun.reactivex.webview.data.GetDefaultVideoPoster;
import com.github.satoshun.reactivex.webview.data.GetVideoLoadingProgressView;
import com.github.satoshun.reactivex.webview.data.GetVisitedHistory;
import com.github.satoshun.reactivex.webview.data.OnCloseWindow;
import com.github.satoshun.reactivex.webview.data.OnConsoleMessage;
import com.github.satoshun.reactivex.webview.data.OnConsoleMessageNew;
import com.github.satoshun.reactivex.webview.data.OnCreateWindow;
import com.github.satoshun.reactivex.webview.data.OnExceededDatabaseQuota;
import com.github.satoshun.reactivex.webview.data.OnGeolocationPermissionsHidePrompt;
import com.github.satoshun.reactivex.webview.data.OnGeolocationPermissionsShowPrompt;
import com.github.satoshun.reactivex.webview.data.OnHideCustomView;
import com.github.satoshun.reactivex.webview.data.OnJsAlert;
import com.github.satoshun.reactivex.webview.data.OnJsBeforeUnload;
import com.github.satoshun.reactivex.webview.data.OnJsConfirm;
import com.github.satoshun.reactivex.webview.data.OnJsPrompt;
import com.github.satoshun.reactivex.webview.data.OnJsTimeout;
import com.github.satoshun.reactivex.webview.data.OnPermissionRequest;
import com.github.satoshun.reactivex.webview.data.OnPermissionRequestCanceled;
import com.github.satoshun.reactivex.webview.data.OnProgressChanged;
import com.github.satoshun.reactivex.webview.data.OnReachedMaxAppCacheSize;
import com.github.satoshun.reactivex.webview.data.OnReceivedIcon;
import com.github.satoshun.reactivex.webview.data.OnReceivedTitle;
import com.github.satoshun.reactivex.webview.data.OnReceivedTouchIconUrl;
import com.github.satoshun.reactivex.webview.data.OnRequestFocus;
import com.github.satoshun.reactivex.webview.data.OnShowCustomView;
import com.github.satoshun.reactivex.webview.data.OnShowCustomViewRequestedOrientation;
import com.github.satoshun.reactivex.webview.data.OnShowFileChooser;
import com.github.satoshun.reactivex.webview.data.RxWebChromeClientData;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

final class AllChromeOnSubscribe implements ObservableOnSubscribe<RxWebChromeClientData> {

  private final WebView webView;
  private final WebChromeClient client;

  AllChromeOnSubscribe(WebView webView, WebChromeClient client) {
    this.webView = webView;
    this.client = client;
  }

  @Override
  public void subscribe(final ObservableEmitter<RxWebChromeClientData> e) throws Exception {
    webView.setWebChromeClient(new WebChromeClientWrapper(client) {
      @Override public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        e.onNext(new OnProgressChanged(newProgress));
      }

      @Override public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        e.onNext(new OnReceivedTitle(title));
      }

      @Override public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
        e.onNext(new OnReceivedIcon(icon));
      }

      @Override public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        e.onNext(new OnReceivedTouchIconUrl(url, precomposed));
      }

      @Override public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        e.onNext(new OnShowCustomView(callback));
      }

      @Override
      public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        super.onShowCustomView(view, requestedOrientation, callback);
        e.onNext(new OnShowCustomViewRequestedOrientation(requestedOrientation, callback));
      }

      @Override public void onHideCustomView() {
        super.onHideCustomView();
        e.onNext(new OnHideCustomView());
      }

      @Override
      public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        e.onNext(new OnCreateWindow(isDialog, isUserGesture, resultMsg));
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
      }

      @Override public void onRequestFocus(WebView view) {
        super.onRequestFocus(view);
        e.onNext(new OnRequestFocus());
      }

      @Override public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
        e.onNext(new OnCloseWindow());
      }

      @Override
      public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        e.onNext(new OnJsAlert(url, message, result));
        return super.onJsAlert(view, url, message, result);
      }

      @Override
      public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        e.onNext(new OnJsConfirm(url, message, result));
        return super.onJsConfirm(view, url, message, result);
      }

      @Override
      public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        e.onNext(new OnJsPrompt(url, message, defaultValue, result));
        return super.onJsPrompt(view, url, message, defaultValue, result);
      }

      @Override
      public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        e.onNext(new OnJsBeforeUnload(url, message, result));
        return super.onJsBeforeUnload(view, url, message, result);
      }

      @Override
      public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        e.onNext(new OnExceededDatabaseQuota(databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater));
      }

      @Override
      public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        e.onNext(new OnReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater));
      }

      @Override
      public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        e.onNext(new OnGeolocationPermissionsShowPrompt(origin, callback));
      }

      @Override public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
        e.onNext(new OnGeolocationPermissionsHidePrompt());
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
        e.onNext(new OnPermissionRequest(request));
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override public void onPermissionRequestCanceled(PermissionRequest request) {
        super.onPermissionRequestCanceled(request);
        e.onNext(new OnPermissionRequestCanceled(request));
      }

      @Override public boolean onJsTimeout() {
        e.onNext(new OnJsTimeout());
        return super.onJsTimeout();
      }

      @Override public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        super.onConsoleMessage(message, lineNumber, sourceID);
        e.onNext(new OnConsoleMessage(message, lineNumber, sourceID));
      }

      @Override public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        e.onNext(new OnConsoleMessageNew(consoleMessage));
        return super.onConsoleMessage(consoleMessage);
      }

      @Override public Bitmap getDefaultVideoPoster() {
        e.onNext(new GetDefaultVideoPoster());
        return super.getDefaultVideoPoster();
      }

      @Override public View getVideoLoadingProgressView() {
        e.onNext(new GetVideoLoadingProgressView());
        return super.getVideoLoadingProgressView();
      }

      @Override public void getVisitedHistory(ValueCallback<String[]> callback) {
        super.getVisitedHistory(callback);
        e.onNext(new GetVisitedHistory(callback));
      }

      @Override
      public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        e.onNext(new OnShowFileChooser(filePathCallback, fileChooserParams));
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
      }
    });

    e.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        webView.setWebChromeClient(null);
      }
    });
  }
}
