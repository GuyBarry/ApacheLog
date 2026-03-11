package org.example.logParser.analytics;

import java.util.Map;

public interface Analytics {
  void addOS(String os);

  void addBrowser(String browser);

  void addCountry(String country);

  void addErrorLog();

  Map<String, Double> getBrowserPercentages();
  Map<String, Double> getCountryPercentages();
  Map<String, Double> getOSPercentages();
}
