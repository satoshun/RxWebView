package com.github.satoshun.reactivex.webkit.data;

import static android.webkit.WebChromeClient.CustomViewCallback;

public class OnShowCustomView implements RxWebChromeClientData {

  private final CustomViewCallback callback;

  public OnShowCustomView(CustomViewCallback callback) {
    this.callback = callback;
  }

  public CustomViewCallback getCallback() {
    return callback;
  }
}
