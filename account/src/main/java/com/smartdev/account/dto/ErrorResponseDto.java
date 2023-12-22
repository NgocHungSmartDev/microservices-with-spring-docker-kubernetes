package com.smartdev.account.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
  private String apiPath;

  private HttpStatus errorCode;

  private String errorMessage;

  private LocalDateTime errorTime;
}
