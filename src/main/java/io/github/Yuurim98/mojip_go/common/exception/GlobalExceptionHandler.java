package io.github.Yuurim98.mojip_go.common.exception;

import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "[ERROR] {} : {}";
    private static final String ERROR_MESSAGE = "요청 형식이 올바르지 않거나 요청 본문이 비어 있습니다.";

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException exception) {
        log.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());

        return ResponseEntity.status(exception.getErrorCode().getHttpStatus())
            .body(ApiResponse.error(exception.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
        MethodArgumentNotValidException exception) {

        FieldError firstError = exception.getFieldErrors().getFirst();
        String message = String.format("{%s} %s", firstError.getField(),
            firstError.getDefaultMessage());

        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getHttpStatus())
            .body(ApiResponse.error(ErrorCode.BAD_REQUEST.getHttpStatus(), message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException exception) {
        log.error(ERROR_LOG_MESSAGE, exception.getClass().getName(), exception.getMessage());

        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getHttpStatus())
            .body(ApiResponse.error(ErrorCode.BAD_REQUEST.getHttpStatus(), ERROR_MESSAGE));
    }
}
