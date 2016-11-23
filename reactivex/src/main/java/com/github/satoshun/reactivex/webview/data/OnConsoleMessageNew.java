package com.github.satoshun.reactivex.webview.data;

import android.webkit.ConsoleMessage;

public class OnConsoleMessageNew implements RxWebChromeClientData {

  private final ConsoleMessage consoleMessage;

  public OnConsoleMessageNew(ConsoleMessage consoleMessage) {
    this.consoleMessage = consoleMessage;
  }

  public ConsoleMessage getConsoleMessage() {
    return consoleMessage;
  }
}
