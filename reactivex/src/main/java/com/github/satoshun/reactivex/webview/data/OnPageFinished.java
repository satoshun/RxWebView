package com.github.satoshun.reactivex.webview.data;

public class OnPageFinished implements RxWebViewClientData {

  private final String url;

  public OnPageFinished(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
