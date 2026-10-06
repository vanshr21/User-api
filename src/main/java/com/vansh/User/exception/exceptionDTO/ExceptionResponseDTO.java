package com.vansh.User.exception.exceptionDTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionResponseDTO {
    private LocalDateTime timeStamp;
    private Integer status;
    private String message;
    private String path;

    public ExceptionResponseDTO(LocalDateTime timeStamp, Integer status, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
