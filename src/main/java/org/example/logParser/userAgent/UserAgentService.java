package org.example.logParser.userAgent;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import nl.basjes.parse.useragent.UserAgent;

public class UserAgentService implements UserAgentHandler {

  private final UserAgentAnalyzer analyzer =
      UserAgentAnalyzer.newBuilder()
          .withField("AgentName")
          .withField("OperatingSystemName")
          .build();

  @Override
  public UserAgentData parse(String rawUserAgent) {
    if (rawUserAgent == null || rawUserAgent.isBlank()) {
      return new UserAgentData("Unknown", "Unknown");
    }

    UserAgent agent = analyzer.parse(rawUserAgent);

    String browser = agent.getValue("AgentName");
    String os = agent.getValue("OperatingSystemName");

    return new UserAgentData(browser, os);
  }
}
