package com.github.satoshun.reactivex.webkit.data;

import android.os.Message;

public class OnCreateWindow implements RxWebChromeClientData {

  private final boolean isDialog;
  private final boolean isUserGesture;
  private final Message resultMsg;

  public OnCreateWindow(boolean isDialog, boolean isUserGesture, Message resultMsg) {
    this.isDialog = isDialog;
    this.isUserGesture = isUserGesture;
    this.resultMsg = resultMsg;
  }

  public boolean isDialog() {
    return isDialog;
  }

  public boolean isUserGesture() {
    return isUserGesture;
  }

  public Message getResultMsg() {
    return resultMsg;
  }
}
