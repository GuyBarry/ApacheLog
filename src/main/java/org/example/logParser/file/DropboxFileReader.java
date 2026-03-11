package org.example.logParser.file;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class DropboxFileReader implements FileReader {

  private HttpClient client = buildHttpClient();

  public DropboxFileReader() {
    client = buildHttpClient();
  }

  private HttpClient buildHttpClient() {
    return HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
  }

  @Override
  public List<String> readFile(String filePath) {
    String downloadUrl = toDirectDownloadUrl(filePath);
    String body = fetchContent(downloadUrl);

    return Arrays.asList(body.split("\\r?\\n"));
  }

  // Converts a Dropbox sharing URL to a direct download URL
  private String toDirectDownloadUrl(String url) {
    if (url.contains("dl=0")) {
      return url.replace("dl=0", "dl=1");
    }

    return url.contains("dl=1") ? url : url + "?dl=1";
  }

  private String fetchContent(String downloadUrl) {
    HttpRequest request = buildRequest(downloadUrl);

    return sendRequest(client, request, downloadUrl);
  }

  private HttpRequest buildRequest(String url) {
    return HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
  }

  private String sendRequest(HttpClient client, HttpRequest request, String downloadUrl) {
    try {
      HttpResponse<String> response =
          client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
      validateResponse(response, downloadUrl);

      return response.body();
    } catch (IOException | InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Error reading file from Dropbox URL: " + downloadUrl, e);
    }
  }

  private void validateResponse(HttpResponse<String> response, String downloadUrl) {
    if (response.statusCode() != 200) {
      throw new RuntimeException(
          "Failed to download file from Dropbox. HTTP status: "
              + response.statusCode()
              + " URL: "
              + downloadUrl);
    }
  }
}
