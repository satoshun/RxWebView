package com.github.satoshun.reactivex.webview.data;


public class OnLoadResource implements RxWebViewData {

  private final String url;

  public OnLoadResource(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
