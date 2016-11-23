package com.github.satoshun.reactivex.webview.data;

public class OnPageCommitVisible implements RxWebViewClientData {

  private final String url;

  public OnPageCommitVisible(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
