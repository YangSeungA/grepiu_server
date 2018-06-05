package com.grepiu.www.process.common.utils;

import com.grepiu.www.process.common.domain.MapGoogleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * 지도 Helper
 *
 */
@Slf4j
public class MapUtil {
  private String googleGeoCodingApi = "https://maps.googleapis.com/maps/api/geocode/json";

  /**
   *
   * 주소를 받아서 좌표 값으로 변환한다.
   *
   * @param address
   * @return
   */
  public MapGoogleVO searchLocalePointWithGoogle(String address) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

    log.debug("address : {}", address);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(googleGeoCodingApi)
        .queryParam("address", address)
        .queryParam("key", "AIzaSyCdlJJ5RuA6jrq-VzexA-8ryqOJVqTJJ-Y");
    HttpEntity<?> entity = new HttpEntity<>(headers);

    HttpEntity<MapGoogleVO> response = restTemplate.exchange(
        builder.build(false).toString(),
        HttpMethod.GET,
        entity,
        MapGoogleVO.class);
    return response.getBody();
  }

}
