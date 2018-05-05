# RxWebView

[![CircleCI](https://circleci.com/gh/satoshun/RxWebView.svg?style=svg)](https://circleci.com/gh/satoshun/RxWebView) [![codecov](https://codecov.io/gh/satoshun/RxWebView/branch/master/graph/badge.svg)](https://codecov.io/gh/satoshun/RxWebView)

RxJava2 binding APIs for Android WebView.

This project inspires by [RxBinding](https://github.com/JakeWharton/RxBinding). It was very helpful. thx!


## install

```groovy
implementation 'com.github.satoshun.RxWebView:rxwebview:2.1.0'

// use kotlin
implementation 'com.github.satoshun.RxWebView:rxwebview-kotlin:2.1.0'
```


## usage

### WebViewClient

observes all events.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebViewClient.events(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .ofType(ANY_DATA_TYPE.class)
    .subscribe();
```

For example, you want a `onPageFinished` event.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebViewClient.events(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .ofType(OnPageFinished.class)
    .subscribe();
```


other types defines into [package](reactivex/src/main/java/com/github/satoshun/reactivex/webkit/data)


### WebChromeClient

observes all events.

```java
WebView webview = new WebView(context);
RxWebChromeClient.events(webview)
    .subscribeOn(AndroidSchedulers.mainThread())
    .ofType(DATA_TYPE.class)
    .subscribe();
```

other types defines into [package](reactivex/src/main/java/com/github/satoshun/reactivex/webkit/data)


## more

- [sample source code](app/src/main/java/com/github/satoshun/reactivex/webkit/example)
