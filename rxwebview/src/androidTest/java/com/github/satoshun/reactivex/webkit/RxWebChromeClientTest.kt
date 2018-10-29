package com.github.satoshun.reactivex.webkit

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.satoshun.reactivex.webkit.data.OnProgressChanged
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RxWebChromeClientTest {
  @get:Rule val activityRule = ActivityTestRule(RxWebViewTestActivity::class.java)

  private val activity: RxWebViewTestActivity get() = activityRule.activity

  @Test
  fun events() {
    RxWebChromeClient.events(activity.webView)
        .doOnSubscribe {
          activity.webView.loadUrl("https://www.google.com/")
        }
        .subscribeOn(AndroidSchedulers.mainThread())
        .ofType(OnProgressChanged::class.java)
        .take(1)
        .test()
        .await()
        .assertValueCount(1)
  }
}
