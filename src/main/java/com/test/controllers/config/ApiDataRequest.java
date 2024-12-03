package com.test.controllers.config;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.ContentCachingRequestWrapper;

import com.test.exception.config.ClientSideException;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataRequest {

  private String serviceName;

  private String originalApp;

  private String targetApp;

  private String canal;

  private String protocol;

  private String method;

  private String uri;

  private String body;

  @Builder.Default
  private String requestID = UUID.randomUUID().toString();

  @Builder.Default
  private LocalDateTime start = LocalDateTime.now();

  public static ApiDataRequest of(HttpServletRequest request) {

    ClientSideException exp = new ClientSideException("error in the request header");
    return ApiDataRequest.builder()
        .serviceName(Optional.of("serviceName").map(request::getHeader).orElseThrow(() -> exp))
        .originalApp(Optional.of("originalApp").map(request::getHeader).orElseThrow(() -> exp))
        .targetApp(Optional.of("targetApp").map(request::getHeader).orElseThrow(() -> exp))
        .canal(Optional.of("canal").map(request::getHeader).orElseThrow(() -> exp))
        .protocol(getProtocol(request))
        .method(getMethod(request))
        .uri(getURI(request))
        .body(getRequestBody(request))
        .build();
  }

  public static String getMethod(HttpServletRequest request) {

    return Try.of(() -> request).mapTry(HttpServletRequest::getMethod).getOrElse(String::new);
  }

  public static String getURI(HttpServletRequest request) {

    return Try.of(() -> request).mapTry(HttpServletRequest::getRequestURI).getOrElse(String::new);
  }

  public static String getRequestBody(HttpServletRequest request) {
    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();
    String characterEncoding = request.getCharacterEncoding();
    return Try.of(() -> new String(contentAsByteArray, 0,
        contentAsByteArray.length, characterEncoding))
        .getOrElse(String::new);

  }

  public static String getProtocol(HttpServletRequest request) {

    return Try.of(() -> request)
        .mapTry(HttpServletRequest::getScheme)
        .mapTry(String::toUpperCase)
        .getOrElse(String::new);

  }
}
