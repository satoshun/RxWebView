package com.github.satoshun.reactivex.webview.data;


import android.webkit.WebResourceRequest;

public class ShouldInterceptWebResourceRequest implements RxWebViewData {

  private final WebResourceRequest resourceRequest;

  public ShouldInterceptWebResourceRequest(WebResourceRequest resourceRequest) {
    this.resourceRequest = resourceRequest;
  }

  public WebResourceRequest getResourceRequest() {
    return resourceRequest;
  }
}
