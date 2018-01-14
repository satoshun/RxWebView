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

    // subscribe only onPageFinished event
    RxWebViewClient.onPageFinished(view, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_LONG).show());
    view.loadUrl("https://www.google.co.jp");

    view = (WebView) findViewById(R.id.web2);
    client = new WebViewClient();
    Observable<RxWebViewClientData> o = RxWebViewClient.events(view, client).share();
    // subscribe ShouldInterceptRequest event
    o.ofType(ShouldInterceptRequest.class)
        .subscribe(data -> Log.d("ShouldInterceptRequest", data.toString()));
    // subscribe OnPageFinished event
    o.ofType(OnPageFinished.class)
        .subscribe(data -> Log.d("OnPageFinished", data.toString()));
    // subscribe OnPageStarted event
    o.ofType(OnPageStarted.class)
        .subscribe(data -> Log.d("OnPageStarted", data.toString()));
    // subscribe all events of WebViewClient
    o.subscribe(d -> Log.d("RxWebViewClient", String.valueOf(d)));

    view.loadUrl("https://www.google.co.jp");
  }

  private void sampleWebChromeClient() {
    WebView view = (WebView) findViewById(R.id.web3);
    WebChromeClient client = new WebChromeClient();
    Observable<RxWebChromeClientData> o = RxWebChromeClient.all(view, client)
        .subscribeOn(AndroidSchedulers.mainThread()).share();
    // subscribe OnJsBeforeUnload event
    o.ofType(OnJsBeforeUnload.class)
        .subscribe(d -> Log.d("OnJsBeforeUnload", String.valueOf(d)));
    // subscribe OnReceivedIcon event
    o.ofType(OnReceivedIcon.class)
        .subscribe(d -> Log.d("OnReceivedIcon", String.valueOf(d)));
    // subscribe all events of WebChromeClient
    o.subscribe(d -> Log.d("RxWebChromeClient", String.valueOf(d)));

    view.loadUrl("https://www.google.co.jp/");
  }
}
