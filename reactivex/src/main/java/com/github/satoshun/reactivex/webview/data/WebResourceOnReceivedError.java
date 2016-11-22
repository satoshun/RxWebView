package com.github.satoshun.reactivex.webview.data;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;

public class WebResourceOnReceivedError implements RxWebViewData {

  private final WebResourceRequest request;
  private final WebResourceError error;

  public WebResourceOnReceivedError(WebResourceRequest request, WebResourceError error) {
    this.request = request;
    this.error = error;
  }

  public WebResourceRequest getRequest() {
    return request;
  }

  public WebResourceError getError() {
    return error;
  }
}
