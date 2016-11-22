package com.github.satoshun.reactivex.webview.data;


import android.graphics.Bitmap;

public class OnPageStarted implements RxWebViewData {

  private final String url;
  private final Bitmap favicon;

  public OnPageStarted(String url, Bitmap favicon) {
    this.url = url;
    this.favicon = favicon;
  }

  public String getUrl() {
    return url;
  }

  public Bitmap getFavicon() {
    return favicon;
  }
}
