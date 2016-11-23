package com.github.satoshun.reactivex.webview.data;

public class OnReceivedTitle implements RxWebChromeClientData {

  private final String title;

  public OnReceivedTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
