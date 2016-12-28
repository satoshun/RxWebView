# RxWebView

[![](https://jitpack.io/v/satoshun/RxWebView.svg)](https://jitpack.io/#satoshun/RxWebView)

this project inspires [RxBinding](https://github.com/JakeWharton/RxBinding). It was very helpful. thx!


## install

```
compile 'com.github.satoshun:RxWebView:1.0.0'
```


## usage

### WebViewClient

only observes WebViewClient#onPageStarted.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebViewClient.onPageStarted(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .subscribe();
```

and only observes WebViewClient#onPageFinished.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebViewClient.onPageFinished(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .subscribe();
```

and observes all events.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebViewClient.all(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .filter(value -> value instanceof `DATA_TYPE`)
    .cast(`DATA_TYPE`.class)
    .subscribe();
```

DATA_TYPE corresponds to all WebViewClient methods. please view [package](reactivex/src/main/java/com/github/satoshun/reactivex/webview/data)


### WebChromeClient

observes all events.

```java
WebView webview = new WebView(context);
WebChromeClient client = new WebChromeClient(); // your custom WebChromeClient
RxWebChromeClient.all(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .filter(value -> value instanceof `DATA_TYPE`)
    .cast(`DATA_TYPE`.class)
    .subscribe();
```

DATA_TYPE corresponds to all WebChromeClient methods. please view [package](reactivex/src/main/java/com/github/satoshun/reactivex/webview/data)


## more

- [sample source code](app/src/main/java/com/github/satoshun/reactivex/webview/example)
