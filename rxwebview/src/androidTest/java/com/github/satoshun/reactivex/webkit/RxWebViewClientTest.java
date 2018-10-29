package com.github.satoshun.reactivex.webkit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.github.satoshun.reactivex.webkit.data.OnPageStarted;
import com.github.satoshun.reactivex.webkit.data.RxWebViewClientData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RxWebViewClientTest {

  @Rule
  public final ActivityTestRule<RxWebViewTestActivity> activityRule =
      new ActivityTestRule<>(RxWebViewTestActivity.class);

  private RxWebViewTestActivity activity;

  @Before
  public void setUp() {
    activity = activityRule.getActivity();
  }

  @Test
  public void events() throws Exception {
    TestObserver<RxWebViewClientData> o = RxWebViewClient.INSTANCE.events(activity.webview)
        .filter(new Predicate<RxWebViewClientData>() {
          @Override
          public boolean test(RxWebViewClientData data) throws Exception {
            return data instanceof OnPageStarted;
          }
        })
        .take(1)
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override
          public void accept(Disposable disposable) throws Exception {
            activity.webview.loadUrl("https://www.google.com/");
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .test();
    o.await();

    OnPageStarted data = (OnPageStarted) o.values().get(0);
    assertThat(data.getUrl(), is("https://www.google.com/"));
  }
}
