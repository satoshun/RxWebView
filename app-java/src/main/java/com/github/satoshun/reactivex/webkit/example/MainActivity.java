package com.github.satoshun.reactivex.webkit.example;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.satoshun.reactivex.webkit.RxWebChromeClient;
import com.github.satoshun.reactivex.webkit.RxWebViewClient;
import com.github.satoshun.reactivex.webkit.data.*;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {
  private final CompositeDisposable disposables = new CompositeDisposable();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sampleWebViewClient();
    sampleWebChromeClient();
  }

  private void sampleWebViewClient() {
    detectStartAndFinishEvent();
    otherEvents();
  }

  private void detectStartAndFinishEvent() {
    WebView view = findViewById(R.id.web1);

    disposables.add(
        RxWebViewClient.events(view, new WebViewClient())
            .publish((shared) -> Observable.merge(
                shared.ofType(OnPageStarted.class),
                shared.ofType(OnPageFinished.class)
            ))
            .subscribe((event) -> {
              if (event instanceof OnPageStarted) {
                Toast
                    .makeText(this, "onPageStarted", Toast.LENGTH_LONG)
                    .show();
              }
              if (event instanceof OnPageFinished) {
                Toast
                    .makeText(this, "onPageStarted", Toast.LENGTH_LONG)
                    .show();
              }
            })
    );
    view.loadUrl("https://www.google.co.jp");
  }

  private void otherEvents() {
    WebView view = findViewById(R.id.web2);
    Observable<RxWebViewClientData> o = RxWebViewClient.events(view).share();
    disposables.addAll(
        // subscribe ShouldInterceptRequest event
        o.ofType(ShouldInterceptRequest.class)
            .subscribe((data) -> Log.d("ShouldInterceptRequest", data.toString())),

        // subscribe OnPageFinished event
        o.ofType(OnPageFinished.class)
            .subscribe((data) -> Log.d("OnPageFinished", data.toString())),

        // subscribe OnPageStarted event
        o.ofType(OnPageStarted.class)
            .subscribe((data) -> Log.d("OnPageStarted", data.toString())),

        // subscribe all events of WebViewClient
        o.subscribe((data) -> Log.d("RxWebViewClient", data.toString()))
    );
    view.loadUrl("https://www.google.co.jp");
  }

  private void sampleWebChromeClient() {
    WebView view = findViewById(R.id.web3);
    Observable<RxWebChromeClientData> o = RxWebChromeClient.events(view, new WebChromeClient()).share();

    disposables.addAll(
        // subscribe OnJsBeforeUnload event
        o.ofType(OnJsBeforeUnload.class)
            .subscribe((d) -> Log.d("OnJsBeforeUnload", d.toString())),

        // subscribe OnReceivedIcon event
        o.ofType(OnReceivedIcon.class)
            .subscribe((d) -> Log.d("OnReceivedIcon", d.toString())),

        // subscribe all events of WebChromeClient
        o.subscribe((d) -> Log.d("RxWebChromeClient", d.toString()))
    );

    view.loadUrl("https://www.google.co.jp/");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposables.clear();
  }
}
