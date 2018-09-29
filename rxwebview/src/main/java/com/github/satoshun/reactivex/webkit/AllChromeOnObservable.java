package com.github.satoshun.reactivex.webkit;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.github.satoshun.reactivex.webkit.data.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class AllChromeOnObservable extends Observable<RxWebChromeClientData> {

  private final WebView webView;
  @Nullable private final WebChromeClient delegate;

  AllChromeOnObservable(WebView webView, @Nullable WebChromeClient delegate) {
    this.webView = webView;
    this.delegate = delegate;
  }

  @Override
  protected void subscribeActual(Observer<? super RxWebChromeClientData> observer) {
    webView.setWebChromeClient(new ClientWrapper(observer, delegate));
    observer.onSubscribe(new MainThreadDisposable() {
      @Override
      protected void onDispose() {
        webView.setWebChromeClient(null);
      }
    });
  }

  static class ClientWrapper extends WebChromeClient {
    private final Observer<? super RxWebChromeClientData> observer;
    @Nullable
    private final WebChromeClient delegate;

    ClientWrapper(Observer<? super RxWebChromeClientData> observer, @Nullable WebChromeClient delegate) {
      super();
      this.observer = observer;
      this.delegate = delegate;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
      if (delegate != null) {
        delegate.onProgressChanged(view, newProgress);
      } else {
        super.onProgressChanged(view, newProgress);
      }
      observer.onNext(new OnProgressChanged(newProgress));
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
      if (delegate != null) {
        delegate.onReceivedTitle(view, title);
      } else {
        super.onReceivedTitle(view, title);
      }
      observer.onNext(new OnReceivedTitle(title));
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
      if (delegate != null) {
        delegate.onReceivedIcon(view, icon);
      } else {
        super.onReceivedIcon(view, icon);
      }
      observer.onNext(new OnReceivedIcon(icon));
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
      if (delegate != null) {
        delegate.onReceivedTouchIconUrl(view, url, precomposed);
      } else {
        super.onReceivedTouchIconUrl(view, url, precomposed);
      }
      observer.onNext(new OnReceivedTouchIconUrl(url, precomposed));
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
      if (delegate != null) {
        delegate.onShowCustomView(view, callback);
      } else {
        super.onShowCustomView(view, callback);
      }
      observer.onNext(new OnShowCustomView(callback));
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
      if (delegate != null) {
        delegate.onShowCustomView(view, requestedOrientation, callback);
      } else {
        super.onShowCustomView(view, requestedOrientation, callback);
      }
      observer.onNext(new OnShowCustomViewRequestedOrientation(requestedOrientation, callback));
    }

    @Override
    public void onHideCustomView() {
      if (delegate != null) {
        delegate.onHideCustomView();
      } else {
        super.onHideCustomView();
      }
      observer.onNext(new OnHideCustomView());
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
      observer.onNext(new OnCreateWindow(isDialog, isUserGesture, resultMsg));
      if (delegate != null) {
        return delegate.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
      } else {
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
      }
    }

    @Override
    public void onRequestFocus(WebView view) {
      if (delegate != null) {
        delegate.onRequestFocus(view);
      } else {
        super.onRequestFocus(view);
      }
      observer.onNext(new OnRequestFocus());
    }

    @Override
    public void onCloseWindow(WebView window) {
      if (delegate != null) {
        delegate.onCloseWindow(window);
      } else {
        super.onCloseWindow(window);
      }
      observer.onNext(new OnCloseWindow());
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsAlert(url, message, result));
      if (delegate != null) {
        return delegate.onJsAlert(view, url, message, result);
      } else {
        return super.onJsAlert(view, url, message, result);
      }
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsConfirm(url, message, result));
      if (delegate != null) {
        return delegate.onJsConfirm(view, url, message, result);
      } else {
        return super.onJsConfirm(view, url, message, result);
      }
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
      observer.onNext(new OnJsPrompt(url, message, defaultValue, result));
      if (delegate != null) {
        return delegate.onJsPrompt(view, url, message, defaultValue, result);
      } else {
        return super.onJsPrompt(view, url, message, defaultValue, result);
      }
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
      observer.onNext(new OnJsBeforeUnload(url, message, result));
      if (delegate != null) {
        return delegate.onJsBeforeUnload(view, url, message, result);
      } else {
        return super.onJsBeforeUnload(view, url, message, result);
      }
    }

    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
      if (delegate != null) {
        delegate.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
      } else {
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
      }
      observer.onNext(new OnExceededDatabaseQuota(databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater));
    }

    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
      if (delegate != null) {
        delegate.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
      } else {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
      }
      observer.onNext(new OnReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater));
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
      if (delegate != null) {
        delegate.onGeolocationPermissionsShowPrompt(origin, callback);
      } else {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
      }
      observer.onNext(new OnGeolocationPermissionsShowPrompt(origin, callback));
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
      if (delegate != null) {
        delegate.onGeolocationPermissionsHidePrompt();
      } else {
        super.onGeolocationPermissionsHidePrompt();
      }
      observer.onNext(new OnGeolocationPermissionsHidePrompt());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPermissionRequest(PermissionRequest request) {
      if (delegate != null) {
        delegate.onPermissionRequest(request);
      } else {
        super.onPermissionRequest(request);
      }
      observer.onNext(new OnPermissionRequest(request));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
      if (delegate != null) {
        delegate.onPermissionRequestCanceled(request);
      } else {
        super.onPermissionRequestCanceled(request);
      }
      observer.onNext(new OnPermissionRequestCanceled(request));
    }

    @Override
    public boolean onJsTimeout() {
      observer.onNext(new OnJsTimeout());
      if (delegate != null) {
        return delegate.onJsTimeout();
      } else {
        return super.onJsTimeout();
      }
    }

    @Override
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
      if (delegate != null) {
        delegate.onConsoleMessage(message, lineNumber, sourceID);
      } else {
        super.onConsoleMessage(message, lineNumber, sourceID);
      }
      observer.onNext(new OnConsoleMessage(message, lineNumber, sourceID));
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
      observer.onNext(new OnConsoleMessageNew(consoleMessage));
      if (delegate != null) {
        return delegate.onConsoleMessage(consoleMessage);
      } else {
        return super.onConsoleMessage(consoleMessage);
      }
    }

    @Override
    public Bitmap getDefaultVideoPoster() {
      observer.onNext(new GetDefaultVideoPoster());
      if (delegate != null) {
        return delegate.getDefaultVideoPoster();
      } else {
        return super.getDefaultVideoPoster();
      }
    }

    @Override
    public View getVideoLoadingProgressView() {
      observer.onNext(new GetVideoLoadingProgressView());
      if (delegate != null) {
        return delegate.getVideoLoadingProgressView();
      } else {
        return super.getVideoLoadingProgressView();
      }
    }

    @Override
    public void getVisitedHistory(ValueCallback<String[]> callback) {
      if (delegate != null) {
        delegate.getVisitedHistory(callback);
      } else {
        super.getVisitedHistory(callback);
      }
      observer.onNext(new GetVisitedHistory(callback));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
      observer.onNext(new OnShowFileChooser(filePathCallback, fileChooserParams));
      if (delegate != null) {
        return delegate.onShowFileChooser(webView, filePathCallback, fileChooserParams);
      } else {
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
      }
    }
  }
}
