package com.github.satoshun.reactivex.webview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.satoshun.reactivex.webview.RxWebView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    WebView view = (WebView) findViewById(R.id.web1);
    WebViewClient client = new WebViewClient();

    RxWebView.onPageFinished(view, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action() {
          @Override public void run() throws Exception {
            Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_LONG).show();
          }
        });
    view.loadUrl("https://www.google.co.jp");

    view = (WebView) findViewById(R.id.web2);
    client = new WebViewClient();

    Observable<RxWebView.Event> observable = RxWebView.all(view, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .share();

    observable.filter(new Predicate<RxWebView.Event>() {
      @Override public boolean test(RxWebView.Event event) throws Exception {
        return event == RxWebView.Event.ON_LOAD_RESOURCE;
      }
    }).subscribe(new Consumer<RxWebView.Event>() {
      @Override public void accept(RxWebView.Event event) throws Exception {
        Log.d("accept", event.toString());
      }
    });

    observable.filter(new Predicate<RxWebView.Event>() {
      @Override public boolean test(RxWebView.Event event) throws Exception {
        return event == RxWebView.Event.ON_PAGE_FINISHED;
      }
    }).subscribe(new Consumer<RxWebView.Event>() {
      @Override public void accept(RxWebView.Event event) throws Exception {
        Log.d("accept2", event.toString());
      }
    });
    view.loadUrl("https://www.google.co.jp");
  }
}
