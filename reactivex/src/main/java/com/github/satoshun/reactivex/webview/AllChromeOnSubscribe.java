package com.github.satoshun.reactivex.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.github.satoshun.reactivex.webview.data.OnProgressChanged;
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
    });

    e.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        webView.setWebChromeClient(null);
      }
    });
  }
}
