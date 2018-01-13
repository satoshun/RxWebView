package com.github.satoshun.reactivex.webview.data;

import android.net.Uri;
import android.webkit.ValueCallback;

import static android.webkit.WebChromeClient.FileChooserParams;

public class OnShowFileChooser implements RxWebChromeClientData {

  private final ValueCallback<Uri[]> filePathCallback;
  private final FileChooserParams fileChooserParams;

  public OnShowFileChooser(ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
    this.filePathCallback = filePathCallback;
    this.fileChooserParams = fileChooserParams;
  }

  public ValueCallback<Uri[]> getFilePathCallback() {
    return filePathCallback;
  }

  public FileChooserParams getFileChooserParams() {
    return fileChooserParams;
  }
}
