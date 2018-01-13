package com.github.satoshun.reactivex.webview.data;

import android.webkit.PermissionRequest;

public class OnPermissionRequest implements RxWebChromeClientData {

  private final PermissionRequest request;

  public OnPermissionRequest(PermissionRequest request) {
    this.request = request;
  }

  public PermissionRequest getRequest() {
    return request;
  }
}
