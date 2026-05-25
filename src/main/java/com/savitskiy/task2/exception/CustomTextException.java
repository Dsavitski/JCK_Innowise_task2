package com.savitskiy.task2.exception;

public class CustomTextException extends Exception {

    public CustomTextException(String message) {
        super(message);
    }

    public CustomTextException(String message, Throwable cause) {
        super(message, cause);
    }
}
