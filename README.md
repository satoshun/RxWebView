[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.satoshun.RxWebView/rxwebview/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.satoshun.RxWebView/rxwebview)
[![CircleCI](https://circleci.com/gh/satoshun/RxWebView.svg?style=svg)](https://circleci.com/gh/satoshun/RxWebView)
[![codecov](https://codecov.io/gh/satoshun/RxWebView/branch/master/graph/badge.svg)](https://codecov.io/gh/satoshun/RxWebView)

# RxWebView

RxJava2 binding APIs for Android WebView with Kotlin.

## install

```groovy
implementation "com.github.satoshun.RxWebView:rxwebview:${latest-version}"
```

## usage

### WebViewClient

If you want a all events from [WebViewClient](https://developer.android.com/reference/android/webkit/WebViewClient),
we can use a `RxWebViewClient#events` method.

```kotlin
val webview = WebView(context)
webview.events().subscribe() // emits all WebView eventss
```

If you want a specific event like a `onPageFinished`,
we can use a `RxWebViewClient#events` + `ofType` operator.

```kotlin
val webview = WebView(context)
webView.events()
    .ofType(OnPageFinished::class.java) // only OnPageFinished
    .subscribe()
```

If you want to a hook events from `WebViewClient`, we can give a custom WebViewClient.

```kotlin
webview.events(delegate = CustomWebViewClient()).subscribe()
```

All data type defined in [this](rxwebview/src/main/java/com/github/satoshun/reactivex/webkit/data/RxWebViewClientData.kt).

### WebChromeClient

If you want a all events from [WebChromeClient](https://developer.android.com/reference/android/webkit/WebChromeClient).

```kotlin
val webview = WebView(context)
webview.chromeEvents().subscribe()
```

If you want to a hook events from `WebChromeClient`, we can give a custom WebChromeClient.

```kotlin
webview.chromeEvents(delegate = customWebChromeClient).subscribe()
```

All data type defined in [this](rxwebview/src/main/java/com/github/satoshun/reactivex/webkit/data/RxWebChromeClientData.kt)

## more information

- [Kotlin sample source code](app/src/main/java/com/github/satoshun/reactivex/webkit/example)
- [Java sample source code](app-java/src/main/java/com/github/satoshun/reactivex/webkit/example)

## etc

This project inspired by [RxBinding](https://github.com/JakeWharton/RxBinding). It was very helpful. thx!
