package org.example.logParser.analytics;

public interface Analytics {
  void addOS(String os);

  void addBrowser(String browser);

  void addCountry(String country);

  void setTotalLogs(int totalLogs);
  void addErrorLog();
}
