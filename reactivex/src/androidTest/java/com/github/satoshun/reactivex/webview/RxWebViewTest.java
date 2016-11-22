package com.github.satoshun.reactivex.webview;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.WebViewClient;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RxWebViewTest {

  @Rule public final ActivityTestRule<RxWebViewTestActivity> activityRule =
      new ActivityTestRule<>(RxWebViewTestActivity.class);

  private RxWebViewTestActivity activity;

  @Before public void setUp() throws Exception {
    activity = activityRule.getActivity();
  }

  @Test public void onPageFinished() throws Exception {
    WebViewClient client = new WebViewClient();
    TestObserver<Void> observer = RxWebView.onPageFinished(activity.webview, client)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    observer.await(10, TimeUnit.SECONDS);
    observer.assertComplete();
  }

  @Test public void onPageFinished_dispose() throws Exception {
    WebViewClient client = new WebViewClient();
    TestObserver<Void> observer = RxWebView.onPageFinished(activity.webview, client)
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    assertThat(observer.isDisposed(), is(false));
    observer.dispose();
    assertThat(observer.isDisposed(), is(true));
  }

  @Test public void onPageStarted() throws Exception {
    WebViewClient client = new WebViewClient();
    TestObserver<Void> observer = RxWebView.onPageStarted(activity.webview, client)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    observer.await(1, TimeUnit.SECONDS);
    observer.assertComplete();
  }

  @Test public void onPageStarted_dispose() throws Exception {
    WebViewClient client = new WebViewClient();
    TestObserver<Void> observer = RxWebView.onPageStarted(activity.webview, client)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    assertThat(observer.isDisposed(), is(false));
    observer.dispose();
    assertThat(observer.isDisposed(), is(true));
  }

  @Test public void all() throws Exception {
    WebViewClient client = new WebViewClient();
    TestObserver<RxWebView.Event> o = RxWebView.all(activity.webview, client)
        .filter(new Predicate<RxWebView.Event>() {
          @Override public boolean test(RxWebView.Event event) throws Exception {
            return event == RxWebView.Event.ON_PAGE_STARTED;
          }
        })
        .take(1)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    o.await(1, TimeUnit.SECONDS);
    o.assertValue(RxWebView.Event.ON_PAGE_STARTED);
  }
}
