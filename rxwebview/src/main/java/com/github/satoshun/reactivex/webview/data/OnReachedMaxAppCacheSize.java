package com.github.satoshun.reactivex.webview.data;

import android.webkit.WebStorage;

public class OnReachedMaxAppCacheSize implements RxWebChromeClientData {

  private final long requiredStorage;
  private final long quota;
  private final WebStorage.QuotaUpdater quotaUpdater;

  public OnReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
    this.requiredStorage = requiredStorage;
    this.quota = quota;
    this.quotaUpdater = quotaUpdater;
  }

  public long getRequiredStorage() {
    return requiredStorage;
  }

  public long getQuota() {
    return quota;
  }

  public WebStorage.QuotaUpdater getQuotaUpdater() {
    return quotaUpdater;
  }
}
