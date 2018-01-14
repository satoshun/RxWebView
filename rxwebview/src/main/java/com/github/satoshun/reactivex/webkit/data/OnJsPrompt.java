package com.github.satoshun.reactivex.webkit.data;

import android.webkit.JsPromptResult;

public class OnJsPrompt implements RxWebChromeClientData {

  private final String url;
  private final String message;
  private final String defaultValue;
  private final JsPromptResult result;

  public OnJsPrompt(String url, String message, String defaultValue, JsPromptResult result) {
    this.url = url;
    this.message = message;
    this.defaultValue = defaultValue;
    this.result = result;
  }

  public String getUrl() {
    return url;
  }

  public String getMessage() {
    return message;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public JsPromptResult getResult() {
    return result;
  }
}
