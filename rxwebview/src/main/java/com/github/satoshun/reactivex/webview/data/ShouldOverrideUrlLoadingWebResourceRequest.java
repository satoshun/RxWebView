package com.github.satoshun.reactivex.webview.data;

import android.webkit.WebResourceRequest;


public class ShouldOverrideUrlLoadingWebResourceRequest implements RxWebViewClientData {

  private final WebResourceRequest resourceRequest;

  public ShouldOverrideUrlLoadingWebResourceRequest(WebResourceRequest resourceRequest) {
    this.resourceRequest = resourceRequest;
  }

  public WebResourceRequest getResourceRequest() {
    return resourceRequest;
  }
}
