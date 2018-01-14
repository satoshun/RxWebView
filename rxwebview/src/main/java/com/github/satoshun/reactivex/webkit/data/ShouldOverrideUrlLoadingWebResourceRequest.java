package com.github.satoshun.reactivex.webkit.data;

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
