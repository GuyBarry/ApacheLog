package org.example;

import org.example.logParser.manager.LogManagerService;
import org.example.logParser.manager.LogParserManager;

public class Main {
  static void main() {
    LogParserManager manager = new LogManagerService();

    String report =
        manager.parseLogFile("https://www.dropbox.com/s/xs47xk59p7mgkbz/apache_log.txt?dl=0");

    System.out.println(report);
  }
}
