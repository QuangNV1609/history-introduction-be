package com.uet.quangnv.exception.domain;

public class DuplicateIDException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicateIDException() {
    }

    public DuplicateIDException(String message) {
        super(message);
    }

    public DuplicateIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateIDException(Throwable cause) {
        super(cause);
    }

    public DuplicateIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
