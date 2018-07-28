[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.satoshun.RxWebView/rxwebview/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.satoshun.RxWebView/rxwebview)
[![CircleCI](https://circleci.com/gh/satoshun/RxWebView.svg?style=svg)](https://circleci.com/gh/satoshun/RxWebView)
[![codecov](https://codecov.io/gh/satoshun/RxWebView/branch/master/graph/badge.svg)](https://codecov.io/gh/satoshun/RxWebView)

# RxWebView

RxJava2 binding APIs for Android WebView.

## install

```groovy
implementation "com.github.satoshun.RxWebView:rxwebview:${latest-version}"

// for Kotlin user
implementation "com.github.satoshun.RxWebView:rxwebview-kotlin:${latest-version}"
```

## usage

### WebViewClient

If you want a all events from [WebViewClient](https://developer.android.com/reference/android/webkit/WebViewClient), we can use a `RxWebViewClient#events`.

```java
WebView webview = new WebView(context);
RxWebViewClient.events(webview)
    .ofType(ANY_DATA_TYPE.class)
    .subscribe();
```

If you want a specific event like a `onPageFinished`, we can use a `RxWebViewClient#events` + `ofType` operator.

```java
WebView webview = new WebView(context);
RxWebViewClient.events(webview)
    .ofType(OnPageFinished.class) // only OnPageFinished
    .subscribe();
```

If you want to a hook events from WebViewClient, we can give a custom WebViewClient.

```java
RxWebViewClient.events(webview, new CustomWebViewClient())
    .subscribe();
```

All data type defined in [this](rxwebview/src/main/java/com/github/satoshun/reactivex/webkit/data).

### WebChromeClient

If you want a all events from [WebChromeClient](https://developer.android.com/reference/android/webkit/WebChromeClient).

```java
WebView webview = new WebView(context);
RxWebChromeClient.events(webview)
    .subscribe();
```

All data type defined in [this](rxwebview/src/main/java/com/github/satoshun/reactivex/webkit/data)

## more information

- [sample source code](app/src/main/java/com/github/satoshun/reactivex/webkit/example)

## etc

This project inspired by [RxBinding](https://github.com/JakeWharton/RxBinding). It was very helpful. thx!
