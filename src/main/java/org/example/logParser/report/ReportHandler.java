package org.example.logParser.report;

import java.util.Map;

public interface ReportHandler {
     Report generateReport(Map<String, Double> countryPercentages, Map<String, Double> browserPercentages, Map<String, Double> osPercentages);
}
