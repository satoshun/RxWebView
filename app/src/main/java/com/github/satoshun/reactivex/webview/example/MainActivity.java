package com.github.satoshun.reactivex.webview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.satoshun.reactivex.webview.RxWebChromeClient;
import com.github.satoshun.reactivex.webview.RxWebViewClient;
import com.github.satoshun.reactivex.webview.data.OnJsBeforeUnload;
import com.github.satoshun.reactivex.webview.data.OnPageFinished;
import com.github.satoshun.reactivex.webview.data.OnPageStarted;
import com.github.satoshun.reactivex.webview.data.OnReceivedIcon;
import com.github.satoshun.reactivex.webview.data.RxWebChromeClientData;
import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptRequest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sampleWebViewClient();
    sampleWebChromeClient();
  }

  private void sampleWebViewClient() {
    WebView view = (WebView) findViewById(R.id.web1);
    WebViewClient client = new WebViewClient();

    RxWebViewClient.onPageFinished(view, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_LONG).show());
    view.loadUrl("https://www.google.co.jp");

    view = (WebView) findViewById(R.id.web2);
    client = new WebViewClient();
    Observable<RxWebViewClientData> o = RxWebViewClient.all(view, client).share();
    o.filter(data -> data instanceof ShouldInterceptRequest)
        .cast(ShouldInterceptRequest.class)
        .subscribe(data -> Log.d("ShouldInterceptRequest", data.toString()));
    o.filter(data -> data instanceof OnPageFinished)
        .cast(OnPageFinished.class)
        .subscribe(data -> Log.d("OnPageFinished", data.toString()));
    o.filter(data -> data instanceof OnPageStarted)
        .cast(OnPageStarted.class)
        .subscribe(data -> Log.d("OnPageStarted", data.toString()));
    view.loadUrl("https://www.google.co.jp");
  }

  private void sampleWebChromeClient() {
    WebView view = (WebView) findViewById(R.id.web3);
    WebChromeClient client = new WebChromeClient();
    Observable<RxWebChromeClientData> o = RxWebChromeClient.all(view, client)
        .subscribeOn(AndroidSchedulers.mainThread()).share();
    o.filter(data -> data instanceof OnJsBeforeUnload)
        .cast(OnJsBeforeUnload.class)
        .subscribe(d -> Log.d("OnJsBeforeUnload", String.valueOf(d)));
    o.filter(data -> data instanceof OnReceivedIcon)
        .cast(OnReceivedIcon.class)
        .subscribe(d -> Log.d("OnReceivedIcon", String.valueOf(d)));
    view.loadUrl("https://www.google.co.jp/");
  }
}
