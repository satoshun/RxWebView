package com.github.satoshun.reactivex.webview.data;

import android.webkit.JsResult;

public class OnJsConfirm implements RxWebChromeClientData {

  private final String url;
  private final String message;
  private final JsResult result;

  public OnJsConfirm(String url, String message, JsResult result) {
    this.url = url;
    this.message = message;
    this.result = result;
  }

  public String getUrl() {
    return url;
  }

  public String getMessage() {
    return message;
  }

  public JsResult getResult() {
    return result;
  }
}
