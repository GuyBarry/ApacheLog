package org.example.logParser.report;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService implements ReportHandler {

    private Map<String, Double> sortByValueDescending(Map<String, Double> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    @Override
    public Report generateReport(Map<String, Double> countryPercentages, Map<String, Double> browserPercentages, Map<String, Double> osPercentages) {
        return new Report(
                sortByValueDescending(countryPercentages),
                sortByValueDescending(osPercentages),
                sortByValueDescending(browserPercentages));
    }
}
