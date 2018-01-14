package com.github.satoshun.reactivex.webkit.data;


import android.os.Message;

public class OnFormResubmission implements RxWebViewClientData {

  private final Message dontResend;
  private final Message resend;

  public OnFormResubmission(Message dontResend, Message resend) {
    this.dontResend = dontResend;
    this.resend = resend;
  }

  public Message getDontResend() {
    return dontResend;
  }

  public Message getResend() {
    return resend;
  }
}
