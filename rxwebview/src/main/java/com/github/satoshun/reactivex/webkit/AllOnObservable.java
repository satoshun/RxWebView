package com.github.satoshun.reactivex.webkit;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.github.satoshun.reactivex.webkit.data.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class AllOnObservable extends Observable<RxWebViewClientData> {

  private final WebView webView;
  private final WebViewClient delegate;

  AllOnObservable(WebView webView, @Nullable WebViewClient delegate) {
    this.webView = webView;
    this.delegate = delegate;
  }

  @Override protected void subscribeActual(final Observer<? super RxWebViewClientData> observer) {
    MainThreadDisposable.verifyMainThread();

    webView.setWebViewClient(new ClientWrapper(observer, delegate));
    observer.onSubscribe(new MainThreadDisposable() {
      @Override protected void onDispose() {
        webView.setWebViewClient(null);
      }
    });
  }

  static class ClientWrapper extends WebViewClient {
    private final Observer<? super RxWebViewClientData> observer;
    @Nullable private final WebViewClient delegate;

    ClientWrapper(
        Observer<? super RxWebViewClientData> observer,
        @Nullable WebViewClient delegate
    ) {
      super();
      this.observer = observer;
      this.delegate = delegate;
    }

    @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
      observer.onNext(new ShouldOverrideUrlLoading(url));
      if (delegate != null) {
        return delegate.shouldOverrideUrlLoading(view, url);
      }
      return super.shouldOverrideUrlLoading(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      observer.onNext(new ShouldOverrideUrlLoadingWebResourceRequest(request));
      if (delegate != null) {
        return delegate.shouldOverrideUrlLoading(view, request);
      }
      return super.shouldOverrideUrlLoading(view, request);
    }

    @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
      if (delegate != null) {
        delegate.onPageStarted(view, url, favicon);
      } else {
        super.onPageStarted(view, url, favicon);
      }
      observer.onNext(new OnPageStarted(url, favicon));
    }

    @Override public void onPageFinished(WebView view, String url) {
      if (delegate != null) {
        delegate.onPageFinished(view, url);
      } else {
        super.onPageFinished(view, url);
      }
      observer.onNext(new OnPageFinished(url));
    }

    @Override public void onLoadResource(WebView view, String url) {
      if (delegate != null) {
        delegate.onLoadResource(view, url);
      } else {
        super.onLoadResource(view, url);
      }
      observer.onNext(new OnLoadResource(url));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override public void onPageCommitVisible(WebView view, String url) {
      if (delegate != null) {
        delegate.onPageCommitVisible(view, url);
      } else {
        super.onPageCommitVisible(view, url);
      }
      observer.onNext(new OnPageCommitVisible(url));
    }

    @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
      observer.onNext(new ShouldInterceptRequest(url));
      if (delegate != null) {
        return delegate.shouldInterceptRequest(view, url);
      }
      return super.shouldInterceptRequest(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
      observer.onNext(new ShouldInterceptWebResourceRequest(request));
      if (delegate != null) {
        return delegate.shouldInterceptRequest(view, request);
      }
      return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
      if (delegate != null) {
        delegate.onTooManyRedirects(view, cancelMsg, continueMsg);
      } else {
        super.onTooManyRedirects(view, cancelMsg, continueMsg);
      }
      observer.onNext(new OnTooManyRedirect(cancelMsg, continueMsg));
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
      if (delegate != null) {
        delegate.onReceivedError(view, errorCode, description, failingUrl);
      } else {
        super.onReceivedError(view, errorCode, description, failingUrl);
      }
      observer.onNext(new OnReceivedError(errorCode, description, failingUrl));
    }

    @RequiresApi(api = Build.VERSION_CODES.M) @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
      if (delegate != null) {
        delegate.onReceivedError(view, request, error);
      } else {
        super.onReceivedError(view, request, error);
      }
      observer.onNext(new WebResourceOnReceivedError(request, error));
    }

    @RequiresApi(api = Build.VERSION_CODES.M) @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
      if (delegate != null) {
        delegate.onReceivedHttpError(view, request, errorResponse);
      } else {
        super.onReceivedHttpError(view, request, errorResponse);
      }
      observer.onNext(new OnReceivedHttpError(request, errorResponse));
    }

    @Override public void onFormResubmission(WebView view, Message dontResend, Message resend) {
      if (delegate != null) {
        delegate.onFormResubmission(view, dontResend, resend);
      } else {
        super.onFormResubmission(view, dontResend, resend);
      }
      observer.onNext(new OnFormResubmission(dontResend, resend));
    }

    @Override public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
      if (delegate != null) {
        delegate.doUpdateVisitedHistory(view, url, isReload);
      } else {
        super.doUpdateVisitedHistory(view, url, isReload);
      }
      observer.onNext(new DoUpdateVisitedHistory(url, isReload));
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      if (delegate != null) {
        delegate.onReceivedSslError(view, handler, error);
      } else {
        super.onReceivedSslError(view, handler, error);
      }
      observer.onNext(new OnReceivedSslError(handler, error));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
      if (delegate != null) {
        delegate.onReceivedClientCertRequest(view, request);
      } else {
        super.onReceivedClientCertRequest(view, request);
      }
      observer.onNext(new OnReceivedClientCertRequest(request));
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
      if (delegate != null) {
        delegate.onReceivedHttpAuthRequest(view, handler, host, realm);
      } else {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
      }
      observer.onNext(new OnReceivedHttpAuthRequest(handler, host, realm));
    }

    @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
      observer.onNext(new ShouldOverrideKeyEvent(event));
      if (delegate != null) {
        return delegate.shouldOverrideKeyEvent(view, event);
      }
      return super.shouldOverrideKeyEvent(view, event);
    }

    @Override public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
      if (delegate != null) {
        delegate.onUnhandledKeyEvent(view, event);
      } else {
        super.onUnhandledKeyEvent(view, event);
      }
      observer.onNext(new OnUnhandledKeyEvent(event));
    }

    @Override public void onScaleChanged(WebView view, float oldScale, float newScale) {
      if (delegate != null) {
        delegate.onScaleChanged(view, oldScale, newScale);
      } else {
        super.onScaleChanged(view, oldScale, newScale);
      }
      observer.onNext(new OnScaleChanged(oldScale, newScale));
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
      if (delegate != null) {
        delegate.onReceivedLoginRequest(view, realm, account, args);
      } else {
        super.onReceivedLoginRequest(view, realm, account, args);
      }
      observer.onNext(new OnReceivedLoginRequest(realm, account, args));
    }
  }
}
