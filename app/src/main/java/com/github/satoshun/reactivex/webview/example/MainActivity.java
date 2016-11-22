package com.github.satoshun.reactivex.webview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.satoshun.reactivex.webview.RxWebView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    WebView view = (WebView) findViewById(R.id.web);
    WebViewClient client = new WebViewClient();

    RxWebView.onPageFinished(view, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action() {
          @Override public void run() throws Exception {
            Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_LONG).show();
          }
        });
    view.loadUrl("https://www.google.co.jp");
  }
}
