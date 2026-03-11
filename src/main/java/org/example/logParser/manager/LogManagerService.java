package org.example.logParser.manager;

import org.example.logParser.LineParser.ApacheLogLineParser;
import org.example.logParser.LineParser.LogLineData;
import org.example.logParser.LineParser.LogLineParser;
import org.example.logParser.analytics.Analytics;
import org.example.logParser.analytics.AnalyticsHandler;
import org.example.logParser.file.DropboxFileReader;
import org.example.logParser.file.FileReader;
import org.example.logParser.geolocation.Geolocation;
import org.example.logParser.geolocation.GeolocationService;
import org.example.logParser.report.Report;
import org.example.logParser.report.ReportHandler;
import org.example.logParser.report.ReportService;
import org.example.logParser.userAgent.UserAgentData;
import org.example.logParser.userAgent.UserAgentHandler;
import org.example.logParser.userAgent.UserAgentService;

import java.util.List;

public class LogManagerService implements LogParserManager {
  private final FileReader fileReader;
  private final LogLineParser logLineParser;
  private final UserAgentHandler userAgentHandler;
  private final Analytics analytics;
  private final Geolocation geolocation;
  private final ReportHandler reportHandler;

  public LogManagerService() {
    this.fileReader = new DropboxFileReader();
    this.logLineParser = new ApacheLogLineParser();
    this.userAgentHandler = new UserAgentService();
    this.analytics = new AnalyticsHandler();
    this.geolocation = new GeolocationService();
    this.reportHandler = new ReportService();
  }

  @Override
  public String parseLogFile(String logFilePath) {
    List<String> logFileLines = fileReader.readFile(logFilePath);

    if (logFileLines == null || logFileLines.isEmpty()) {
      return "Report: The log file is empty. No data to analyze.";
    }

    logFileLines.forEach(this::processLine);

    Report report =
        reportHandler.generateReport(
            analytics.getCountryPercentages(),
            analytics.getBrowserPercentages(),
            analytics.getOSPercentages());

    return report.toString();
  }

  private void processLine(String line) {
    LogLineData lineData = logLineParser.getLogLineData(line);

    if (lineData == null) {
      analytics.addErrorLog();
    } else {
      handleUserAgent(lineData.userAgent());
      handleGeolocation(lineData.ipAddress());
    }
  }

  private void handleGeolocation(String ipAddress) {
    String country = geolocation.getGeolocation(ipAddress);
    analytics.addCountry(country);
  }

  private void handleUserAgent(String rawUserAgent) {
    UserAgentData userAgent = userAgentHandler.parse(rawUserAgent);
    analytics.addBrowser(userAgent.browser());
    analytics.addOS(userAgent.os());
  }
}
