package com.github.satoshun.reactivex.webkit.data;


public class DoUpdateVisitedHistory implements RxWebViewClientData {

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
