package org.freeride.shootbug.dto;

import lombok.Data;

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
        response.setHttpCode(200);
        return response;
    }

    public static <T> ApiResponse<T> fail(String message, Integer httpCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMsg(message);
        response.setHttpCode(httpCode);
        return response;
    }
}
