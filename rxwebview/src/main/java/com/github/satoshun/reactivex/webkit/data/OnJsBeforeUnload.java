package com.github.satoshun.reactivex.webkit.data;

import android.webkit.JsResult;

public class OnJsBeforeUnload implements RxWebChromeClientData {

  private final String url;
  private final String message;
  private final JsResult result;

  public OnJsBeforeUnload(String url, String message, JsResult result) {
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
