package org.freeride.shootbug.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/4 16:42
 */

@Getter
public class ApiResponseException extends RuntimeException {
    private HttpStatus httpStatus;

    public ApiResponseException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiResponseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
