package com.github.satoshun.reactivex.webkit.data;


import android.os.Message;

public class OnTooManyRedirect implements RxWebViewClientData {

  private final Message cancelMsg;
  private final Message continueMsg;

  public OnTooManyRedirect(Message cancelMsg, Message continueMsg) {
    this.cancelMsg = cancelMsg;
    this.continueMsg = continueMsg;
  }

  public Message getCancelMsg() {
    return cancelMsg;
  }

  public Message getContinueMsg() {
    return continueMsg;
  }
}
