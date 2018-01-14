package com.github.satoshun.reactivex.webkit;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class RxWebViewTestActivity extends Activity {
  FrameLayout parent;
  WebView webview;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    parent = new FrameLayout(this);
    webview = new WebView(this);
    parent.addView(webview);
    setContentView(parent);
  }
}
