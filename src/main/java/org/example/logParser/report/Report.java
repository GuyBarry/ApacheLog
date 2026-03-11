package org.example.logParser.report;

import java.util.Map;

public class Report {
  private final Map<String, Double> countriesPrecentages;
  private final Map<String, Double> OSPrecentages;
  private final Map<String, Double> BrowsersPrecentages;

  public Report(
      Map<String, Double> countriesPercentages,
      Map<String, Double> osPercentages,
      Map<String, Double> browsersPercentages) {
    this.countriesPrecentages = countriesPercentages;
    OSPrecentages = osPercentages;
    BrowsersPrecentages = browsersPercentages;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Report:\n");
    sb.append("---Countries:--------------------\n");
    countriesPrecentages.forEach(
        (country, percentage) -> sb.append(String.format("%s: %.2f%%\n", country, percentage)));
    sb.append("---Operating Systems:------------\n");
    OSPrecentages.forEach(
        (os, percentage) -> sb.append(String.format("%s: %.2f%%\n", os, percentage)));
    sb.append("---Browsers:---------------------\n");
    BrowsersPrecentages.forEach(
        (browser, percentage) -> sb.append(String.format("%s: %.2f%%\n", browser, percentage)));
    return sb.toString();
  }
}
