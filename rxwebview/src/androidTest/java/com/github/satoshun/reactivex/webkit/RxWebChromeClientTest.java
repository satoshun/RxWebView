package com.github.satoshun.reactivex.webkit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.github.satoshun.reactivex.webkit.data.OnProgressChanged;
import com.github.satoshun.reactivex.webkit.data.RxWebChromeClientData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RxWebChromeClientTest {

  @Rule public final ActivityTestRule<RxWebViewTestActivity> activityRule =
      new ActivityTestRule<>(RxWebViewTestActivity.class);

  private RxWebViewTestActivity activity;

  @Before public void setUp() throws Exception {
    activity = activityRule.getActivity();
  }

  @Test public void events() throws Exception {
    TestObserver<Integer> o = RxWebChromeClient.INSTANCE.events(activity.webview)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com/");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .filter(new Predicate<RxWebChromeClientData>() {
          @Override
          public boolean test(RxWebChromeClientData data) throws Exception {
            return data instanceof OnProgressChanged;
          }
        })
        .map(new Function<RxWebChromeClientData, Integer>() {
          @Override
          public Integer apply(RxWebChromeClientData data) throws Exception {
            return ((OnProgressChanged) data).getNewProgress();
          }
        })
        .take(1)
        .test();
    o.await();
    o.assertValueCount(1);
  }
}
