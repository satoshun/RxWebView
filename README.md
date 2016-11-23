# RxWebView

## install

```
compile 'com.github.satoshun:RxWebView:0.2.1'
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

## data types(RxWebViewClientData)

- DoUpdateVisitedHistory : WebViewClient#doUpdateVisitedHistory
- OnReceivedClientCertRequest : WebViewClient#onReceivedClientCertRequest
- OnScaleChanged : WebViewClient#OnScaleChanged
- ShouldOverrideKeyEvent : WebViewClient#shouldOverrideKeyEvent
- OnFormResubmission : WebViewClient#onFormResubmission
- OnReceivedError: WebViewClient#onReceivedError
- OnTooManyRedirect: WebViewClient#onTooManyRedirect
- ShouldOverrideUrlLoading: WebViewClient#shouldOverrideUrlLoading
- OnLoadResource: WebViewClient#onLoadResource
- OnReceivedHttpAuthRequest: WebViewClient#onReceivedHttpAuthRequest
- OnUnhandledKeyEvent: WebViewClient#onUnhandledKeyEvent
- ShouldOverrideUrlLoadingWebResourceRequest: WebViewClient#shouldOverrideUrlLoading
- OnPageCommitVisible: WebViewClient#onPageCommitVisible
- OnReceivedHttpError: WebViewClient#onReceivedHttpError
- WebResourceOnReceivedError: WebViewClient#onReceivedError
- OnPageFinished: WebViewClient#onPageFinished
- OnReceivedLoginRequest: WebViewClient#onReceivedLoginRequest
- ShouldInterceptRequest: WebViewClient#shouldInterceptRequest
- OnPageStarted: WebViewClient#onPageStarted
- OnReceivedSslError: WebViewClient#onReceivedSslError
- ShouldInterceptWebResourceRequest: WebViewClient#shouldInterceptWebResourceRequest


## todo

- corresponds to WebChromeClient
