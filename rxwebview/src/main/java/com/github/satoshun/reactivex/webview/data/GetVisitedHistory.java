package com.github.satoshun.reactivex.webview.data;

import android.webkit.ValueCallback;

public class GetVisitedHistory implements RxWebChromeClientData {

  private final ValueCallback<String[]> callback;

  public GetVisitedHistory(ValueCallback<String[]> callback) {
    this.callback = callback;
  }

  public ValueCallback<String[]> getCallback() {
    return callback;
  }
}
