package com.github.satoshun.reactivex.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.functions.Cancellable;

final class PageFinishedOnSubscribe implements CompletableOnSubscribe {

  private final WebView webView;
  private final WebViewClient client;

  PageFinishedOnSubscribe(WebView webView, WebViewClient webViewClient) {
    this.webView = webView;
    this.client = webViewClient;
  }

  @Override public void subscribe(final CompletableEmitter e) throws Exception {
    webView.setWebViewClient(new WebViewClientWrapper(client) {
      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        e.onComplete();
      }
    });
    e.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        webView.setWebViewClient(null);
      }
    });
  }
}
