package com.github.satoshun.reactivex.webview.data;

public class OnProgressChanged implements RxWebChromeClientData {

  private final int newProgress;

  public OnProgressChanged(int newProgress) {
    this.newProgress = newProgress;
  }

  public int getNewProgress() {
    return newProgress;
  }
}
