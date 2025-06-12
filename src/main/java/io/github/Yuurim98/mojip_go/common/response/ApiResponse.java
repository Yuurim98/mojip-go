package io.github.Yuurim98.mojip_go.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "success";

    private final int code;
    private final String message;

    @JsonInclude(Include.NON_NULL)
    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static ApiResponse<String> success() {
        return new ApiResponse<String>(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<Void>(errorCode.getHttpStatus().value(), errorCode.getMessage(), null);
    }

    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return new ApiResponse<Void>(status.value(), message, null);
    }


}
