package com.github.satoshun.reactivex.webkit.data;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;

public class OnReceivedSslError implements RxWebViewClientData {

  private final SslErrorHandler handler;
  private final SslError error;

  public OnReceivedSslError(SslErrorHandler handler, SslError error) {
    this.handler = handler;
    this.error = error;
  }

  public SslErrorHandler getHandler() {
    return handler;
  }

  public SslError getError() {
    return error;
  }
}
