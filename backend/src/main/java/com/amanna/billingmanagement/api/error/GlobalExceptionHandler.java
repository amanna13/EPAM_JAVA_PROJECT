package com.amanna.billingmanagement.api.error;

import com.amanna.billingmanagement.shared.kernel.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> handleDomainException(DomainException exception) {
        Map<String, Object> payload = Map.of(
                "timestamp", Instant.now().toString(),
                "error", "DOMAIN_VALIDATION",
                "message", exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }
}

