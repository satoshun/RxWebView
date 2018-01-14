package com.github.satoshun.reactivex.webkit.data;


import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

public class OnReceivedHttpError implements RxWebViewClientData {

  private final WebResourceRequest request;
  private final WebResourceResponse errorResponse;

  public OnReceivedHttpError(WebResourceRequest request, WebResourceResponse errorResponse) {
    this.request = request;
    this.errorResponse = errorResponse;
  }

  public WebResourceRequest getRequest() {
    return request;
  }

  public WebResourceResponse getErrorResponse() {
    return errorResponse;
  }
}
