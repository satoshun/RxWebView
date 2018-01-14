package com.github.satoshun.reactivex.webkit.data;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;

public class WebResourceOnReceivedError implements RxWebViewClientData {

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
