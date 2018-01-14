package com.github.satoshun.reactivex.webkit.data;

public class OnConsoleMessage implements RxWebChromeClientData {

  private final String message;
  private final int lineNumber;
  private final String sourceID;

  public OnConsoleMessage(String message, int lineNumber, String sourceID) {
    this.message = message;
    this.lineNumber = lineNumber;
    this.sourceID = sourceID;
  }

  public String getMessage() {
    return message;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public String getSourceID() {
    return sourceID;
  }
}
