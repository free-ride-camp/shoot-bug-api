package org.freeride.shootbug.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
    private Integer httpCode;
    private String msg;
    private T data;
    private long timestamp;

    private ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMsg("success");
        response.setData(data);
        response.setHttpCode(HttpStatus.OK.value());
        return response;
    }

    public static <T> ApiResponse<T> fail(String message, HttpStatus httpStatus) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMsg(message);
        response.setHttpCode(httpStatus.value());
        return response;
    }
}
