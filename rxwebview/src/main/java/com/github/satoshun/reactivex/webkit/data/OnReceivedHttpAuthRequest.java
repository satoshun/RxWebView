package com.github.satoshun.reactivex.webkit.data;

import android.webkit.HttpAuthHandler;

public class OnReceivedHttpAuthRequest implements RxWebViewClientData {

  private final HttpAuthHandler handler;
  private final String host;
  private final String realm;

  public OnReceivedHttpAuthRequest(HttpAuthHandler handler, String host, String realm) {
    this.handler = handler;
    this.host = host;
    this.realm = realm;
  }

  public HttpAuthHandler getHandler() {
    return handler;
  }

  public String getHost() {
    return host;
  }

  public String getRealm() {
    return realm;
  }
}
