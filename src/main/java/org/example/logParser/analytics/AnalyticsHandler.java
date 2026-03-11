package org.example.logParser.analytics;

import java.util.HashMap;
import java.util.Map;

public class AnalyticsHandler implements Analytics {
  private final Map<String, Integer> countryCount;
  private final Map<String, Integer> browserCount;
  private final Map<String, Integer> OSCount;
  private int totalLogs;
  private int errorLogs;

  public AnalyticsHandler() {
    countryCount = new HashMap<>();
    browserCount = new HashMap<>();
    OSCount = new HashMap<>();
    totalLogs = 0;
    errorLogs = 0;
  }

  @Override
  public void addCountry(String country) {
    countryCount.put(country, countryCount.getOrDefault(country, 0) + 1);
  }

  @Override
  public void addBrowser(String browser) {
    browserCount.put(browser, browserCount.getOrDefault(browser, 0) + 1);
  }

  @Override
  public void addOS(String os) {
    OSCount.put(os, OSCount.getOrDefault(os, 0) + 1);
  }

  @Override
  public void setTotalLogs(int totalLogs) {
    this.totalLogs = totalLogs;
  }

  @Override
  public void addErrorLog() {
    errorLogs++;
  }
}
