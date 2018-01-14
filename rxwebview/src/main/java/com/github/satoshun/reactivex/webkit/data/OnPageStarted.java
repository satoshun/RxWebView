package com.github.satoshun.reactivex.webkit.data;


import android.graphics.Bitmap;

public class OnPageStarted implements RxWebViewClientData {

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
