package com.dpiataikin.microservice.common.response;

import lombok.Getter;

@Getter
public enum CommonStatus {
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    UNKNOWN_ERROR(500);

    private final Integer status;

    CommonStatus(Integer status) {
        this.status = status;
    }
}
