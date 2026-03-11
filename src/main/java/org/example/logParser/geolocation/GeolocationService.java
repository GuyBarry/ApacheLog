package org.example.logParser.geolocation;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class GeolocationService implements Geolocation {

  private final DatabaseReader databaseReader;

  public GeolocationService() {
    try {
      InputStream dbStream =
          getClass().getClassLoader().getResourceAsStream("GeoLite2-Country.mmdb");
      if (dbStream == null) {
        throw new IllegalStateException("GeoLite2-Country.mmdb not found in resources");
      }
      this.databaseReader = new DatabaseReader.Builder(dbStream).build();
    } catch (IOException e) {
      throw new IllegalStateException("Failed to load GeoLite2-Country database", e);
    }
  }

  @Override
  public String getGeolocation(String ipAddress) {
    try {
      InetAddress inetAddress = InetAddress.getByName(ipAddress);
      CountryResponse response = databaseReader.country(inetAddress);
      return response.getCountry().getName();
    } catch (IOException | GeoIp2Exception e) {
      return "Other";
    }
  }
}
