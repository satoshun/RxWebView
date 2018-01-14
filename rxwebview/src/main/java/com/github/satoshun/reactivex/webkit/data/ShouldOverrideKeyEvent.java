package com.github.satoshun.reactivex.webkit.data;

import android.view.KeyEvent;

public class ShouldOverrideKeyEvent implements RxWebViewClientData {

  private final KeyEvent event;

  public ShouldOverrideKeyEvent(KeyEvent event) {
    this.event = event;
  }

  public KeyEvent getEvent() {
    return event;
  }
}
