package com.bk.registry.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    SYSTEM_ERROR("/system-error", "System error"),
    INVALID_DATA("/invalid-data", "Invalid data"),
    NOT_FOUND("/resource-not-found","Resource not found"),
    ALREAD_EXISTS("/resource-already-exists","Resource already exist")
;

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.title = title;
        this.uri = "http://localhost"+path;
    }
}
