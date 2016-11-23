package com.github.satoshun.reactivex.webview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.satoshun.reactivex.webview.RxWebViewClient;
import com.github.satoshun.reactivex.webview.data.OnPageFinished;
import com.github.satoshun.reactivex.webview.data.OnPageStarted;
import com.github.satoshun.reactivex.webview.data.RxWebViewClientData;
import com.github.satoshun.reactivex.webview.data.ShouldInterceptRequest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
        .map(data -> (ShouldInterceptRequest) data)
        .subscribe(data -> Log.d("ShouldInterceptRequest", data.toString()));
    o.filter(data -> data instanceof OnPageFinished)
        .map(data -> (OnPageFinished) data)
        .subscribe(data -> Log.d("OnPageFinished", data.toString()));
    o.filter(data -> data instanceof OnPageStarted)
        .map(data -> (OnPageStarted) data)
        .subscribe(data -> Log.d("OnPageStarted", data.toString()));
    view.loadUrl("https://www.google.co.jp");
  }
}
