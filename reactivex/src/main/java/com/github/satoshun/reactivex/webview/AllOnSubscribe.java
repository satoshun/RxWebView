package com.github.satoshun.reactivex.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.satoshun.reactivex.webview.data.DoUpdateVisitedHistory;
import com.github.satoshun.reactivex.webview.data.OnFormResubmission;
import com.github.satoshun.reactivex.webview.data.OnLoadResource;
import com.github.satoshun.reactivex.webview.data.OnPageCommitVisible;
import com.github.satoshun.reactivex.webview.data.OnPageFinished;
import com.github.satoshun.reactivex.webview.data.OnPageStarted;
import com.github.satoshun.reactivex.webview.data.OnReceivedClientCertRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedError;
import com.github.satoshun.reactivex.webview.data.OnReceivedHttpAuthRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedHttpError;
import com.github.satoshun.reactivex.webview.data.OnReceivedLoginRequest;
import com.github.satoshun.reactivex.webview.data.OnReceivedSslError;
import com.github.satoshun.reactivex.webview.data.OnScaleChanged;
import com.github.satoshun.reactivex.webview.data.OnTooManyRedirect;
import com.github.satoshun.reactivex.webview.data.OnUnhandledKeyEvent;
import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptRequest;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptWebResourceRequest;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideKeyEvent;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideUrlLoading;
import com.github.satoshun.reactivex.webview.data.ShouldOverrideUrlLoadingWebResourceRequest;
import com.github.satoshun.reactivex.webview.data.WebResourceOnReceivedError;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

final class AllOnSubscribe implements ObservableOnSubscribe<RxWebViewClientData> {

  private final WebView webView;
  private final WebViewClient client;

  AllOnSubscribe(WebView webView, WebViewClient client) {
    this.webView = webView;
    this.client = client;
  }

  @Override public void subscribe(final ObservableEmitter<RxWebViewClientData> e) throws Exception {
    webView.setWebViewClient(new WebViewClientWrapper(client) {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        e.onNext(new ShouldOverrideUrlLoading(url));
        return super.shouldOverrideUrlLoading(view, url);
      }

      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        e.onNext(new ShouldOverrideUrlLoadingWebResourceRequest(request));
        return super.shouldOverrideUrlLoading(view, request);
      }

      @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        e.onNext(new OnPageStarted(url, favicon));
      }

      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        e.onNext(new OnPageFinished(url));
      }

      @Override public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        e.onNext(new OnLoadResource(url));
      }

      @RequiresApi(api = Build.VERSION_CODES.M)
      @Override public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
        e.onNext(new OnPageCommitVisible(url));
      }

      @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        e.onNext(new ShouldInterceptRequest(url));
        return super.shouldInterceptRequest(view, url);
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
      public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        e.onNext(new ShouldInterceptWebResourceRequest(request));
        return super.shouldInterceptRequest(view, request);
      }

      @Override
      public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg);
        e.onNext(new OnTooManyRedirect(cancelMsg, continueMsg));
      }

      @Override
      public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        e.onNext(new OnReceivedError(errorCode, description, failingUrl));
      }

      @RequiresApi(api = Build.VERSION_CODES.M) @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        e.onNext(new WebResourceOnReceivedError(request, error));
      }

      @RequiresApi(api = Build.VERSION_CODES.M) @Override
      public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        e.onNext(new OnReceivedHttpError(request, errorResponse));
      }

      @Override public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        super.onFormResubmission(view, dontResend, resend);
        e.onNext(new OnFormResubmission(dontResend, resend));
      }

      @Override public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
        e.onNext(new DoUpdateVisitedHistory(url, isReload));
      }

      @Override
      public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        e.onNext(new OnReceivedSslError(handler, error));
      }

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        super.onReceivedClientCertRequest(view, request);
        e.onNext(new OnReceivedClientCertRequest(request));
      }

      @Override
      public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
        e.onNext(new OnReceivedHttpAuthRequest(handler, host, realm));
      }

      @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        e.onNext(new ShouldOverrideKeyEvent(event));
        return super.shouldOverrideKeyEvent(view, event);
      }

      @Override public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        super.onUnhandledKeyEvent(view, event);
        e.onNext(new OnUnhandledKeyEvent(event));
      }

      @Override public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        e.onNext(new OnScaleChanged(oldScale, newScale));
      }

      @Override
      public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        super.onReceivedLoginRequest(view, realm, account, args);
        e.onNext(new OnReceivedLoginRequest(realm, account, args));
      }
    });
    e.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        webView.setWebViewClient(null);
      }
    });
  }
}
