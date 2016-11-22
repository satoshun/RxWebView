package com.github.satoshun.reactivex.webview.data;


public class DoUpdateVisitedHistory implements RxWebViewData {

  private final String url;
  private final boolean isReload;

  public DoUpdateVisitedHistory(String url, boolean isReload) {
    this.url = url;
    this.isReload = isReload;
  }

  public String getUrl() {
    return url;
  }

  public boolean isReload() {
    return isReload;
  }
}
