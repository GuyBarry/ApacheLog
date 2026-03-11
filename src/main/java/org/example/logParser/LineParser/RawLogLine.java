package org.example.logParser.LineParser;

public class RawLogLine implements LogLineData {
  private final String ipAddress;
  private final String userAgent;

  public RawLogLine(String ipAddress, String userAgent) {
    this.ipAddress = ipAddress;
    this.userAgent = userAgent;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public String getUserAgent() {
    return userAgent;
  }
}
