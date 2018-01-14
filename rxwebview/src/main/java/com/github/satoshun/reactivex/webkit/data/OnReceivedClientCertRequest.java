package com.github.satoshun.reactivex.webkit.data;

import android.webkit.ClientCertRequest;

public class OnReceivedClientCertRequest implements RxWebViewClientData {

  private final ClientCertRequest request;

  public OnReceivedClientCertRequest(ClientCertRequest request) {
    this.request = request;
  }

  public ClientCertRequest getRequest() {
    return request;
  }
}
