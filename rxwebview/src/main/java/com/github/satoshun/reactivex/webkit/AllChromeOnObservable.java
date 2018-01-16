package com.github.satoshun.reactivex.webkit;

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

import com.github.satoshun.reactivex.webkit.data.GetDefaultVideoPoster;
import com.github.satoshun.reactivex.webkit.data.GetVideoLoadingProgressView;
import com.github.satoshun.reactivex.webkit.data.GetVisitedHistory;
import com.github.satoshun.reactivex.webkit.data.OnCloseWindow;
import com.github.satoshun.reactivex.webkit.data.OnConsoleMessage;
import com.github.satoshun.reactivex.webkit.data.OnConsoleMessageNew;
import com.github.satoshun.reactivex.webkit.data.OnCreateWindow;
import com.github.satoshun.reactivex.webkit.data.OnExceededDatabaseQuota;
import com.github.satoshun.reactivex.webkit.data.OnGeolocationPermissionsHidePrompt;
import com.github.satoshun.reactivex.webkit.data.OnGeolocationPermissionsShowPrompt;
import com.github.satoshun.reactivex.webkit.data.OnHideCustomView;
import com.github.satoshun.reactivex.webkit.data.OnJsAlert;
import com.github.satoshun.reactivex.webkit.data.OnJsBeforeUnload;
import com.github.satoshun.reactivex.webkit.data.OnJsConfirm;
import com.github.satoshun.reactivex.webkit.data.OnJsPrompt;
import com.github.satoshun.reactivex.webkit.data.OnJsTimeout;
import com.github.satoshun.reactivex.webkit.data.OnPermissionRequest;
import com.github.satoshun.reactivex.webkit.data.OnPermissionRequestCanceled;
import com.github.satoshun.reactivex.webkit.data.OnProgressChanged;
import com.github.satoshun.reactivex.webkit.data.OnReachedMaxAppCacheSize;
import com.github.satoshun.reactivex.webkit.data.OnReceivedIcon;
import com.github.satoshun.reactivex.webkit.data.OnReceivedTitle;
import com.github.satoshun.reactivex.webkit.data.OnReceivedTouchIconUrl;
import com.github.satoshun.reactivex.webkit.data.OnRequestFocus;
import com.github.satoshun.reactivex.webkit.data.OnShowCustomView;
import com.github.satoshun.reactivex.webkit.data.OnShowCustomViewRequestedOrientation;
import com.github.satoshun.reactivex.webkit.data.OnShowFileChooser;
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class AllChromeOnObservable extends Observable<RxWebChromeClientData> {

  private final WebView webView;

  AllChromeOnObservable(WebView webView) {
    this.webView = webView;
  }

  @Override protected void subscribeActual(Observer<? super RxWebChromeClientData> observer) {
    webView.setWebChromeClient(new ClientWrapper(observer));
    observer.onSubscribe(new MainThreadDisposable() {
      @Override protected void onDispose() {
        webView.setWebChromeClient(null);
      }
    });
  }

  static class ClientWrapper extends WebChromeClient {
    private final Observer<? super RxWebChromeClientData> observer;

    ClientWrapper(Observer<? super RxWebChromeClientData> observer) {
      super();
      this.observer = observer;
    }

    @Override public void onProgressChanged(WebView view, int newProgress) {
      super.onProgressChanged(view, newProgress);
      observer.onNext(new OnProgressChanged(newProgress));
    }

    @Override public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
      observer.onNext(new OnReceivedTitle(title));
    }

    @Override public void onReceivedIcon(WebView view, Bitmap icon) {
      super.onReceivedIcon(view, icon);
      observer.onNext(new OnReceivedIcon(icon));
    }

    @Override public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
      super.onReceivedTouchIconUrl(view, url, precomposed);
      observer.onNext(new OnReceivedTouchIconUrl(url, precomposed));
    }

    @Override public void onShowCustomView(View view, CustomViewCallback callback) {
      super.onShowCustomView(view, callback);
      observer.onNext(new OnShowCustomView(callback));
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
      super.onShowCustomView(view, requestedOrientation, callback);
      observer.onNext(new OnShowCustomViewRequestedOrientation(requestedOrientation, callback));
    }

    @Override public void onHideCustomView() {
      super.onHideCustomView();
      observer.onNext(new OnHideCustomView());
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
      observer.onNext(new OnCreateWindow(isDialog, isUserGesture, resultMsg));
      return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
    }

    @Override public void onRequestFocus(WebView view) {
      super.onRequestFocus(view);
      observer.onNext(new OnRequestFocus());
    }

    @Override public void onCloseWindow(WebView window) {
      super.onCloseWindow(window);
      observer.onNext(new OnCloseWindow());
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsAlert(url, message, result));
      return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsConfirm(url, message, result));
      return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
      observer.onNext(new OnJsPrompt(url, message, defaultValue, result));
      return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsBeforeUnload(url, message, result));
      return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
      super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
      observer.onNext(new OnExceededDatabaseQuota(databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater));
    }

    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
      super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
      observer.onNext(new OnReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater));
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
      super.onGeolocationPermissionsShowPrompt(origin, callback);
      observer.onNext(new OnGeolocationPermissionsShowPrompt(origin, callback));
    }

    @Override public void onGeolocationPermissionsHidePrompt() {
      super.onGeolocationPermissionsHidePrompt();
      observer.onNext(new OnGeolocationPermissionsHidePrompt());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onPermissionRequest(PermissionRequest request) {
      super.onPermissionRequest(request);
      observer.onNext(new OnPermissionRequest(request));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onPermissionRequestCanceled(PermissionRequest request) {
      super.onPermissionRequestCanceled(request);
      observer.onNext(new OnPermissionRequestCanceled(request));
    }

    @Override public boolean onJsTimeout() {
      observer.onNext(new OnJsTimeout());
      return super.onJsTimeout();
    }

    @Override public void onConsoleMessage(String message, int lineNumber, String sourceID) {
      super.onConsoleMessage(message, lineNumber, sourceID);
      observer.onNext(new OnConsoleMessage(message, lineNumber, sourceID));
    }

    @Override public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
      observer.onNext(new OnConsoleMessageNew(consoleMessage));
      return super.onConsoleMessage(consoleMessage);
    }

    @Override public Bitmap getDefaultVideoPoster() {
      observer.onNext(new GetDefaultVideoPoster());
      return super.getDefaultVideoPoster();
    }

    @Override public View getVideoLoadingProgressView() {
      observer.onNext(new GetVideoLoadingProgressView());
      return super.getVideoLoadingProgressView();
    }

    @Override public void getVisitedHistory(ValueCallback<String[]> callback) {
      super.getVisitedHistory(callback);
      observer.onNext(new GetVisitedHistory(callback));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
      observer.onNext(new OnShowFileChooser(filePathCallback, fileChooserParams));
      return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }
  }
}
