package com.github.satoshun.reactivex.webview.data;

import android.webkit.ClientCertRequest;

public class OnReceivedClientCertRequest implements RxWebViewData {

  private final ClientCertRequest request;

  public OnReceivedClientCertRequest(ClientCertRequest request) {
    this.request = request;
  }

  public ClientCertRequest getRequest() {
    return request;
  }
}
