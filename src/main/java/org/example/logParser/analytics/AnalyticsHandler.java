package org.example.logParser.analytics;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsHandler implements Analytics {
  private final Map<String, Integer> countryCount;
  private final Map<String, Integer> browserCount;
  private final Map<String, Integer> OSCount;
  private int errorLogs;

  public AnalyticsHandler() {
    countryCount = new HashMap<>();
    browserCount = new HashMap<>();
    OSCount = new HashMap<>();
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
  public void addErrorLog() {
    errorLogs++;
  }

  @Override
  public Map<String, Double> getBrowserPercentages() {
    return toPercentages(browserCount);
  }

  @Override
  public Map<String, Double> getCountryPercentages() {
    return toPercentages(countryCount);
  }

  @Override
  public Map<String, Double> getOSPercentages() {
    return toPercentages(OSCount);
  }

  private Map<String, Double> toPercentages(Map<String, Integer> countMap) {
    int total = countMap.values().stream().mapToInt(Integer::intValue).sum();
    if (total == 0) return new HashMap<>();
    return countMap.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            e -> Math.round((e.getValue() * 100.0 / total) * 100.0) / 100.0
        ));
  }
}
