package com.github.satoshun.reactivex.webview.data;

public class OnReceivedTouchIconUrl implements RxWebChromeClientData {

  private final String url;
  private final boolean precomposed;

  public OnReceivedTouchIconUrl(String url, boolean precomposed) {
    this.url = url;
    this.precomposed = precomposed;
  }

  public String getUrl() {
    return url;
  }

  public boolean isPrecomposed() {
    return precomposed;
  }
}
