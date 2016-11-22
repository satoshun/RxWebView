# RxWebView

## install

```
compile 'com.github.satoshun:RxWebView:0.1.0'
```


## usage

only observes WebViewClient#onPageStarted.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebView.onPageStarted(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .subscribe();
```

and only observes WebViewClient#onPageFinished.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebView.onPageFinished(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .subscribe();
```

and observes all events.

```java
WebView webview = new WebView(context);
WebViewClient client = new WebViewClient();
RxWebView.all(webview, client)
    .subscribeOn(AndroidSchedulers.mainThread())
    .filter(value -> value instanceof `DATA_TYPE`)
    .map(value -> (`DATA_TYPE`) value)
    .subscribe();
```

data types:

DoUpdateVisitedHistory
OnReceivedClientCertRequest
OnScaleChanged
ShouldOverrideKeyEvent
OnFormResubmission
OnReceivedError
OnTooManyRedirect
ShouldOverrideUrlLoading
OnLoadResource
OnReceivedHttpAuthRequest
OnUnhandledKeyEvent
ShouldOverrideUrlLoadingWebResourceRequest
OnPageCommitVisible
OnReceivedHttpError
WebResourceOnReceivedError
OnPageFinished
OnReceivedLoginRequest
ShouldInterceptRequest
OnPageStarted
OnReceivedSslError
ShouldInterceptWebResourceRequest


## todo

more document and samples
