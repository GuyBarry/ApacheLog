package org.example.logParser.LineParser;

public class ApacheLogLineParser implements LogLineParser {

  @Override
  public LogLineData getLogLineData(String logLine) {
    if (logLine == null || logLine.isBlank()) {
      return null;
    }

    String ipAddress = logLine.split(" ")[0];

    int lastQuoteEnd = logLine.lastIndexOf('"');
    int lastQuoteStart = logLine.lastIndexOf('"', lastQuoteEnd - 1);

    if (lastQuoteStart < 0 || lastQuoteEnd <= lastQuoteStart) {
      return null;
    }

    String userAgent = logLine.substring(lastQuoteStart + 1, lastQuoteEnd);

    return new LogLineData(ipAddress, userAgent);
  }
}
