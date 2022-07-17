package com.exceptions;

import org.springframework.http.HttpStatus;

public class CustomError extends Error {
    private String message;
    private HttpStatus status;

    public CustomError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String message) {
        this.message = message;
    }

    public HttpStatus getstatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
