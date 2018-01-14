package com.github.satoshun.reactivex.webkit.data;


import android.webkit.WebResourceRequest;

public class ShouldInterceptWebResourceRequest implements RxWebViewClientData {

  private final WebResourceRequest resourceRequest;

  public ShouldInterceptWebResourceRequest(WebResourceRequest resourceRequest) {
    this.resourceRequest = resourceRequest;
  }

  public WebResourceRequest getResourceRequest() {
    return resourceRequest;
  }
}
