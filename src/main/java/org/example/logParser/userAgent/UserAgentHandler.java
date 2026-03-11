package org.example.logParser.userAgent;

public interface UserAgentHandler {
    UserAgentData parse(String rawUserAgent);
}
