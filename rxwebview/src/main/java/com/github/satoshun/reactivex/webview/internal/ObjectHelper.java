package com.github.satoshun.reactivex.webview.internal;

public class ObjectHelper {
  public static <T> T requireNonNull(T obj, String message) {
    if (obj == null) {
      throw new NullPointerException(message);
    }
    return obj;
  }
}
