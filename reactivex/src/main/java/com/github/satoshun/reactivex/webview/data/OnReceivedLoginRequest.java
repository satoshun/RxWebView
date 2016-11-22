package com.github.satoshun.reactivex.webview.data;

public class OnReceivedLoginRequest implements RxWebViewData {

  private final String realm;
  private final String account;
  private final String args;

  public OnReceivedLoginRequest(String realm, String account, String args) {
    this.realm = realm;
    this.account = account;
    this.args = args;
  }

  public String getRealm() {
    return realm;
  }

  public String getAccount() {
    return account;
  }

  public String getArgs() {
    return args;
  }
}
