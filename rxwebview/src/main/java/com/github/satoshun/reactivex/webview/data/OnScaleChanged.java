package com.github.satoshun.reactivex.webview.data;

public class OnScaleChanged implements RxWebViewClientData {

  private final float oldScale;
  private final float newScale;

  public OnScaleChanged(float oldScale, float newScale) {
    this.oldScale = oldScale;
    this.newScale = newScale;
  }

  public float getOldScale() {
    return oldScale;
  }

  public float getNewScale() {
    return newScale;
  }
}
