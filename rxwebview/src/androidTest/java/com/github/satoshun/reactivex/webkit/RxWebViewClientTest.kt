package com.github.satoshun.reactivex.webkit

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.github.satoshun.reactivex.webkit.data.OnPageStarted
import io.reactivex.android.schedulers.AndroidSchedulers
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class RxWebViewClientTest {
  @get:Rule val activityRule = ActivityTestRule(RxWebViewTestActivity::class.java)

  private val activity: RxWebViewTestActivity get() = activityRule.activity

  @Test
  fun events() {
    val data = RxWebViewClient.events(activity.webView)
        .ofType(OnPageStarted::class.java)
        .take(1)
        .doOnSubscribe {
          activity.webView.loadUrl("https://www.google.com/")
        }
        .subscribeOn(AndroidSchedulers.mainThread())
        .test()
        .await()
        .values()[0]
    assertThat(data.url, `is`("https://www.google.com/"))
  }
}
