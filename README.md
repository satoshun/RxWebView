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

When you want a all [WebViewClient](https://developer.android.com/reference/android/webkit/WebViewClient) events, we can use a `RxWebViewClient#events`.

```java
WebView webview = new WebView(context);
RxWebViewClient.events(webview)
    .ofType(ANY_DATA_TYPE.class)
    .subscribe();
```

When you want a `onPageFinished` event, we can use a `RxWebViewClient#events` + `ofType` operator.

```java
WebView webview = new WebView(context);
RxWebViewClient.events(webview)
    .ofType(OnPageFinished.class)
    .subscribe();
```

All data type defined to [package](reactivex/src/main/java/com/github/satoshun/reactivex/webkit/data).


### WebChromeClient

When you want a all [WebChromeClient](https://developer.android.com/reference/android/webkit/WebChromeClient) events.

```java
WebView webview = new WebView(context);
RxWebChromeClient.events(webview)
    .subscribe();
```

All data type defined to [package](reactivex/src/main/java/com/github/satoshun/reactivex/webkit/data)s

## more information

- [sample source code](app/src/main/java/com/github/satoshun/reactivex/webkit/example)

## etc

This project inspired by [RxBinding](https://github.com/JakeWharton/RxBinding). It was very helpful. thx!
