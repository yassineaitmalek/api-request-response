package com.test.controllers.config;

import java.time.LocalDateTime;

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
public class ApiDataResponse<T> {

  private String status;

  private Integer httpStatus;

  private LocalDateTime start;

  private LocalDateTime end;

  private Long duration;

  private T data;

}
