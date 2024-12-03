package com.test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.controllers.config.AbstractController;
import com.test.controllers.config.ApiDataRequest;
import com.test.controllers.config.ApiDataResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController implements AbstractController {

  @GetMapping
  public ResponseEntity<ApiDataResponse<String>> getVersion() {
    ApiDataRequest apiDataRequest = request();
    return ok(() -> "test", apiDataRequest);
  }

}
