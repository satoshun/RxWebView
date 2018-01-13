package com.github.satoshun.reactivex.webview.data;

import static android.webkit.WebChromeClient.CustomViewCallback;

public class OnShowCustomViewRequestedOrientation implements RxWebChromeClientData {

  private final int requestedOrientation;
  private final CustomViewCallback callback;

  public OnShowCustomViewRequestedOrientation(int requestedOrientation, CustomViewCallback callback) {
    this.requestedOrientation = requestedOrientation;
    this.callback = callback;
  }

  public int getRequestedOrientation() {
    return requestedOrientation;
  }

  public CustomViewCallback getCallback() {
    return callback;
  }
}
