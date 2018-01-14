package com.github.satoshun.reactivex.webkit.data;

public class OnReceivedError implements RxWebViewClientData {

  private final int errorCode;
  private final String description;
  private final String failingUrl;

  public OnReceivedError(int errorCode, String description, String failingUrl) {
    this.errorCode = errorCode;
    this.description = description;
    this.failingUrl = failingUrl;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getDescription() {
    return description;
  }

  public String getFailingUrl() {
    return failingUrl;
  }
}
