package com.vansh.User.exception.exceptionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ValidExceptionDTO {
    private LocalDateTime timeStamp;
    private Integer status;
    private String message;
    private Map<String, String> errors;
    private String path;

    public ValidExceptionDTO(LocalDateTime timeStamp, Integer status, String message, Map<String, String> errors, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
