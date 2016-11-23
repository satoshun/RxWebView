package com.github.satoshun.reactivex.webview.data;

import android.webkit.WebStorage;

public class OnExceededDatabaseQuota implements RxWebChromeClientData {

  private final String databaseIdentifier;
  private final long quota;
  private final long estimatedDatabaseSize;
  private final long totalQuota;
  private final WebStorage.QuotaUpdater quotaUpdater;


  public OnExceededDatabaseQuota(String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
    this.databaseIdentifier = databaseIdentifier;
    this.quota = quota;
    this.estimatedDatabaseSize = estimatedDatabaseSize;
    this.totalQuota = totalQuota;
    this.quotaUpdater = quotaUpdater;
  }

  public String getDatabaseIdentifier() {
    return databaseIdentifier;
  }

  public long getQuota() {
    return quota;
  }

  public long getEstimatedDatabaseSize() {
    return estimatedDatabaseSize;
  }

  public long getTotalQuota() {
    return totalQuota;
  }

  public WebStorage.QuotaUpdater getQuotaUpdater() {
    return quotaUpdater;
  }
}
