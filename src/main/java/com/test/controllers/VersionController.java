package com.test.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.config.DefaultHeaders;
import com.test.controllers.config.AbstractController;
import com.test.controllers.config.ApiDataRequest;
import com.test.controllers.config.ApiDataResponse;
import com.test.dto.VersionDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/version")
@DefaultHeaders
@RequiredArgsConstructor
public class VersionController implements AbstractController {

  @Value("${app.version}")
  private String appVersion;

  @GetMapping
  public ResponseEntity<ApiDataResponse<VersionDTO>> getVersion() {
    ApiDataRequest apiDataRequest = request();
    return ok(() -> VersionDTO.builder().version(appVersion).build(), apiDataRequest);
  }

}
