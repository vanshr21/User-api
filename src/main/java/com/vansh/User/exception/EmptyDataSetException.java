package com.vansh.User.exception;

public class EmptyDataSetException extends RuntimeException{
    public EmptyDataSetException(String message) {
       super(message);
    }
}
