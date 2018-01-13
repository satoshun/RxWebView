package com.github.satoshun.reactivex.webview.data;

import android.webkit.PermissionRequest;

public class OnPermissionRequestCanceled implements RxWebChromeClientData {

  private final PermissionRequest request;

  public OnPermissionRequestCanceled(PermissionRequest request) {
    this.request = request;
  }

  public PermissionRequest getRequest() {
    return request;
  }
}
