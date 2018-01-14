package com.github.satoshun.reactivex.webkit.data;

import android.webkit.GeolocationPermissions;

public class OnGeolocationPermissionsShowPrompt implements RxWebChromeClientData {

  private final String origin;
  private final GeolocationPermissions.Callback callback;

  public OnGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
    this.origin = origin;
    this.callback = callback;
  }

  public String getOrigin() {
    return origin;
  }

  public GeolocationPermissions.Callback getCallback() {
    return callback;
  }
}
