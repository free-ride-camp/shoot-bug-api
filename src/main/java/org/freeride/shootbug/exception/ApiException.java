package org.freeride.shootbug.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/1 10:27
 */

public class ApiException extends ResponseStatusException {

    public ApiException(HttpStatus status, String message) {
        super(status, message);
    }

    public ApiException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
