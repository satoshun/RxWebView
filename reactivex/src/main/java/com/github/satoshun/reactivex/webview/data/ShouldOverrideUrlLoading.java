package com.github.satoshun.reactivex.webview.data;

public class ShouldOverrideUrlLoading implements RxWebViewData {

  private final String url;

  public ShouldOverrideUrlLoading(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
